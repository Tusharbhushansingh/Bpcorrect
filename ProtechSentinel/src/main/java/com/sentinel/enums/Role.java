package com.sentinel.enums;

public enum Role {

	ROLE_USER("ROLE_USER");
	
	private String role;
	
	private Role(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return role;
	}
	
}
