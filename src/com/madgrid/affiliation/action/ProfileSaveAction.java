package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.ProfileForm;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationUser;

public class ProfileSaveAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		ProfileForm profileForm = (ProfileForm) form;
		ActionErrors errors	= new ActionErrors();
		
		if( profileForm.getUrl().length()>=255){
			errors.add( "url",new ActionError( "errors.profile.url.invalid"));
		}
		
		if( profileForm.getBitcoinAddress().length()>=40){
			errors.add( "bitcoinAddress",new ActionError( "errors.profile.bitcoinaddress.invalid"));
		}
		
		if( !errors.isEmpty()){
			
			AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
			affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
			
			profileForm.setAskedForTransfer(affiliationUser.getAskedForTransfer());
			profileForm.setEmail( affiliationUser.getEmail());
			profileForm.setId( affiliationUser.getId());
			profileForm.setLogin(affiliationUser.getLogin());
			profileForm.setPercentage(affiliationUser.getPercentage());
			
			request.setAttribute( "profileForm", profileForm);
			
			
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		
		AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
		affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
		
		affiliationUser.setUrl(profileForm.getUrl());
		affiliationUser.setBitcoinAddress(profileForm.getBitcoinAddress());
		affiliationUser.setSendEmailAlerts(profileForm.getSendEmailAlerts());
		
		affiliationUserDAO.setAffiliationUser(affiliationUser);
		
		request.getSession().setAttribute( "affiliationUserSession", affiliationUser); 
		
		request.setAttribute("systemAlert", "Your profile has been updated successfully");
	
		return mapping.findForward( "ok");
	}

}		
