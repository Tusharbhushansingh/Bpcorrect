package com.sentinel.model;

import java.util.HashMap;

public class ProtocolData {
	
String	end_date;
String    device_name;
String    patient_id;
String    datasource;
String    protocol_id;
String    value;
String    measurement_service;
String    start_date;
String    timestamp;
String[]  datasource_array;
String[] value_array;
HashMap<String, String> readings;



public String getEnd_date() {
	return end_date;
}
public void setEnd_date(String end_date) {
	this.end_date = end_date;
}
public String getDevice_name() {
	return device_name;
}
public void setDevice_name(String device_name) {
	this.device_name = device_name;
}
public String getPatient_id() {
	return patient_id;
}
public void setPatient_id(String patient_id) {
	this.patient_id = patient_id;
}
/*public String[] getDatasource() {
	return datasource_array;
}*/
public void setDatasource(String datasource) {
	this.datasource_array = datasource.split(",");
	this.datasource = datasource;
}
public String getProtocol_id() {
	return protocol_id;
}
public void setProtocol_id(String protocol_id) {
	this.protocol_id = protocol_id;
}
/*public String[] getValue() {
	return value_array;
}*/
public void setValue(String value) {
	this.value_array = value.split(",");
	this.value = value;
}
public String getMeasurement_service() {
	return measurement_service;
}
public void setMeasurement_service(String measurement_service) {
	this.measurement_service = measurement_service;
}
public String getStart_date() {
	return start_date;
}
public void setStart_date(String start_date) {
	this.start_date = start_date;
}
public String getTimestamp() {
	return timestamp;
}
public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}

public void set_key_value(String key, String value) {
	String o_key[] = key.split(",");
	String o_value[] = value.split(",");
	HashMap< String, String> key_value = new HashMap<>();
	for(int i=0;i<o_key.length;i++) {
		key_value.put(o_key[i], o_value[i]);
	}
	this.readings = key_value;
}

public HashMap<String, String> get_key_value() {
     return readings;
}



}
