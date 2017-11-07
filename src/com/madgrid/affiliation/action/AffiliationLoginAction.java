package com.madgrid.affiliation.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.form.LoginForm;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationUser;

public class AffiliationLoginAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		LoginForm loginForm = (LoginForm) form;
		ActionErrors errors	= new ActionErrors();
		Date today = Utils.today();
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo( "login", loginForm.getLogin());
		criteria.addEqualTo( "password", Utils.digest(loginForm.getPassword()));
		
		AffiliationUser affiliationUser= affiliationUserDAO.getAffiliationUserByCriteria(criteria);

		if( affiliationUser == null){
			errors.add( "login",new ActionError( "errors.login"));
			saveErrors	( request, errors);
			return mapping.getInputForward();
		}
		
		affiliationUser.setLastLogin(today);
		affiliationUserDAO.setAffiliationUser(affiliationUser);
		
		request.getSession().setAttribute( "affiliationUserSession", affiliationUser);
		
		return mapping.findForward( "ok");
	}

}		
