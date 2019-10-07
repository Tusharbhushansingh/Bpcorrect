package com.sentinel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sentinel.constant.ConstantMessage;
import com.sentinel.dao.UsersInformationDao;
import com.sentinel.response.ResponseMessage;

@Service
public class UsersInformationService {

	@Autowired
	private UsersInformationDao usersInformationService;

	public Object getUsersBasicDetails(Integer userId) {
		try {

			Object obj = usersInformationService.getUsersBasicDetails(userId);

			if (obj != null) {

				return new ResponseMessage<>(ConstantMessage.get_users_info,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<>(ConstantMessage.ErrorMessage, false);
	}

}
