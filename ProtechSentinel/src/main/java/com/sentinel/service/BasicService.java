package com.sentinel.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.stereotype.Service;

import com.sentinel.constant.ConstantMessage;
import com.sentinel.dao.BasicDao;
import com.sentinel.response.ResponseMessage;

@Service
public class BasicService {

	@Autowired
	private BasicDao basicDao;

	@Autowired
	private DefaultTokenServices tokenServices;

	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	public Object forgotPassword(String email) {
		try {

			Object obj = basicDao.forgotPassword(email);

			if (obj != null) {
				ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) obj;
				return new ResponseMessage<>(
						ConstantMessage.forgot_password_message, obj, true);
				//return new ResponseEntity<>(data.get(0),HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object verifyEmail(String email) {
		try {

			Object obj = basicDao.verifyEmail(email);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.verify_email_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object emailExists(String email) {
		try {

			Object obj = basicDao.emailExists(email);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.verify_email_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object Logout(String access_token) {

		boolean b = tokenServices.revokeToken(access_token);

		if (b) {
			return new ResponseMessage<>(ConstantMessage.verify_email_message,
					b);
		}
		return new ResponseMessage<>(ConstantMessage.ErrorMessage, b);
	}

	public Object resetPassword(String code, String password) {
		try {

			Object obj = basicDao.resetPassword(code,
					bcryptEncoder.encode(password));

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.reset_password_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

}
