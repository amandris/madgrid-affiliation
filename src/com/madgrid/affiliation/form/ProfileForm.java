package com.madgrid.affiliation.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorForm;

public class ProfileForm extends ValidatorForm implements Serializable{

	private Integer id;
	private String login;
	private String url;
	private String bitcoinAddress;
	private String email;
	private Double percentage;
	private boolean askedForTransfer;
	private boolean sendEmailAlerts;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}


	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getBitcoinAddress() {
		return bitcoinAddress;
	}
	public void setBitcoinAddress(String bitcoinAddress) {
		this.bitcoinAddress = bitcoinAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getPercentage() {
		return percentage;
	}
	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}
	public boolean isAskedForTransfer() {
		return askedForTransfer;
	}
	public void setAskedForTransfer(boolean askedForTransfer) {
		this.askedForTransfer = askedForTransfer;
	}
	public boolean getSendEmailAlerts() {
		return sendEmailAlerts;
	}
	public void setSendEmailAlerts(boolean sendEmailAlerts) {
		this.sendEmailAlerts = sendEmailAlerts;
	}
	
	
	
	
	
	
}
