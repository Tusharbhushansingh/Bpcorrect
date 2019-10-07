package com.sentinel.model.provider.dashboard;

import com.sentinel.model.UserModel;

public class UserMedicinePercents {
	
	UserModel userAccount;
	Integer percent;
	
	public UserModel getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserModel userAccount) {
		this.userAccount = userAccount;
	}
	public Integer getPercent() {
		return percent;
	}
	public void setPercent(Integer percent) {
		this.percent = percent;
	}
	
	
}
