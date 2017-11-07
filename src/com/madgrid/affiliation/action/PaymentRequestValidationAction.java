package com.madgrid.affiliation.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.util.Utils;
import com.madgrid.dao.AffiliationUserDAO;

import com.madgrid.model.AffiliationUser;

public class PaymentRequestValidationAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		boolean isLogged = true;
		
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			isLogged = false;
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		
		String login = request.getParameter("login");
		String code = request.getParameter("code");
		
		if( !Utils.nullOrBlank( login) && !Utils.nullOrBlank( "code")){
			Criteria criteria = new Criteria();
			criteria.addEqualTo("login", login);
			
			AffiliationUser affiliationUser = affiliationUserDAO.getAffiliationUserByCriteria(criteria );
			
			if( affiliationUser != null){
				if(code.equals( Utils.digestMD5( "kasuhy3b39" + affiliationUser.getLogin()))){
					affiliationUser.setPaymentRequestValidated( true);
					
					affiliationUserDAO.setAffiliationUser(affiliationUser);
					request.setAttribute("systemAlert", "The payment transaction has been validated. All payments are processed manually so it may take up to 24 hour to be performed. Please be patient.");
					
					if( isLogged){
						return mapping.findForward( "okLogged");
					} else{
						return mapping.findForward( "okNotLogged");
					}
				}
			}
			
		}

		
		request.setAttribute("systemAlertWarning", "There was an error validating payment transaction. Please contact admin for further details.");
		if( isLogged){
			return mapping.findForward( "koLogged");
		} else{
			return mapping.findForward( "koNotLogged");
		}
		
	}

}		
