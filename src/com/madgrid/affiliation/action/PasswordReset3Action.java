package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.PasswordReset2Form;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationUser;

public class PasswordReset3Action extends Action {

	
	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		
		PasswordReset2Form passwordReset2Form = (PasswordReset2Form) form;
		
		ActionErrors errors	= new ActionErrors();
		
		if( !Utils.nullOrBlank( passwordReset2Form.getPassword())){
			if( passwordReset2Form.getPassword().length() > 255){
				errors.add( "password",new ActionError( "error.password.long"));
			} 
			
			if( Utils.nullOrBlank( passwordReset2Form.getPassword2())){
				errors.add( "password2",new ActionError( "error.password2.required"));
			} else{
				if( !passwordReset2Form.getPassword().equals( passwordReset2Form.getPassword2())){
					errors.add( "password2",new ActionError( "error.password2.equals"));
				}
			}
		}
		
		if( !errors.isEmpty()){
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		AffiliationUser affiliationUser = (AffiliationUser) request.getSession().getAttribute( "affiliationUserSession");
		
		affiliationUser = affiliationUserDAO.getAffiliationUserById( affiliationUser.getId());
		
		affiliationUser.setModified( Utils.today());
		affiliationUser.setCodeForPasswordRestart(null);
		affiliationUser.setLastLogin(null);
		affiliationUser.setCodeForPasswordRestartCreated(null);
		
		if( !Utils.nullOrBlank(passwordReset2Form.getPassword())){
			affiliationUser.setPassword( Utils.digest(passwordReset2Form.getPassword()));
		}
		
		
		affiliationUserDAO.setAffiliationUser( affiliationUser);
		
		request.setAttribute("systemAlert", "Your password has been changed successfully.");
		
		return mapping.findForward("ok");
	}
	
	
}		
