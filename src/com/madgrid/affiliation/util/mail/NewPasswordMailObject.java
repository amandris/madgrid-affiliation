package com.madgrid.affiliation.util.mail;

public class NewPasswordMailObject implements MailObject {

	private String 	login;
	private String 	baseUrl;
	private String code;
	private String email;
	
	
	public NewPasswordMailObject( String login, String code, String email,  String baseUrl)
	{
		this.login = login;
		this.baseUrl = baseUrl;
		this.code = code;
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
		result.append( "Hi " + login + ". You have request a new password for your Instantri.ch affiliation control panel. Click in the link below to reset your password:");
		result.append( "</td>"); 
		result.append( "</tr>");
		
		result.append( "<tr>");
		result.append( "<td align='center' style='font-family:arial; font-size:25;'>");
		result.append( "<a href='"+baseUrl+"/affiliation/do/passwordReset2?login="+login+"&email="+email+"&code="+code+"'>Reset password</a>");
		result.append( "</td>"); 
		result.append( "</tr>");
		
		result.append( "<tr>");
		result.append( "<td align='center' style='font-family:arial; font-size:20;'>");
		result.append( "You have 24 hours to change it. If you don't click in the link before 24 hours the password will remain as is now. <br/> If you haven't requested a password reseting, please ignore this message." );
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
		
		result.append( login + ".  You have request a new password for your Instantri.ch affiliation control panel. Click in the link below to reset your password:\r\n");
		result.append( baseUrl+"/affiliation/do/passwordReset2?login="+login+"&email="+email+"&code="+code);
		result.append( "You have 24 hours to change it. If you don't click in the link before 24 hours the password will remain as is now. <br/> If you haven't requested a password reseting, please ignore this message.\r\n" );
				
		return result.toString();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
