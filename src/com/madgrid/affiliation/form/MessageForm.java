package com.madgrid.affiliation.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class MessageForm extends ValidatorForm implements Serializable{

	private String subject;
	private String message;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
}
