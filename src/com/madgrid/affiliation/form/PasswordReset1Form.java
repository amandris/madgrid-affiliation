package com.madgrid.affiliation.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class PasswordReset1Form extends ValidatorForm implements Serializable{

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	
}
