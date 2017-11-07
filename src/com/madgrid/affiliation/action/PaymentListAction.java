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
import com.madgrid.dao.AffiliationPaymentDAO;
import com.madgrid.model.AffiliationPayment;
import com.madgrid.model.AffiliationUser;

public class PaymentListAction extends Action {

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
		
		
		
		AffiliationPaymentDAO affiliationPaymentDAO = new AffiliationPaymentDAO();
		
		AffiliationUser affiliationUser = (AffiliationUser) request.getSession().getAttribute( "affiliationUserSession");
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
		
		List<AffiliationPayment> affiliationPaymentList = affiliationPaymentDAO.getAffiliationPaymentListByCriteriaAndRange( criteria, "-created", (page - 1) *20, 20);
		int affiliationPaymentCount  = affiliationPaymentDAO.getAffiliationPaymentCountByCriteria( criteria);
		int pageCount  = (int)(Math.floor(((double)affiliationPaymentCount - 1d) /20d)) + 1;
		
		
		request.setAttribute( "affiliationPaymentList", affiliationPaymentList);
		request.setAttribute( "pageCount", pageCount);
		request.setAttribute( "affiliationPaymentCount", affiliationPaymentCount);
		request.setAttribute( "page", page);
		
		
		return mapping.findForward( "ok");
	}

}		
