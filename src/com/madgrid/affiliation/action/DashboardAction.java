package com.madgrid.affiliation.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationPaymentDAO;
import com.madgrid.dao.AffiliationUserMessageDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationUser;

public class DashboardAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUser affiliationUser = (AffiliationUser) request.getSession().getAttribute( "affiliationUserSession");
		
		
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		AffiliationPaymentDAO affiliationPaymentDAO = new AffiliationPaymentDAO();
		AffiliationUserMessageDAO affiliationUserMessageDAO = new AffiliationUserMessageDAO();
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
		criteria.addEqualTo("type", 1);
	
		Integer registeredUsersCount = affiliationActivityDAO.getAffiliationActivityCountByCriteria(criteria);
		
		
		criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
		criteria.addEqualTo("type", 2);
	
		List<AffiliationActivity> creditPurchasesList = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
		double bitcoinsPayed = 0;
		double bitcoinsPending = 0;
		int creditPurchasesCount = 0;
		for( AffiliationActivity creditPurchase:creditPurchasesList){
			if( creditPurchase.getAlreadyPayed()){
				bitcoinsPayed = bitcoinsPayed + creditPurchase.getAssignedBitcoins();
			} else{
				bitcoinsPending = bitcoinsPending + creditPurchase.getAssignedBitcoins();
			}
			creditPurchasesCount ++;
		}
		
		criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
	
		Integer affiliationPaymentCount = affiliationPaymentDAO.getAffiliationPaymentCountByCriteria(criteria);
		
		
		criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
		criteria.addEqualTo("isRead", false);
		Integer affiliationMessagesReceivedCount = affiliationUserMessageDAO.getAffiliationUserMessageCountByCriteria(criteria);
		
		
		request.setAttribute( "bitcoinsPayed", bitcoinsPayed);
		request.setAttribute( "bitcoinsPending", bitcoinsPending);
		request.setAttribute( "registeredUsersCount", registeredUsersCount);
		request.setAttribute( "creditPurchasesCount", creditPurchasesCount);
		request.setAttribute( "affiliationPaymentCount", affiliationPaymentCount);
		request.setAttribute( "affiliationMessagesReceivedCount", affiliationMessagesReceivedCount);
		return mapping.findForward( "ok");
	}

}		
