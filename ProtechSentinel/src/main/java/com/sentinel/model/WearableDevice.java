package com.sentinel.model;

public class WearableDevice {

	public static class AddWearableDevice {
		private String device_name;
		private String device_type;
		private String device_modal;
		private String device_vendor;
		private String device_description;
		private String device_icon;
		private Integer patientUserId;
		private Integer createrUserId;

		public String getDevice_name() {
			return device_name;
		}

		public void setDevice_name(String device_name) {
			this.device_name = device_name;
		}

		public String getDevice_type() {
			return device_type;
		}

		public void setDevice_type(String device_type) {
			this.device_type = device_type;
		}

		public String getDevice_modal() {
			return device_modal;
		}

		public void setDevice_modal(String device_modal) {
			this.device_modal = device_modal;
		}

		public String getDevice_vendor() {
			return device_vendor;
		}

		public void setDevice_vendor(String device_vendor) {
			this.device_vendor = device_vendor;
		}

		public String getDevice_description() {
			return device_description;
		}

		public void setDevice_description(String device_description) {
			this.device_description = device_description;
		}

		public String getDevice_icon() {
			return device_icon;
		}

		public void setDevice_icon(String device_icon) {
			this.device_icon = device_icon;
		}

		public Integer getPatientUserId() {
			return patientUserId;
		}

		public void setPatientUserId(Integer patientUserId) {
			this.patientUserId = patientUserId;
		}

		public Integer getCreaterUserId() {
			return createrUserId;
		}

		public void setCreaterUserId(Integer createrUserId) {
			this.createrUserId = createrUserId;
		}

	}

	public static class UpdateWearableDevice {
		private String device_name;
		private String device_type;
		private String device_modal;
		private String device_vendor;
		private String device_description;
		private String device_icon;
		private Integer device_id;

		public String getDevice_name() {
			return device_name;
		}

		public void setDevice_name(String device_name) {
			this.device_name = device_name;
		}

		public String getDevice_type() {
			return device_type;
		}

		public void setDevice_type(String device_type) {
			this.device_type = device_type;
		}

		public String getDevice_modal() {
			return device_modal;
		}

		public void setDevice_modal(String device_modal) {
			this.device_modal = device_modal;
		}

		public String getDevice_vendor() {
			return device_vendor;
		}

		public void setDevice_vendor(String device_vendor) {
			this.device_vendor = device_vendor;
		}

		public String getDevice_description() {
			return device_description;
		}

		public void setDevice_description(String device_description) {
			this.device_description = device_description;
		}

		public String getDevice_icon() {
			return device_icon;
		}

		public void setDevice_icon(String device_icon) {
			this.device_icon = device_icon;
		}

		public Integer getDevice_id() {
			return device_id;
		}

		public void setDevice_id(Integer device_id) {
			this.device_id = device_id;
		}

	}

}
