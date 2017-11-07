package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationUserMessage;

public class MessagesReceivedViewAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		String idString = request.getParameter("id");
		Integer id = null;
		
		try{
			id=Integer.parseInt(idString);
		}catch (NumberFormatException e) {
			return null;
		}
		
		if( id != null){
			AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
			
			AffiliationUser affiliationUser = (AffiliationUser) request.getSession().getAttribute( "affiliationUserSession");
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("id", id);
			
			AffiliationUserMessage messageReceived= affiliationUserMessageDAO.getAffiliationUserMessageByCriteria( criteria);

			if( messageReceived != null){
				messageReceived.setIsRead(true);
				affiliationUserMessageDAO.setAffiliationUserMessage(messageReceived);
				
				request.setAttribute("messageReceived", messageReceived);
				
				return mapping.findForward( "ok");
			}
		}
		
		return null;
	}

}		
