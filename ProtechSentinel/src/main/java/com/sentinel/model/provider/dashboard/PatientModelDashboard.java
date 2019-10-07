package com.sentinel.model.provider.dashboard;

import com.sentinel.model.UserFullModel;

public class PatientModelDashboard {
	
	UserFullModel user;
	BloodPresure bloodPresure;
	PulseOximmeter pulseOximmeter;
	Pulse pulse;
	Clinic clinic;
	Info<BloodPresure> infoBloodPressure;
	Info<PulseOximmeter> infoPulseOximmeter;
	Info<Pulse> infoPulse;
	
	
	
	public UserFullModel getUser() {
		return user;
	}



	public void setUser(UserFullModel user) {
		this.user = user;
	}



	public BloodPresure getBloodPresure() {
		return bloodPresure;
	}



	public void setBloodPresure(BloodPresure bloodPresure) {
		this.bloodPresure = bloodPresure;
	}



	public PulseOximmeter getPulseOximmeter() {
		return pulseOximmeter;
	}



	public void setPulseOximmeter(PulseOximmeter pulseOximmeter) {
		this.pulseOximmeter = pulseOximmeter;
	}



	public Pulse getPulse() {
		return pulse;
	}



	public void setPulse(Pulse pulse) {
		this.pulse = pulse;
	}



	public Clinic getClinic() {
		return clinic;
	}



	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}



	public Info<BloodPresure> getInfoBloodPressure() {
		return infoBloodPressure;
	}



	public void setInfoBloodPressure(Info<BloodPresure> infoBloodPressure) {
		this.infoBloodPressure = infoBloodPressure;
	}



	public Info<PulseOximmeter> getInfoPulseOximmeter() {
		return infoPulseOximmeter;
	}



	public void setInfoPulseOximmeter(Info<PulseOximmeter> infoPulseOximmeter) {
		this.infoPulseOximmeter = infoPulseOximmeter;
	}



	public Info<Pulse> getInfoPulse() {
		return infoPulse;
	}



	public void setInfoPulse(Info<Pulse> infoPulse) {
		this.infoPulse = infoPulse;
	}



	public class Info<T>{
		T morning;
		T afternoon;
		T evening;
		public T getMorning() {
			return morning;
		}
		public void setMorning(T morning) {
			this.morning = morning;
		}
		public T getAfternoon() {
			return afternoon;
		}
		public void setAfternoon(T afternoon) {
			this.afternoon = afternoon;
		}
		public T getEvening() {
			return evening;
		}
		public void setEvening(T evening) {
			this.evening = evening;
		}
		
		
	}
}
