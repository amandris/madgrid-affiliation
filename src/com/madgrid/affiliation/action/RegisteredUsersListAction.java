package com.madgrid.affiliation.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.util.Utils;
import com.madgrid.affiliation.util.bean.RegisteredUserData;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationUser;

public class RegisteredUsersListAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		String pageString = request.getParameter("page");
		
		int page = 1;
		
		if( !Utils.nullOrBlank(pageString)){
			try{
				page = Integer.parseInt(pageString);
			}catch (NumberFormatException e) {
				page = 1;
			}
		}
		
		if( page < 1){
			page = 1;
		}
		
		
		
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		
		AffiliationUser affiliationUser = (AffiliationUser) request.getSession().getAttribute( "affiliationUserSession");
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
		criteria.addEqualTo("type", 1);
		
		List<AffiliationActivity> registeredUserList = affiliationActivityDAO.getAffiliationActivityListByCriteriaAndRange( criteria, "-created", (page - 1) *40, 40);
		int registeredUserCount  = affiliationActivityDAO.getAffiliationActivityCountByCriteria( criteria);
		List<RegisteredUserData> registeredUserDataList = new ArrayList<RegisteredUserData>();
		int pageCount  = (int)(Math.floor(((double)registeredUserCount - 1d) /40d)) + 1;
		
		
		for( AffiliationActivity affiliationActivity: registeredUserList){
			if( affiliationActivity != null && affiliationActivity.getUser() != null && affiliationActivity.getUser().getId() != null){
				RegisteredUserData registeredUserData = new RegisteredUserData();
				registeredUserData.setId( affiliationActivity.getUser().getId().toString());
				registeredUserData.setLogin( affiliationActivity.getUser().getLogin());
				registeredUserData.setCreated(Utils.getDate( affiliationActivity.getCreated(), 3) + "  " + Utils.getTime( affiliationActivity.getCreated()));
				
				
				criteria = new Criteria();
				criteria.addEqualTo("userId", affiliationActivity.getUserId());
				criteria.addEqualTo("type", 2);
				List<AffiliationActivity> payments = affiliationActivityDAO.getAffiliationActivityListByCriteria(criteria);
				Double totalBitcoins = 0d;
				Double assignedBitcoins = 0d;
				int count = 0;
				for( AffiliationActivity payment:payments){
					totalBitcoins = totalBitcoins + payment.getTotalBitcoins();
					assignedBitcoins = assignedBitcoins + payment.getAssignedBitcoins();
					count ++;
				}
				registeredUserData.setTotalBitcoins(totalBitcoins);
				registeredUserData.setAssignedBitcoins(assignedBitcoins);
				registeredUserData.setPayments(count);
				registeredUserData.setValidated( affiliationActivity.getUser().getValidated());
				registeredUserData.setFraudulent( affiliationActivity.getUser().getIsFraudulent());
				registeredUserDataList.add(registeredUserData);
			}
		}
		
		request.setAttribute( "registeredUserDataList", registeredUserDataList);
		request.setAttribute( "pageCount", pageCount);
		request.setAttribute( "registeredUserCount", registeredUserCount);
		request.setAttribute( "page", page);
		
		
		return mapping.findForward( "ok");
	}

}		
