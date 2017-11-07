package com.madgrid.affiliation.util.mail;


public class AddCreditsByAdminMailObject implements MailObject {

	private String 	baseUrl; 
	private String 	login;
	
	
	public AddCreditsByAdminMailObject( String login,  String baseUrl)
	{
		this.login = login;
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
		result.append( "Hi "+login+".<br/><br/>");
		
		
		result.append( "We've added 1 credit to your account as a reward for log in our new affiliation control panel.<br/><br/>");
		result.append( "You can use that credit right now in any game you want.<br/><br/>");
		result.append( "Good luck!");
		
		
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
		
		
			result.append( "We've added 1 credit to your account as a reward for log in our new affiliation control panel.\r\n");
			result.append( "You can use that credit right now in any game you want.\r\n");
			result.append( "Good luck!");
		
				
		return result.toString();
	}

	

}
