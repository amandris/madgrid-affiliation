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

public class CreditsPurchasedListAction extends Action {

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
		criteria.addEqualTo("type", 2);
		
		List<AffiliationActivity> creditsPurchasedList = affiliationActivityDAO.getAffiliationActivityListByCriteriaAndRange( criteria, "-created", (page - 1) *20, 20);
		int creditsPurchasedCount  = affiliationActivityDAO.getAffiliationActivityCountByCriteria( criteria);
		List<RegisteredUserData> creditsPurchasedDataList = new ArrayList<RegisteredUserData>();
		int pageCount  = (int)(Math.floor(((double)creditsPurchasedCount - 1d) /20d)) + 1;
		
		
		for( AffiliationActivity affiliationActivity: creditsPurchasedList){
			RegisteredUserData registeredUserData = new RegisteredUserData();
			registeredUserData.setId( affiliationActivity.getUser().getId().toString());
			registeredUserData.setLogin( affiliationActivity.getUser().getLogin());
			registeredUserData.setAlreadyPayed(affiliationActivity.getAlreadyPayed());
			registeredUserData.setCreated(Utils.getDate( affiliationActivity.getCreated(), 4) + "  " + Utils.getTime( affiliationActivity.getCreated()));
			
			registeredUserData.setTotalBitcoins(affiliationActivity.getTotalBitcoins());
			registeredUserData.setAssignedBitcoins(affiliationActivity.getAssignedBitcoins());
			
			creditsPurchasedDataList.add(registeredUserData);
			
		}
		
		request.setAttribute( "creditsPurchasedDataList", creditsPurchasedDataList);
		request.setAttribute( "pageCount", pageCount);
		request.setAttribute( "creditsPurchasedCount", creditsPurchasedCount);
		request.setAttribute( "page", page);
		
		
		return mapping.findForward( "ok");
	}

}		
