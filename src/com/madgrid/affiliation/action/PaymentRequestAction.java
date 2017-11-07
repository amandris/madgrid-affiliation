package com.madgrid.affiliation.action;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ojb.broker.query.Criteria;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.madgrid.affiliation.util.Mail;
import com.madgrid.affiliation.util.Utils;
import com.madgrid.affiliation.util.mail.PaymentRequestMailObject;
import com.madgrid.affiliation.util.mail.PaymentRequestValidationMailObject;
import com.madgrid.dao.AffiliationActivityDAO;
import com.madgrid.dao.AffiliationUserDAO;
import com.madgrid.model.AffiliationActivity;
import com.madgrid.model.AffiliationUser;

public class PaymentRequestAction extends Action {

	public ActionForward execute( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		if( request.getSession().getAttribute( "affiliationUserSession") == null){
			return mapping.findForward( "session");
		}
		
		AffiliationUserDAO affiliationUserDAO = new AffiliationUserDAO();
		AffiliationActivityDAO affiliationActivityDAO = new AffiliationActivityDAO();
		
		AffiliationUser affiliationUser = (AffiliationUser)request.getSession().getAttribute( "affiliationUserSession"); 
		affiliationUser = affiliationUserDAO.getAffiliationUserById(affiliationUser.getId() );
		
		affiliationUser.setAskedForTransfer(true);
		affiliationUser.setPaymentRequestValidated(false);
		affiliationUserDAO.setAffiliationUser(affiliationUser);
		
		request.getSession().setAttribute( "affiliationUserSession", affiliationUser); 
		
		double bitcoins = 0d;
		
		Criteria criteria = new Criteria();
		criteria.addEqualTo("affiliationUserId", affiliationUser.getId());
		
		List<AffiliationActivity> affiliationActivityList = affiliationActivityDAO.getAffiliationActivityListByCriteria( criteria);
		
		request.setAttribute( "affiliationActivityList", affiliationActivityList);
		
		for( AffiliationActivity affiliationActivity:affiliationActivityList){
			if( !affiliationActivity.getAlreadyPayed()){
				bitcoins = bitcoins + affiliationActivity.getAssignedBitcoins();
			}
		}
		
		DecimalFormat numberFormat = new DecimalFormat("#.########");
		
		PaymentRequestMailObject paymentRequestMailObject = new PaymentRequestMailObject( affiliationUser.getLogin(), bitcoins, Utils.getBaseUrl());
		Mail mail = new Mail( "amandris@hotmail.com", "El afiliado de Instantri.ch " +  affiliationUser.getLogin() + " ha solicitado el pago de sus Bitcoins",  paymentRequestMailObject);
		mail.start();
		
		String code = Utils.digestMD5("kasuhy3b39" + affiliationUser.getLogin());
		
		
		PaymentRequestValidationMailObject paymentRequestValidationMailObject = new PaymentRequestValidationMailObject( affiliationUser.getLogin(), bitcoins, affiliationUser.getBitcoinAddress(), code, Utils.getBaseUrl());
		Mail mail2 = new Mail( affiliationUser.getEmail(), numberFormat.format(bitcoins) + " BTC requested. Click in this link to validate the transaction.",  paymentRequestValidationMailObject);
		mail2.start();
	
		request.setAttribute("systemAlert", "You've requested the payment of " + numberFormat.format(bitcoins) + " BTC. You have to click on the link we've sent you by email to validate the transaction.");
		
		return mapping.findForward( "ok");
	}

}		
