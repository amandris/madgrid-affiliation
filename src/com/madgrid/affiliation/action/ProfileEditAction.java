package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.ProfileForm;

import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationUser;

public class ProfileEditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
		affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
		
		ProfileForm profileForm = new ProfileForm();
		
		profileForm.setAskedForTransfer(affiliationUser.getAskedForTransfer());
		profileForm.setBitcoinAddress(affiliationUser.getBitcoinAddress());
		profileForm.setEmail( affiliationUser.getEmail());
		profileForm.setId( affiliationUser.getId());
		profileForm.setLogin(affiliationUser.getLogin());
		profileForm.setPercentage(affiliationUser.getPercentage());
		profileForm.setSendEmailAlerts(affiliationUser.getSendEmailAlerts());
		profileForm.setUrl( affiliationUser.getUrl());
		
		
		request.setAttribute( "profileForm", profileForm);

						
		return mapping.findForward( "ok");
		
	}

}		
