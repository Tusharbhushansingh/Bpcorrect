package com.sentinel.model;

public class HealthGroupDetails {

	private String healthorgname;
	private String location;
	private String zipcode;
	private String mobileno;
	private String contact;
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	private Integer adminuserId;

	public String getHealthorgname() {
		return healthorgname;
	}

	public void setHealthorgname(String healthorgname) {
		this.healthorgname = healthorgname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getAdminuserId() {
		return adminuserId;
	}

	public void setAdminuserId(Integer adminuserId) {
		this.adminuserId = adminuserId;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

}
