package com.sentinel.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sentinel.constant.CustomQuery;
import com.sentinel.generic.dao.GenericDao;
import com.sentinel.model.HealthGroupDetails;
import com.sentinel.model.PracticeMappingOrg;
import com.sentinel.model.UpdateHealthGroupDetails;
import com.sentinel.model.WearableDevice.AddWearableDevice;
import com.sentinel.model.WearableDevice.UpdateWearableDevice;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
@Transactional
public class AdminDao extends GenericDao {

	public Object addWearableDevice(AddWearableDevice addWearableDevice) {
		try {

			Object values[] = { addWearableDevice.getDevice_name(),
					addWearableDevice.getDevice_type(),
					addWearableDevice.getDevice_modal(),
					addWearableDevice.getDevice_vendor(),
					addWearableDevice.getDevice_description(),
					addWearableDevice.getDevice_icon(),
					addWearableDevice.getPatientUserId(),
					addWearableDevice.getCreaterUserId() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.add_wearable_device, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateWearableDevice(UpdateWearableDevice updateWearableDevice) {
		try {

			Object values[] = { updateWearableDevice.getDevice_name(),
					updateWearableDevice.getDevice_type(),
					updateWearableDevice.getDevice_modal(),
					updateWearableDevice.getDevice_vendor(),
					updateWearableDevice.getDevice_description(),
					updateWearableDevice.getDevice_icon(),
					updateWearableDevice.getDevice_id() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.update_wearable_device, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object practiceAdminMap(PracticeMappingOrg practiceMappingOrg) {
		try {

			Object values[] = {
					 practiceMappingOrg.getUserId(),
					 practiceMappingOrg.getHealthId()
					 };

			Session session = getSession();
			Object obj = executemappingStoreProcedure(session, null,
					CustomQuery.Admin.practice_org_mapping, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object mappingPatientWithDevice(Integer patientUserId,
			Integer deviceId, Integer createrUserId) {
		try {

			Object values[] = { patientUserId, deviceId, createrUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.patient_mapping_wearable_device, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getPatientWearableDeviceByPatientId(Integer userId) {
		try {

			Object values[] = { userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.get_patient_wearable_device_by_patient,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object addupdatecoupon(Integer coupon_id,  String code,  String startdate,  String enddate,  Integer discount, 
			 Integer status) {
		try {

			Object values[] = { coupon_id,code,startdate, enddate, discount,status };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.add_update_coupon_code,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getcouponList(Integer coupon_id, Integer admin_id) {
		try {
			Object values[] = { coupon_id, admin_id };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.get_coupon_list,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object addHealthgroupBySentinelAdmin(HealthGroupDetails modal) {
		try {

			Object values[] = { modal.getHealthorgname(), modal.getLocation(),modal.getContact(),
					modal.getZipcode(), modal.getMobileno(),
					modal.getAdminuserId(), "ROLE_SUPERADMIN" };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.add_health_group_by_sentinel_admin,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object addchargeamount(Integer amount) {
		try {

			Object values[] = {amount  };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.add_charge_amount,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getchargeamount() {
		try {

			Object values[] = {  };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.get_charge_amount,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getallpaymentrecord(Integer id, Integer monthno, Integer year) {
		try {

			Object values[] = { id, monthno, year};
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.get_all_payment_record,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Object activatedeactivatecoupon(Integer id, Integer status) {
		try {

			Object values[] = { id, status };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.activate_deactivate_coupon,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object updateHealthgroupBySentinelAdmin(
			UpdateHealthGroupDetails modal) {
		try {

			Object values[] = { modal.getHealthorgname(), modal.getLocation(),
					modal.getZipcode(), modal.getMobileno(),
					modal.getAdminuserId(), modal.getHealthgroupId(),
					"ROLE_SUPERADMIN" };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.update_health_group_by_sentinel_admin,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getHealthgroupBySentinelAdmin(Integer adminuserId) {
		try {

			Object values[] = { adminuserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.get_health_group_by_sentinel_admin,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}


	// new code for chart
	
	public Object getChartbyAdmin(Integer startdate, Integer enddate) {
		try {

			Object values[] = { startdate, enddate };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.get_chart_data,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object addMasterMedication(String genericName, String brandName,
			String classname, String common_side_effect, String dosse,
			Integer userId) {
		try {

			Object values[] = { genericName, brandName, classname,
					common_side_effect, dosse, userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.add_master_medication, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateMasterMedication(String genericName, String brandName,
			String classname, String common_side_effect, String dosse,
			Integer userId, Integer masterMedicationId) {
		try {

			Object values[] = { genericName, brandName, classname,
					common_side_effect, dosse, userId, masterMedicationId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Admin.update_master_medication, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getAllPatientByProvider(Integer userId) {

		Object values[] = { userId };
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_ALL_Practice_BY_PROVIDER_ID, values);

		return obj;

	}
	
	public Object getquickStats(int userid) {

		Object values[] = {userid};
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_QUICK_STATS,values);

		return obj;

	}
}
