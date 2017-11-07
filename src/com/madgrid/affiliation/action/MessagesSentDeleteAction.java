package com.madgrid.affiliation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AffiliationContactDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.AffiliationContact;

public class MessagesSentDeleteAction extends Action {

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
			response.getWriter().print( "ko");
			return null;
		}
		
		if( id != null){
			AffiliationContactDAO affiliationContactDAO = new AffiliationContactDAO();
			
			AffiliationUser affiliationUser = (AffiliationUser) request.getSession().getAttribute( "affiliationUserSession");
			
			Criteria criteria = new Criteria();
			criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
			criteria.addEqualTo("id", id);
			
			AffiliationContact messageSent= affiliationContactDAO.getAffiliationContactByCriteria( criteria);

			if( messageSent != null){
				affiliationContactDAO.deleteAffiliationContact(messageSent);
				response.getWriter().print( "ok");
				return null;
			}
		}
		
		response.getWriter().print( "ko");
		return null;
	}

}		
