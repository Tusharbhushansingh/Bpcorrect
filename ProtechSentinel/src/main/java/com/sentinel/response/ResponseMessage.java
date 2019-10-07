package com.sentinel.response;

import com.sentinel.enums.Status;

public class ResponseMessage<T> {

	private String message;

	private T data;

	private boolean valid;

	public ResponseMessage() {

	}

	public ResponseMessage(String message, T data, boolean valid) {
		super();
		this.message = message;
		this.data = data;
		this.valid = valid;
	}

	public ResponseMessage(String message, boolean valid) {
		this.message = message;
		this.valid = valid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

}
