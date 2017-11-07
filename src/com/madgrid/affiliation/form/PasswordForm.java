package com.madgrid.affiliation.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class PasswordForm extends ValidatorForm implements Serializable{

	private String previousPassword;
	private String password;
	private String repeatPassword;
	
	

	public String getPreviousPassword() {
		return previousPassword;
	}
	public void setPreviousPassword(String previousPassword) {
		this.previousPassword = previousPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRepeatPassword() {
		return repeatPassword;
	}
	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}
	
	
}
