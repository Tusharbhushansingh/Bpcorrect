package com.sentinel.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sentinel.constant.CustomQuery;
import com.sentinel.generic.dao.GenericDao;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
@Transactional
public class ProviderDao extends GenericDao {

	public Object getAllPatientByProvider(Integer userId, Integer admin_id) {

		Object values[] = { userId , admin_id};
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_ALL_PATIENT_BY_PROVIDER_ID, values);

		return obj;

	}
	
	public Object applycoupon(String code) {

		Object values[] = { code};
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.apply_coupon, values);

		return obj;

	}
	
	public Object getAllPatientBySuperAdmin(Integer userId, Integer admin_id) {

		Object values[] = { userId , admin_id};
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_ALL_PATIENT_BY_ADMIN_ID, values);

		return obj;

	}
	
	// new code
	public Object getCommonPatientByProvider(Integer userId, Integer admin_id) {

		Object values[] = { userId , admin_id};
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_COMMON_PATIENT_BY_PROVIDER, values);

		return obj;

	}
	
	public Object addPatientLoghours(Integer userId, Integer admin_id, String inTime, String outTime, Double time, Integer day, Integer month, Integer year) {

		Object values[] = { userId , admin_id, inTime, outTime, time , day, month, year};
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.ADD_LOGHOURS_PATIENT_BY_PROVIDER, values);

		return obj;

	}
	
	public Object getAllProviderByAdmin(Integer userId, Integer adminId) {

		Object values[] = { userId , adminId };
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_ALL_PROVIDER_BY_PRACTICE_ID, values);

		return obj;

	}
	
	// new code 
	public Object getPracticeAdminChart(Integer userId, Integer startdate, Integer enddate) {

		Object values[] = { userId , startdate, enddate };
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_PRACTICE_ADMIN_CHART, values);

		return obj;

	}
	// new code
	
	public Object getAllstaffByAdmin(Integer userId, Integer adminId) {

		Object values[] = { userId , adminId };
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_ALL_STAFF_BY_PRACTICE_ID, values);

		return obj;

	}
	
	public Object getCommonstaffByProvider(Integer userId, Integer adminId) {

		Object values[] = { userId , adminId };
		Session session = getSession();

		Object obj = executeStoreProcedure(session, null,
				CustomQuery.Provider.GET_COMMON_STAFF, values);
		return obj;
	}

	public Object getVitalLastVisit(Integer patientUserId, Integer userId) {
		try {
			Object values[] = { patientUserId, userId };

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Provider.get_vital_last_visit_by_patient,
					values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getBloodPressureByPatient(Integer patientUserId) {
		try {
			Object values[] = { patientUserId };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Provider.get_blood_pressure_by_patient, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getPulseoxByPatient(Integer patientUserId) {
		try {
			Object values[] = { patientUserId };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Provider.get_piulseox_by_patient, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getPulseByPatient(Integer patientUserId) {
		try {
			Object values[] = { patientUserId };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Provider.get_pulse_by_patient, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getAllProvidersByProvider(Integer userId) {
		try {

			Object values[] = { userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Provider.get_all_providers_by_provider, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// new code
	
	
	

}
