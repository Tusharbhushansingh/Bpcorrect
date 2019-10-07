package com.sentinel.swagger.model;

import com.sentinel.model.PatientModel;
import com.sentinel.response.ResponseMessage;

public class ResponseObject {

	public static final class GetAllpatient extends
			ResponseMessage<PatientModel> {
	}

	public static final class GetUserInfo {
		Integer userId;
		String firstname;
		String middlename;
		String lastname;
		Integer dob;
		String address1;
		String address2;
		String email;
		String mobile1;
		String mobile2;
		String accessRole;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

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

		public Integer getDob() {
			return dob;
		}

		public void setDob(Integer dob) {
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

		public String getAccessRole() {
			return accessRole;
		}

		public void setAccessRole(String accessRole) {
			this.accessRole = accessRole;
		}

	}

	public static final class GetPatientInfo {
		Integer userId;
		String firstname;
		String middlename;
		String lastname;
		Integer dob;
		String address1;
		String address2;
		String email;
		String mobile1;
		String mobile2;
		Integer patientId;

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

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

		public Integer getDob() {
			return dob;
		}

		public void setDob(Integer dob) {
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

		public Integer getPatientId() {
			return patientId;
		}

		public void setPatientId(Integer patientId) {
			this.patientId = patientId;
		}

	}

	public static final class GetHealthGroupDetailsByPatient {
		String health_org_Id;
		String health_org_name;
		String health_org_address;
		String health_org_city;
		String health_org_country;
		String health_org_region;
		String health_org_group;
		String health_org_mobile;
		String health_org_phone;
		String user_userId;

		public String getHealth_org_Id() {
			return health_org_Id;
		}

		public void setHealth_org_Id(String health_org_Id) {
			this.health_org_Id = health_org_Id;
		}

		public String getHealth_org_name() {
			return health_org_name;
		}

		public void setHealth_org_name(String health_org_name) {
			this.health_org_name = health_org_name;
		}

		public String getHealth_org_address() {
			return health_org_address;
		}

		public void setHealth_org_address(String health_org_address) {
			this.health_org_address = health_org_address;
		}

		public String getHealth_org_city() {
			return health_org_city;
		}

		public void setHealth_org_city(String health_org_city) {
			this.health_org_city = health_org_city;
		}

		public String getHealth_org_country() {
			return health_org_country;
		}

		public void setHealth_org_country(String health_org_country) {
			this.health_org_country = health_org_country;
		}

		public String getHealth_org_region() {
			return health_org_region;
		}

		public void setHealth_org_region(String health_org_region) {
			this.health_org_region = health_org_region;
		}

		public String getHealth_org_group() {
			return health_org_group;
		}

		public void setHealth_org_group(String health_org_group) {
			this.health_org_group = health_org_group;
		}

		public String getHealth_org_mobile() {
			return health_org_mobile;
		}

		public void setHealth_org_mobile(String health_org_mobile) {
			this.health_org_mobile = health_org_mobile;
		}

		public String getHealth_org_phone() {
			return health_org_phone;
		}

		public void setHealth_org_phone(String health_org_phone) {
			this.health_org_phone = health_org_phone;
		}

		public String getUser_userId() {
			return user_userId;
		}

		public void setUser_userId(String user_userId) {
			this.user_userId = user_userId;
		}

	}

	public static final class GetVitalLastInfoByPatient {
		String bp;
		String height;
		String weight;
		String spo2;
		String pi;
		String pulse;

		public String getBp() {
			return bp;
		}

		public void setBp(String bp) {
			this.bp = bp;
		}

		public String getHeight() {
			return height;
		}

		public void setHeight(String height) {
			this.height = height;
		}

		public String getWeight() {
			return weight;
		}

		public void setWeight(String weight) {
			this.weight = weight;
		}

		public String getSpo2() {
			return spo2;
		}

		public void setSpo2(String spo2) {
			this.spo2 = spo2;
		}

		public String getPi() {
			return pi;
		}

		public void setPi(String pi) {
			this.pi = pi;
		}

		public String getPulse() {
			return pulse;
		}

		public void setPulse(String pulse) {
			this.pulse = pulse;
		}

	}

	public static final class GetBloodPressureByPatient {
		String time;
		String sbp;
		String dbp;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getSbp() {
			return sbp;
		}

