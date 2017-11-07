package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.PasswordReset1Form;
import com.madgrid.affiliation.util.Mail;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.affiliation.util.mail.NewPasswordMailObject;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.User;

public class PasswordReset1Action extends Action {

	
	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		PasswordReset1Form passwordReset1Form = (PasswordReset1Form) form;
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		
		if( request.getSession().getAttribute( "affiliationUserSession") != null){
			return null;
		}

		Criteria criteria = new Criteria();
		criteria.addEqualTo("email", passwordReset1Form.getEmail());
		AffiliationUser affiliationUser = affiliationUserDAO.getAffiliationUserByCriteria(criteria);
		
		if( affiliationUser != null){
			String code = generateCode();
			
			affiliationUser.setCodeForPasswordRestart(code);
			affiliationUser.setCodeForPasswordRestartCreated(Utils.today());
			
			affiliationUserDAO.setAffiliationUser(affiliationUser);
			
			NewPasswordMailObject newPasswordMailObject = new NewPasswordMailObject( affiliationUser.getLogin(), code, affiliationUser.getEmail(), Utils.getBaseUrl());
		
			Mail mail = new Mail( affiliationUser.getEmail(), "You have resquested a password reseting for your Instantri.ch affiliation control panel", newPasswordMailObject);
			mail.start();
			
		} 
		
		request.setAttribute("systemAlert", "Check your email inbox. You should have an email from us with a link you have to click on.");
		
		return mapping.findForward( "ok");
	}
	
	private String generateCode() 
	{
		String 	chars 	= "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String 	code	= "";
		long 	size	= 32;
		
		while( code.length() < size){
	      code = code + chars.charAt( ( int) Math.round( Math.random() * ( chars.length() - 1)));
		}
		
		return code;
	}
}		
