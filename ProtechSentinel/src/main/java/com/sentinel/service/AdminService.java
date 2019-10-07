package com.sentinel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.sentinel.constant.ConstantMessage;
import com.sentinel.dao.AdminDao;
import com.sentinel.dao.ProviderDao;
import com.sentinel.model.HealthGroupDetails;
import com.sentinel.model.PracticeMappingOrg;
import com.sentinel.model.UpdateHealthGroupDetails;
import com.sentinel.model.WearableDevice.AddWearableDevice;
import com.sentinel.model.WearableDevice.UpdateWearableDevice;
import com.sentinel.response.ResponseMessage;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public Object addWearableDevice(AddWearableDevice addWearableDevice) {
		try {

			Object obj = adminDao.addWearableDevice(addWearableDevice);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.add_wearable_device_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updateWearableDevice(UpdateWearableDevice updateWearableDevice) {
		try {

			Object obj = adminDao.updateWearableDevice(updateWearableDevice);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.update_wearable_device_message, obj,
						true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	
	public Object practiceorgmapping(PracticeMappingOrg practiceMappingOrg) {
		try {

			Object obj = adminDao.practiceAdminMap(practiceMappingOrg);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.practice_org_mapping_message, obj,
						true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object mappingPatientWithDevice(Integer patientUserId,
			Integer deviceId, Integer createrUserId) {
		try {

			Object obj = adminDao.mappingPatientWithDevice(patientUserId,
					deviceId, createrUserId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.patient_mapping_wearable_device_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getPatientWearableDeviceByPatientId(Integer userId) {
		try {

			Object obj = adminDao.getPatientWearableDeviceByPatientId(userId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_patient_wearable_device_by_patient_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object addupdatecoupon( Integer coupon_id,  String code,  String startdate,  String enddate,  Integer discount, 
			 Integer status ) {
		try {

			Object obj = adminDao.addupdatecoupon(coupon_id,code,startdate, enddate, discount,status);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_patient_wearable_device_by_patient_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getcoupon( Integer coupon_id , Integer admin_id) {
		try {

			Object obj = adminDao.getcouponList(coupon_id, admin_id);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_patient_wearable_device_by_patient_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addHealthgroupBySentinelAdmin(
			HealthGroupDetails healthGroupDetails) {
		try {

			Object obj = adminDao
					.addHealthgroupBySentinelAdmin(healthGroupDetails);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.add_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object addchargeamount(
			Integer amount) {
		try {

			Object obj = adminDao
					.addchargeamount(amount);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.add_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	
	public Object getchargeamount(
			) {
		try {

			Object obj = adminDao
					.getchargeamount();

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.add_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getallpaymentrecord( Integer id, Integer monthno ,Integer year) {
		try {

			Object obj = adminDao
					.getallpaymentrecord(id, monthno, year);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.add_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object activatedeactivatecoupon(
			Integer id, Integer status) {
		try {

			Object obj = adminDao
					.activatedeactivatecoupon(id, status);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.add_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updateHealthgroupBySentinelAdmin(
			UpdateHealthGroupDetails updateHealthGroupDetails) {
		try {

			Object obj = adminDao
					.updateHealthgroupBySentinelAdmin(updateHealthGroupDetails);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.update_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getHealthgroupBySentinelAdmin(Integer adminuserId) {
		try {

			Object obj = adminDao.getHealthgroupBySentinelAdmin(adminuserId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	// new code for chart
	
	public Object getChartbyAdmin(Integer startdate, Integer enddate) {
		try {

			Object obj = adminDao.getChartbyAdmin(startdate, enddate);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.get_health_group_by_sentinel_admin_message,
						obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
ProviderDao providerDao;

	public Object getAllPracticeAdmin(Integer userId) {
		try {

			Object obj = adminDao.getAllPatientByProvider(userId);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getQuickStats(int userid) {
		try {

			Object obj = adminDao.getquickStats(userid);

			if (obj != null) {
				return new ResponseMessage<>(
						ConstantMessage.ProviderConnectedPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

}
