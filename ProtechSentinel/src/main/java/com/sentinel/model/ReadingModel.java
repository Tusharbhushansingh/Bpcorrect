package com.sentinel.model;

public class ReadingModel {
	
	 String patientId;
	 String device_id;
	 String systolic;
	 String diastolic;
	 String pulse_data;
	 String reading_time;
	 String device_name;
	 String device_mac_address;
	 String protocol_id;
	 String is_abberant;
	 String protocol_no;
	 
	
	public String getProtocol_no() {
		return protocol_no;
	}
	public void setProtocol_no(String protocol_no) {
		this.protocol_no = protocol_no;
	}
	public String getDevice_name() {
		return device_name;
	}
	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}
	public String getDevice_mac_address() {
		return device_mac_address;
	}
	public void setDevice_mac_address(String device_mac_address) {
		this.device_mac_address = device_mac_address;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getSystolic() {
		return systolic;
	}
	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}
	public String getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}
	public String getPulse_data() {
		return pulse_data;
	}
	public void setPulse_data(String pulse_data) {
		this.pulse_data = pulse_data;
	}
	public String getReading_time() {
		return reading_time;
	}
	public void setReading_time(String reading_time) {
		this.reading_time = reading_time;
	}
	public String getProtocol_id() {
		return protocol_id;
	}
	public void setProtocol_id(String protocol_id) {
		this.protocol_id = protocol_id;
	}
	public String getIs_abberant() {
		return is_abberant;
	}
	public void setIs_abberant(String is_abberant) {
		this.is_abberant = is_abberant;
	}
}
