package com.madgrid.affiliation.util;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.madgrid.affiliation.util.mail.MailObject;

public class Mail extends Thread{

	private String email;
	private String title;
	private MailObject mailObject;
	
	public Mail(String email, String title, MailObject mailObject){
		this.email = email;
		this.title = title;
		this.mailObject = mailObject;
	}

	public void run() {
		 sendMail(email, title, mailObject);
	}
	
	
	public synchronized boolean sendMail( String email, String title, MailObject mailObject)
	{
        final String  	from  			= ConfigurationParameterManager.getParameterString( "madgridEmail");
        final String  	host  			= ConfigurationParameterManager.getParameterString( "smptHost");
        final String  	mailUser  		= ConfigurationParameterManager.getParameterString( "mailUser");
        final String  	mailPassword  	= ConfigurationParameterManager.getParameterString( "mailPassword");
        Properties 		properties 		= new Properties();

        properties.put( "mail.smtp.host", host);
        properties.put( "mail.smtp.port", ConfigurationParameterManager.getParameterString( "smptPort"));
        properties.put( "mail.smtp.starttls.enable", "false");
        properties.put( "mail.smtp.auth", "true");
        properties.put( "mail.user", mailUser);
        properties.put( "mail.password", mailPassword);
        
        Session session = Session.getInstance( properties, new Authenticator (  )   {  
	       public PasswordAuthentication  
	          getPasswordAuthentication (  )   {  
	            return new PasswordAuthentication ( mailUser, mailPassword ) ; 
	        }  
	    }); 

        try {
            MimeMessage 		message 	= new MimeMessage( session);
            InternetAddress  	fromAddress = new InternetAddress( from, ConfigurationParameterManager.getParameterString( "mailSender"));
            InternetAddress  	toAddress1 	= null;
            Multipart			multipart	= null;
            MimeBodyPart 		bodyPart1 	= new MimeBodyPart();
            MimeBodyPart 		bodyPart2 	= new MimeBodyPart();

            message.setFrom( fromAddress);
            
            toAddress1 		= new InternetAddress( email, "");
            InternetAddress[] address = { toAddress1};
            
            message.setRecipients	( Message.RecipientType.TO, address);
            
            message.setSubject		( ( title != null ? title : ""));
            message.setSentDate		( Utils.today());

            bodyPart1.setText			( mailObject.toText());
            bodyPart2.setDataHandler	( new DataHandler( new ByteArrayDataSource( mailObject.toHtml(), "text/html")));
            
            multipart = new MimeMultipart( "alternative");
            multipart.addBodyPart( bodyPart1);
            multipart.addBodyPart( bodyPart2);
            
            message.setContent		( multipart);
            Transport t = session.getTransport("smtp");
            t.connect( mailUser,mailPassword);
            t.sendMessage( message,message.getAllRecipients());
            t.close();
            
        } catch ( Exception e) {
        		e.printStackTrace();
            return false;
        }
        return true;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public MailObject getMailObject() {
		return mailObject;
	}

	public void setMailObject(MailObject mailObject) {
		this.mailObject = mailObject;
	}
}		
