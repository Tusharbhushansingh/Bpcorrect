package com.sentinel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.sentinel.constant.ConstantMessage;
import com.sentinel.dao.ProviderDao;
import com.sentinel.response.ResponseMessage;
import com.sentinel.util.Utility;

@Service
public class PracticeAdminService {
	@Autowired
	ProviderDao providerDao;

	Gson gson = Utility.getGsonObject();
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	// new code new file
	public Object getAllProviderByAdmin(Integer userId, Integer adminId) {
		try {

			Object obj = providerDao.getAllProviderByAdmin(userId, adminId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ADMINConnectedPROVIDERS, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	// new code for chart
	// new code new file
		public Object getPracticeAdminChart( Integer userId, Integer startdate, Integer enddate) {
			try {

				Object obj = providerDao.getPracticeAdminChart(userId, startdate, enddate);

				if (obj != null) {
					return new ResponseMessage<>(
							ConstantMessage.ADMINConnectedPROVIDERS, obj, true);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		}
	
	// new code
	public Object getAllstaffByAdmin(Integer userId, Integer adminId) {
		try {

			Object obj = providerDao.getAllstaffByAdmin(userId, adminId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ADMINConnectedstaff, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	//  new code
	


}
