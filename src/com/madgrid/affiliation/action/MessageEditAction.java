package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.MessageForm;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationUserMessage;

public class MessageEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		String idString = request.getParameter("id");
		
		if( !Utils.nullOrBlank(idString)){
			Integer id = null;
			try{
				id = Integer.parseInt( idString);
			} catch (NumberFormatException e) {
				id= null;
			}
			
			if( id != null){
				AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
				AffiliationUserMessage affiliationUserMessage = affiliationUserMessageDAO.getAffiliationUserMessageById(id);
				
				if( affiliationUserMessage != null){
					MessageForm messageForm = new MessageForm();
					messageForm.setSubject( "Re: " + affiliationUserMessage.getSubject());
					request.setAttribute( "messageForm", messageForm);
				}
			}
		}
		
						
		return mapping.findForward( "ok");
		
	}

}		
