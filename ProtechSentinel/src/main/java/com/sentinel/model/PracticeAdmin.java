package com.sentinel.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itextpdf.text.log.SysoLogger;

public class PracticeAdmin {

	String firstname;
	String middlename;
	String lastname;
	String dob;
	String address1;
	String address2;
	String email;
	String mobile1;
	String mobile2;
	String gender;
	String city;
	String state;
	String country;
	String photo_url;
	String zipcode;
	String password;
	Integer createrUserId;
	Integer issentinel;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public Integer getCreaterUserId() {
		return createrUserId;
	}

	public void setCreaterUserId(Integer createrUserId) {
		this.createrUserId = createrUserId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	public String getPassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(password.length()<0 || password == null) {
			System.out.println("getting in if");
			
			password = passwordEncoder.encode("testpassword");
			return password;
		}
		else {
			System.out.println("getting in else");
			return passwordEncoder.encode(password);
		}
		
	}

	public void setPassword(String password) {
		if(password.length()<0 || password == null) {
			System.out.println("setting in if");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			this.password = passwordEncoder.encode("testpassword");
		}
		else {
			System.out.println("setting in else");
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			this.password = passwordEncoder.encode(password);
			this.password = password;
		}
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getIssentinel() {
		return issentinel;
	}

	public void setIssentinel(Integer issentinel) {
		this.issentinel = issentinel;
	}

}
