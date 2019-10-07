package com.sentinel.model.provider.dashboard;

import java.util.List;

import com.sentinel.model.UserModel;

public class IncreasedUser {
	
	UserModel userAccount;
	
	Integer average;
	Integer minimum;
	Integer maximum;
	
	public UserModel getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(UserModel userAccount) {
		this.userAccount = userAccount;
	}
	public Integer getAverage() {
		return average;
	}
	public void setAverage(Integer average) {
		this.average = average;
	}
	public Integer getMinimum() {
		return minimum;
	}
	public void setMinimum(Integer minimum) {
		this.minimum = minimum;
	}
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	
	
	
	
}
