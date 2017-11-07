package com.madgrid.affiliation.util.mail;

import java.text.DecimalFormat;


public class PaymentRequestValidationMailObject implements MailObject {

	private String 	baseUrl; 
	private String 	login;
	private double bitcoins;
	private String address;
	private String code;
	
	
	public PaymentRequestValidationMailObject( String login,  double bitcoins, String address, String code, String baseUrl)
	{
		this.login = login;
		this.baseUrl = baseUrl;
		this.bitcoins = bitcoins;
		this.address = address;
		this.code = code;
	}

	public String toHtml()
	{
		DecimalFormat numberFormat = new DecimalFormat("#.########");
		StringBuffer result = new StringBuffer( "");
		
		result.append( "<html>");
		result.append( "<head>");
		result.append( "</head>");
		result.append( "<body>");
		result.append( "<table width='100%' cellpadding='0' cellspacing='0' border='0'>");
		result.append( "<tr>");
		result.append( "<td align='center'>");
		result.append( "<a href='" + baseUrl + "'><img src='" + baseUrl+ "/img/logo_instantrich_small.jpg' border='0'/></a>");
		result.append( "</td>");
		result.append( "</tr>");
		result.append( "<tr>");
		result.append( "<td>");
		result.append( "<table width='100%' cellpadding='0' cellspacing='15' border='0'>");
		
		result.append( "<tr>");
		result.append( "<td align='center' style='font-family:arial; font-size:20;'>");
		result.append( "Hi "+login+".<br/><br/>");
		
		result.append( "You have requested the payment of the " + numberFormat.format( bitcoins) + " BTC you have earned as an affiliation user in Instantri.ch.<br/><br/>");
		result.append( "For security reasons the paymen won't be performed until you click in the validation link below.<br/><br/>");
		result.append( "<a href='" + baseUrl + "/affiliation/do/paymentRequestValidation?login="+login+"&code="+code+"'>Send " + numberFormat.format( bitcoins) + " BTC to your address " + address + "</a><br/><br/>");
		result.append( "If you didn't request any payment from your affiliation control panel just ignore this email (and perhaps change your password in the <a href='" + baseUrl + "/affiliation'>affiliation control pannel</a>).<br/><br/>");
		
		result.append( "</td>"); 
		result.append( "</tr>");
		
		result.append( "</table>");
		result.append( "</td>");
		result.append( "</tr>");
		result.append( "</table>"); 
		result.append( "</body>");
		result.append( "</html>");
		
		return result.toString();
	}
	
	public String toText()
	{
		DecimalFormat numberFormat = new DecimalFormat("#.########");
		StringBuffer result = new StringBuffer( "");
		
		result.append( "You have requested the payment of the " + numberFormat.format( bitcoins) + " BTC you have earned as an affiliation user in Instantri.ch.\r\n");
		result.append( "For security reasons the paymen won't be performed until you click in the validation link below.\r\n");
		result.append( baseUrl + "/affiliation/do/paymentRequestValidation?login="+login+"&code="+code+"'>Send " + numberFormat.format( bitcoins) + " BTC to your address " + address + "\r\n");
		result.append( "If you didn't request any payment from your affiliation control panel just ignore this email (and perhaps change your password in " + baseUrl + "/affiliation.\r\n");

		
				
		return result.toString();
	}

	

}
