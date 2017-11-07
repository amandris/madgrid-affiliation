package com.madgrid.affiliation.util.mail;

public class PaymentRequestMailObject implements MailObject {

	private String 	baseUrl; 
	private String 	login;
	private Double	bitcoins;
	
	
	public PaymentRequestMailObject( String login, Double bitcoins, String baseUrl)
	{
		this.login = login;
		this.bitcoins = bitcoins;
		this.baseUrl = baseUrl;
	}

	public String toHtml()
	{
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
		result.append( "Gustavo, el afiliado de Instantri.ch " + login + " ha solicitado el pago de sus Bitcoins.");
		result.append( "</td>"); 
		result.append( "</tr>");
		
		result.append( "<tr>");
		result.append( "<td align='center' style='font-family:arial; font-size:20;'>");
		result.append( "Cantidad a pagar: " + bitcoins);
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
		StringBuffer result = new StringBuffer( "");
		result.append( "Gustavo, el afiliado de Instantri.ch " + login + " ha solicitado el pago de sus Bitcoins.\r\n");
		result.append( "Cantidad a pagar: " + bitcoins + "\r\n");
				
		return result.toString();
	}

	

}