		public void setSbp(String sbp) {
			this.sbp = sbp;
		}

		public String getDbp() {
			return dbp;
		}

		public void setDbp(String dbp) {
			this.dbp = dbp;
		}

	}

	public static final class GetPulseOxByPatient {
		String time;
		String sp02;
		String pi;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getSp02() {
			return sp02;
		}

		public void setSp02(String sp02) {
			this.sp02 = sp02;
		}

		public String getPi() {
			return pi;
		}

		public void setPi(String pi) {
			this.pi = pi;
		}

	}

	public static final class GetPulseByPatient {
		String time;
		String BPM;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getBPM() {
			return BPM;
		}

		public void setBPM(String bPM) {
			BPM = bPM;
		}

	}

	public static class getProviderInfo {
		private Integer provider_userId;
		private String provider_firstname;
		private String provider_middlename;
		private String provider_lastname;
		private String provider_dob;
		private String provider_address1;
		private String provider_address2;
		private String provider_email;
		private String provider_mobile1;
		private String provider_mobile2;
		private String provider_accessRole;
		private String provider_providertype;
		private String provider_gender;
		private String provider_city;
		private String provider_state;
		private String provider_country;
		private String provider_zipcode;
		private String provider_photo_url;

		public Integer getProvider_userId() {
			return provider_userId;
		}

		public void setProvider_userId(Integer provider_userId) {
			this.provider_userId = provider_userId;
		}

		public String getProvider_firstname() {
			return provider_firstname;
		}

		public void setProvider_firstname(String provider_firstname) {
			this.provider_firstname = provider_firstname;
		}

		public String getProvider_middlename() {
			return provider_middlename;
		}

		public void setProvider_middlename(String provider_middlename) {
			this.provider_middlename = provider_middlename;
		}

		public String getProvider_lastname() {
			return provider_lastname;
		}

		public void setProvider_lastname(String provider_lastname) {
			this.provider_lastname = provider_lastname;
		}

		public String getProvider_dob() {
			return provider_dob;
		}

		public void setProvider_dob(String provider_dob) {
			this.provider_dob = provider_dob;
		}

		public String getProvider_address1() {
			return provider_address1;
		}

		public void setProvider_address1(String provider_address1) {
			this.provider_address1 = provider_address1;
		}

		public String getProvider_address2() {
			return provider_address2;
		}

		public void setProvider_address2(String provider_address2) {
			this.provider_address2 = provider_address2;
		}

		public String getProvider_email() {
			return provider_email;
		}

		public void setProvider_email(String provider_email) {
			this.provider_email = provider_email;
		}

		public String getProvider_mobile1() {
			return provider_mobile1;
		}

		public void setProvider_mobile1(String provider_mobile1) {
			this.provider_mobile1 = provider_mobile1;
		}

		public String getProvider_mobile2() {
			return provider_mobile2;
		}

		public void setProvider_mobile2(String provider_mobile2) {
			this.provider_mobile2 = provider_mobile2;
		}

		public String getProvider_accessRole() {
			return provider_accessRole;
		}

		public void setProvider_accessRole(String provider_accessRole) {
			this.provider_accessRole = provider_accessRole;
		}

		public String getProvider_providertype() {
			return provider_providertype;
		}

		public void setProvider_providertype(String provider_providertype) {
			this.provider_providertype = provider_providertype;
		}

		public String getProvider_gender() {
			return provider_gender;
		}

		public void setProvider_gender(String provider_gender) {
			this.provider_gender = provider_gender;
		}

		public String getProvider_city() {
			return provider_city;
		}

		public void setProvider_city(String provider_city) {
			this.provider_city = provider_city;
		}

		public String getProvider_state() {
			return provider_state;
		}

		public void setProvider_state(String provider_state) {
			this.provider_state = provider_state;
		}

		public String getProvider_country() {
			return provider_country;
		}

		public void setProvider_country(String provider_country) {
			this.provider_country = provider_country;
		}

		public String getProvider_zipcode() {
			return provider_zipcode;
		}

		public void setProvider_zipcode(String provider_zipcode) {
			this.provider_zipcode = provider_zipcode;
		}

		public String getProvider_photo_url() {
			return provider_photo_url;
		}

		public void setProvider_photo_url(String provider_photo_url) {
			this.provider_photo_url = provider_photo_url;
		}

	}

}
