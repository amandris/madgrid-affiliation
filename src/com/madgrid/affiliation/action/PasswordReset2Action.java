package com.madgrid.affiliation.action;

import java.util.Date;
import java.util.GregorianCalendar;

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

public class PasswordReset2Action extends Action {

	
	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String code = request.getParameter("code");
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		Criteria criteria = new Criteria();
		criteria.addEqualTo("login", login);
		criteria.addEqualTo("email", email);
		criteria.addEqualTo("codeForPasswordRestart", code);
		
		AffiliationUser affiliationUser = affiliationUserDAO.getAffiliationUserByCriteria(criteria);
		
		System.out.println("------el affiliation  user es " + (affiliationUser == null? "NULL" : affiliationUser.getLogin()));
		
		Date today = Utils.today();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime( today);
		calendar.add(GregorianCalendar.HOUR, 24);
		
		if( affiliationUser != null && affiliationUser.getCodeForPasswordRestartCreated().getTime() <= calendar.getTimeInMillis()){
			
			System.out.println("--------------1");
			request.getSession().setAttribute( "affiliationUserSession", affiliationUser);
			return mapping.findForward( "ok");
		} else{
			System.out.println("--------------2");
			return mapping.findForward( "ko");
		}
		
		
	}
	
}		
