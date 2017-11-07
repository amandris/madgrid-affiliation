package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.util.Mail;
import com.madgrid.affiliation.form.MessageForm;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.affiliation.util.mail.AffiliationContactMailObject;
import com.madgrid.dao.AffiliationContactDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationContact;
import com.madgrid.model.AffiliationUser;

public class MessageSendAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationContactDAO affiliationContactDAO = new AffiliationContactDAO();
		MessageForm messageForm = (MessageForm) form;
		ActionErrors errors	= new ActionErrors();
		
		if( Utils.nullOrBlank(messageForm.getSubject())){
			errors.add( "subject",new ActionError( "errors.message.subject.empty"));
		} else if( messageForm.getSubject().length()>=255){
			errors.add( "subject",new ActionError( "errors.message.subject.invalid"));
		}
		
		if( Utils.nullOrBlank(messageForm.getMessage())){
			errors.add( "message",new ActionError( "errors.message.message.empty"));
		} else if( messageForm.getMessage().length()>=1000){
			errors.add( "message",new ActionError( "errors.message.message.invalid"));
		}
		
		if( !errors.isEmpty()){
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		
		AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
		affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
		
		AffiliationContact affiliationContact = new AffiliationContact();
		affiliationContact.setAffiliationUser(affiliationUser);
		affiliationContact.setAffiliationUserId(affiliationUser.getId());
		affiliationContact.setCreated(Utils.today());
		affiliationContact.setIsRead(false);
		affiliationContact.setMessage(messageForm.getMessage());
		affiliationContact.setSubject(messageForm.getSubject());
		
		affiliationContactDAO.setAffiliationContact(affiliationContact);
		
		AffiliationContactMailObject affiliationContactMailObject = new AffiliationContactMailObject( affiliationUser.getLogin(), affiliationUser.getEmail(), messageForm.getMessage(), messageForm.getSubject(), Utils.getBaseUrl());
		Mail mail = new Mail(  "amandris@hotmail.com", "Un afiliado de Instantri.ch te acaba de mandar un mensaje", affiliationContactMailObject);
		mail.start();
		
		request.setAttribute("systemAlert", "The message was sent to admin successfully");
	
		return mapping.findForward( "ok");
	}

}		
