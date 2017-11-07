package com.madgrid.affiliation.form;

import java.io.Serializable;
import org.apache.struts.validator.ValidatorForm;


public class LoginForm extends ValidatorForm implements Serializable{

	private String login;
	private String password;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
		
}
