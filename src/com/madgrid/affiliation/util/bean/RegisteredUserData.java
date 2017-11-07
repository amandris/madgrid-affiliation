package com.madgrid.affiliation.util.bean;


public class RegisteredUserData{
		
		private String id;
		private String login;
		private String created;
		private Integer payments;
		private Double totalBitcoins;
		private Double assignedBitcoins;
		private Boolean alreadyPayed;
		private Boolean validated;
		private Boolean fraudulent;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public String getCreated() {
			return created;
		}
		public void setCreated(String created) {
			this.created = created;
		}
		public Integer getPayments() {
			return payments;
		}
		public void setPayments(Integer payments) {
			this.payments = payments;
		}
		public Double getTotalBitcoins() {
			return totalBitcoins;
		}
		public void setTotalBitcoins(Double totalBitcoins) {
			this.totalBitcoins = totalBitcoins;
		}
		public Double getAssignedBitcoins() {
			return assignedBitcoins;
		}
		public void setAssignedBitcoins(Double assignedBitcoins) {
			this.assignedBitcoins = assignedBitcoins;
		}
		public Boolean getAlreadyPayed() {
			return alreadyPayed;
		}
		public void setAlreadyPayed(Boolean alreadyPayed) {
			this.alreadyPayed = alreadyPayed;
		}
		public Boolean getValidated() {
			return validated;
		}
		public void setValidated(Boolean validated) {
			this.validated = validated;
		}
		public Boolean getFraudulent() {
			return fraudulent;
		}
		public void setFraudulent(Boolean fraudulent) {
			this.fraudulent = fraudulent;
		}
		
		
		

		
	}
	