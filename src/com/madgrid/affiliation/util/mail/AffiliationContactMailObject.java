package com.madgrid.affiliation.util.mail;

public class AffiliationContactMailObject implements MailObject {

	private String 	baseUrl;
	private String 	login;
	private String 	email;
	private String 	message;
	private String 	subject;
	
	
	public AffiliationContactMailObject( String login, String email, String message, String subject, String baseUrl)
	{
		this.message = message;
		this.subject = subject;
		this.baseUrl = baseUrl;
		this.login = login;
		this.email = email;
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
		result.append( "Gustavo, un afiliado de Instantri.ch te ha mandado un mensaje. Su login es " + login + " y su email es " + email);
		result.append( "</td>"); 
		result.append( "</tr>");
		
		result.append( "<tr>");
		result.append( "<td align='center' style='font-family:arial; font-size:20;'>");
		result.append( "Asunto: " + subject);
		result.append( "</td>"); 
		result.append( "</tr>");
		
		result.append( "<tr>");
		result.append( "<td align='center' style='font-family:arial; font-size:20;'>");
		result.append( "Mensaje: " + message);
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
		result.append( "Gustavo, un afiliado de Instantri.ch te ha mandado un mensaje.\r\n");
		result.append( subject+"\r\n");
		result.append( message+"\r\n");
				
		return result.toString();
	}

	

}
