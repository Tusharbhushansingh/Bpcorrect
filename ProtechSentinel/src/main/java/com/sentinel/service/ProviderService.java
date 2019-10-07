package com.sentinel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sentinel.constant.ConstantMessage;
import com.sentinel.dao.ProviderDao;
import com.sentinel.response.ResponseMessage;
import com.sentinel.util.Utility;

@Service
public class ProviderService {

	@Autowired
	ProviderDao providerDao;

	Gson gson = Utility.getGsonObject();

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Object getAllPatientByProvider(Integer userId, Integer admin_id) {
		try {

			Object obj = providerDao.getAllPatientByProvider(userId, admin_id);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object applycoupon(String code) {
		try {

			Object obj = providerDao.applycoupon(code);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	// new code
	public Object getCommonstaffByProvider(Integer userId, Integer adminId) {
		try {

			Object obj = providerDao.getCommonstaffByProvider(userId, adminId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ADMINConnectedstaff, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getAllPatientBySuperAdmin(Integer userId, Integer admin_id) {
		try {

			Object obj = providerDao.getAllPatientBySuperAdmin(userId, admin_id);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	// new vode
	
	public Object getCommonPatientBySuperProvider(Integer userId, Integer admin_id) {
		try {

			Object obj = providerDao.getCommonPatientByProvider(userId, admin_id);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	//
	
	public Object addPatientLoghours(Integer userId, Integer admin_id, String inTime, String outTime, Double time, Integer day, Integer month, Integer year) {
		try {

			Object obj = providerDao.addPatientLoghours(userId, admin_id, inTime, outTime, time, day ,month, year);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getBloodPressureByPatient(Integer patientUserId) {
		try {

			Object obj = providerDao.getBloodPressureByPatient(patientUserId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_blood_pressure_by_patient, obj,
						true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}



	public Object getAllProvidersByProvider(Integer userId) {
		try {

			Object obj = providerDao.getAllProvidersByProvider(userId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_all_providers_by_provider_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

}
