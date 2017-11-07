package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.PasswordForm;


public class PasswordEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		PasswordForm passwordForm = new PasswordForm();
		passwordForm.setPassword( null);
		passwordForm.setPreviousPassword( null);
		passwordForm.setRepeatPassword( null);
		request.setAttribute( "passwordForm", passwordForm);
		
		return mapping.findForward( "ok");
	}

}		
