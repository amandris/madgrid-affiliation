package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.util.Mail;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.affiliation.util.mail.AddCreditsByAdminMailObject;

import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.dao.UserDAO;
import com.madgrid.dao.UserHistoricDAO;
import com.madgrid.model.AffiliationUser;
import com.madgrid.model.User;
import com.madgrid.model.UserHistoric;

public class ClaimCreditAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
		affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
		
		if( affiliationUser.getCreditAlreadyClaimed() == false){
			UserDAO userDAO = new UserDAO();
			Criteria criteria = new Criteria();
			criteria.addEqualTo("login", affiliationUser.getLogin());
			
			User user = userDAO.getUserByCriteria(criteria);
			
			if( user != null){
				user.setCredits(user.getCredits() + 1);
				
				UserHistoricDAO userHistoricDAO = new UserHistoricDAO();
				UserHistoric userHistoric = new UserHistoric();
				userHistoric.setCreated( Utils.today() );
				userHistoric.setType( UserHistoric.GET_CREDIT_BY_ADMIN_MORE);
				userHistoric.setUser(user);
				userHistoric.setUserId(user.getId());
				userHistoric.setValue1(1d);
				
				userDAO.setUser(user);
				userHistoricDAO.setUserHistoric(userHistoric);
				
				affiliationUser.setCreditAlreadyClaimed( true);
				affiliationUserDAO.setAffiliationUser(affiliationUser);
				request.getSession().setAttribute( "affiliationUserSession", affiliationUser); 
				
				AddCreditsByAdminMailObject addCreditsByAdminMailObject = new AddCreditsByAdminMailObject( user.getLogin(), Utils.getBaseUrl());
				Mail mail = new Mail(  user.getEmail(), "We've added 1 credit to your account", addCreditsByAdminMailObject);
				mail.start();
				
				request.setAttribute("systemAlert", "The credit has been added to your Instantri.ch account successfully");
			}
		}
						
		return mapping.findForward( "ok");
		
	}

}		
