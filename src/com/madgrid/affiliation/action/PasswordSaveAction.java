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

import com.madgrid.affiliation.form.PasswordForm;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationUser;

public class PasswordSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		PasswordForm passwordForm = (PasswordForm) form;
		ActionErrors errors	= new ActionErrors();
		
		AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
		affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
		
		
		if( Utils.nullOrBlank( passwordForm.getPreviousPassword())){
			errors.add( "previousPassword",new ActionError( "errors.previouspassword.required"));
		}
		
		if( Utils.nullOrBlank( passwordForm.getPassword())){
			errors.add( "password",new ActionError( "errors.password.required"));
		}
		
		if( Utils.nullOrBlank( passwordForm.getRepeatPassword())){
			errors.add( "repeatPassword",new ActionError( "errors.repeatpassword.required"));
		}
		
		if( !Utils.nullOrBlank(passwordForm.getPreviousPassword()) && !Utils.digest(passwordForm.getPreviousPassword()).equals( affiliationUser.getPassword())){
			errors.add( "previousPassword",new ActionError( "errors.previouspassword.invalid"));
			
		} else if(  !Utils.nullOrBlank(passwordForm.getPassword()) &&  !Utils.nullOrBlank(passwordForm.getRepeatPassword()) &&!passwordForm.getPassword().equals( passwordForm.getRepeatPassword())){
			errors.add( "repeatPassword",new ActionError( "errors.passwordnotequal"));
		}
		
		if( !errors.isEmpty()){
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}

		affiliationUser.setPassword( Utils.digest(passwordForm.getPassword()));
		
		affiliationUserDAO.setAffiliationUser(affiliationUser);
		
		request.setAttribute("systemAlert", "The password of your affiliation control panel user has been changed successfully");
		
		return mapping.findForward( "ok");
	}

}		
