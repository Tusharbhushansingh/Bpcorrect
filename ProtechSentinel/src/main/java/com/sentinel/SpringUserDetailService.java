package com.sentinel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;

/*import com.pervez.domain.User;
import com.pervez.user.UserDao;
import com.pervez.user.UserService;*/

public class SpringUserDetailService extends ClientDetailsUserDetailsService{

	public SpringUserDetailService(ClientDetailsService clientDetailsService) {
		super(clientDetailsService);
	}
	
	/*@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	ClientDetailsService clientDetailsService;
	private String emptyPassword = "";
	
	public SpringUserDetailService(ClientDetailsService clientDetailsService) {
		super(clientDetailsService);
		this.clientDetailsService = clientDetailsService;

	}

	

	@Override
	public SpringUser loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("UserService: username: "+username);
		
		//super.loadUserByUsername(username);
		
		User user = userDao.getUserByMobileNumber(username);
		
		ClientDetails clientDetails;
		try {
			clientDetails = clientDetailsService.loadClientByClientId(username);
		} catch (NoSuchClientException e) {
			throw new UsernameNotFoundException(e.getMessage(), e);
		}
		String clientSecret = clientDetails.getClientSecret();
		
		if (clientSecret== null || clientSecret.trim().length()==0) {
			clientSecret = emptyPassword;
		}
		
		
		return new SpringUser(username, clientSecret, clientDetails.getAuthorities(), user);
		

	}*/
	
	

}
