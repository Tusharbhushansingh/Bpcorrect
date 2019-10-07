package com.sentinel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.sentinel.dao.UserDao;


public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	UserDao userDao;

	/*
	 * public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
	 * OAuth2Authentication authentication) { // TODO Auto-generated method stub
	 * return accessToken; }
	 */

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
			OAuth2Authentication authentication) {

		User user = (User) authentication.getPrincipal();
		final Map<String, Object> additionalInfo = new HashMap<>();
		String role = userDao.getAuthorityByUserName(user.getUsername());
		additionalInfo.put("role", role);
		additionalInfo.put("id",
				userDao.getIDAuthorityByUserName(user.getUsername()));
		additionalInfo.put("enable",
				userDao.getEnableAuthorityByUserName(user.getUsername()));
		additionalInfo.put("otp", 
				userDao.getOtpByEmail(6));
		((DefaultOAuth2AccessToken) accessToken)
				.setAdditionalInformation(additionalInfo);

		return accessToken;
	}

}
