package com.sentinel.model.provider.dashboard;

import java.util.List;

public class Clinic {

	String homeClinic;
	String onBoarded;
	List<Provider> provider;
	String caregiverModal;
	
	public String getHomeClinic() {
		return homeClinic;
	}



	public void setHomeClinic(String homeClinic) {
		this.homeClinic = homeClinic;
	}



	public String getOnBoarded() {
		return onBoarded;
	}



	public void setOnBoarded(String onBoarded) {
		this.onBoarded = onBoarded;
	}



	public List<Provider> getProvider() {
		return provider;
	}



	public void setProvider(List<Provider> provider) {
		this.provider = provider;
	}



	public String getCaregiverModal() {
		return caregiverModal;
	}



	public void setCaregiverModal(String caregiverModal) {
		this.caregiverModal = caregiverModal;
	}



	public class Provider{
		String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
