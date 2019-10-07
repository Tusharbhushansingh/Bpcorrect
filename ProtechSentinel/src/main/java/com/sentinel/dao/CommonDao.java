package com.sentinel.dao;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.sentinel.constant.Constant;
import com.sentinel.constant.CustomQuery;
import com.sentinel.constant.Logmanager;
import com.sentinel.generic.dao.GenericDao;
import com.sentinel.model.NewUsers;
import com.sentinel.model.PracticeAdmin;
import com.sentinel.model.PrePayment;
import com.sentinel.model.ReadingModel;
import com.sentinel.model.UserFullModel;
import com.sentinel.model.UserFullModelIsSentinel;
import com.sentinel.model.UserUpdateFullModel;
import com.sentinel.util.Transaction;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Repository
@Transactional
public class CommonDao extends GenericDao {

	public Object addNotes( Integer id, String note, Integer receiverId, Integer senderId, Integer isCritical, String date, String epoch) {

		try {

			Object values[] = {id, note, receiverId, senderId, isCritical, date, epoch };

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_notes, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// new code
	public Object getLoghours(Integer main_id,Integer admin_id, Integer patient_id,Integer monthno,Integer dateno,Integer role_id, Integer year) {

		try {

			Object values[] = {main_id,admin_id,patient_id,monthno,dateno,role_id , year};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_log_hours, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// get log hours by patient
	
	public Object getLoghoursbyPatient(Integer year,Integer admin_id, Integer patient_id,Integer monthno,Integer dateno) {

		try {

			Object values[] = {admin_id,patient_id,monthno,dateno,year};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_log_hours_by_patient, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getLoghoursbyPatientfilterbyMonth(Integer admin_id,Integer monthno,Integer year) {

		try {

			Object values[] = {admin_id,monthno,year};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_log_hours_by_patient_filter_by_month, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getEhcprortocol(String enddate, Integer userId) {

		try {

			Object values[] = {enddate, userId};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_ehc_data, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getEhcprortocolBypatient(String patient_id) {
		String url_param = "patient_id: "+patient_id;
		try {

			Object values[] = {patient_id};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_ehc_data_by_patient, values);

			return obj;

		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "", 
					getExceptionString(e), String.valueOf(System.currentTimeMillis()), "GET","/get/ehc/protocol/by/patient");
            lm.initiateData();
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getPreviousprortocolBypatient(String patient_id) {
		String url_param = "patient_id: "+patient_id;
		try {

			Object values[] = {patient_id};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_previous_protocol_data_by_patient, values);

			return obj;

		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "", 
					getExceptionString(e), String.valueOf(System.currentTimeMillis()), "GET","/get/previous/protocol/by/patient");
            lm.initiateData();
			e.printStackTrace();
		}

		return null;
	}
	public Object getstateList() {

		try {

			Object values[] = {};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.getstates, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getLogs() {

		try {

			Object values[] = {};

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.getLogs, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addPatient(UserFullModel model) {

		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(),model.getPassword(), model.getCreaterUserId(), model.getMrnno()};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.ADD_PATIENT, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// register patient
	
	public Object registerPatient(String email, String password) {

		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			Object values[] = { email, passwordEncoder.encode(password)};
          
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.REGSITER_PATIENT, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object notauthorised() {

		try {

			Object values[] = {};

			Session session = getSession();
			Object obj = "Not a authorised device";
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addProvider(UserFullModel model) {
		try {
			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getPassword(), model.getCreaterUserId() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.ADD_PROVIDER, values);
			return obj;
		} catch (Exception e) { 
			e.printStackTrace();
		}

		return null;
	}
	public Object shareReading(String patient_id, String request_type, boolean status) {
		try {
			Object values[] = { patient_id, request_type, status };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.Share_Reading, values);
			return obj;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}
	
	// new code
	
	public Object getinfo(String userid) {
		try {
			Object values[] = { userid };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_info, values);
			return obj;
		} catch (Exception e) { 
			e.printStackTrace();
		}

		return null;
	}

	public Object getPatientInformation(Integer patientUserId) {
		try {
			Object values[] = { patientUserId };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_patient_information, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	public Object getQuickactionPatient(Integer startdate, Integer enddate, Integer userId) {
		try {
			Object values[] = { startdate, enddate, userId };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_quick_action_patients, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object update_enable_disable(Integer userid, Integer enableid) {
		try {
			Object values[] = { enableid, userid };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_enable_disable, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object deleteProtocol(String protocol_id) {
		try {
			Object values[] = { protocol_id };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.delete_protocol, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getinactivePatient(Integer adminId) {
		try {
			Object values[] = { adminId };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_inactive_patient, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object update_wave_off(Integer userid, Integer enableid) {
		try {
			Object values[] = { enableid, userid };
			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_wave_off, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addVital(Integer patientId, Integer deviceid, Integer userId, Integer service, Integer datasource,
			String value, Integer timed, Integer entry_type) {
		try {

			Object values[] = { patientId, deviceid, userId, service, datasource, value, timed, entry_type };

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_vital, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object userMappedWithHealthgroup(Integer userId, Integer health_care_group_id, Integer createrUserId) {
		try {

			Object values[] = { userId, health_care_group_id, createrUserId };

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.user_mapped_with_healthgroup, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object userMappedWithUser(Integer userId, Integer mappedUserId, Integer createrUserId) {
		try {

			Object values[] = { userId, mappedUserId, createrUserId };

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.user_mapped_with_user, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object bulkAddVital(List<String> sqlQueryList) {
		try {

			for (String sqlQuery : sqlQueryList) {
				Session session = getSession();
				Object obj = executeQuery(session, sqlQuery);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getHealthgroupDetailsByUser(Integer userId) {
		try {

			Object values[] = { userId };

			Session session = getSession();

			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_health_group_details_by_user,
					values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addAdminProvider(UserFullModelIsSentinel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getPassword(), model.getCreaterUserId(), model.getIssentinel() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_admin_provider, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	// new Code register practice admin
	
	public Object registerAdminPractice(PracticeAdmin model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getPassword(), model.getCreaterUserId(), model.getIssentinel()};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.register_practice_admin, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addAdmin(UserFullModelIsSentinel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getCreaterUserId(), model.getIssentinel() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_admin, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addOfficestaff(UserFullModelIsSentinel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(),model.getEmail(), model.getMobile1(), model.getMobile2(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(),model.getPassword(), model.getCreaterUserId(), 0 };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_officestaff, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object addphysiologicalData(@RequestParam Integer patientId,
			@RequestParam Integer datavalue, @RequestParam Integer sourceid, @RequestParam Integer timestamp) {
		try {

			Object values[] = { patientId, datavalue, sourceid, timestamp };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_physiologicaldata, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateAdminProvider(UserUpdateFullModel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getUserId() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_admin_provider, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateAdmin(UserUpdateFullModel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getUserId() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_admin, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	//new code to change password
	
	public Object changePassword(Integer userId, String password) {
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			password = passwordEncoder.encode(password);
			Object values[] = { userId, password };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.change_password, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateOfficestaff(UserUpdateFullModel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getUserId() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_officestaff, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updatePatient(UserUpdateFullModel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getUserId() , model.getMrnno() , model.getHeight(), model.getWeight()};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_patient, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateProvider(UserUpdateFullModel model) {
		try {

			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getUserId() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_provider, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addAllergyComorbidity(String name, String type, String description, Integer patientUserId,
			Integer createrUserId, String allergies_comorbidity_date) {
		try {

			Object values[] = { name, type, description, patientUserId, createrUserId, allergies_comorbidity_date };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_allergy_comorbidity, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateAllergyComorbidity(String name, String type, String description, Integer allergyComorbidityId,
			String allergies_comorbidity_date) {
		try {

			Object values[] = { name, type, description, allergyComorbidityId, allergies_comorbidity_date };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_allergy_comorbidity, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object mappingPatientAllergycomorbidity(Integer patientUserId, Integer allergiescomorbidityId,
			Integer createrUserId) {
		try {

			Object values[] = { patientUserId, allergiescomorbidityId, createrUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.patient_mapping_allergy_comorbidity,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addMedication(String name, String alias, String generic_name, String classtype, String dossage,
			String frequency, String frequency_count, String side_effect, String description, String pills,
			Integer meddate, String med_am_pm, Integer createrUserId, Integer patientUserId) {
		try {

			Object values[] = { name, alias, generic_name, classtype, dossage, frequency, frequency_count, side_effect,
					description, pills, meddate, med_am_pm, createrUserId, patientUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_medication, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object updateMedication(String name, String alias, String generic_name, String classtype, String dossage,
			String frequency, String frequency_count, String side_effect, String description, String pills,
			Integer meddate, String med_am_pm, Integer medicationId) {
		try {

			Object values[] = { name, alias, generic_name, classtype, dossage, frequency, frequency_count, side_effect,
					description, pills, meddate, med_am_pm, medicationId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_medication, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object mappingPatientMedication(Integer patientId, Integer medicationId, Integer userId) {
		try {

			Object values[] = { patientId, medicationId, userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.patient_mapping_medication, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getNotes(Integer receiverId, Integer senderid) {
		try {

			Object values[] = { receiverId, senderid };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.view_notes, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getPatientMedicationPatientId(Integer userId) {
		try {

			Object values[] = { userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_patient_medication_by_patient,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getPatientAllergyComorbidityPatientId(String type, Integer userId) {
		try {

			Object values[] = { type, userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null,
					CustomQuery.Common.get_patient_allergy_comorbidity_by_patient, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getAllMedication(Integer userId) {
		try {

			Object values[] = { userId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_all_medication, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object setGraduatedPatient(Integer userId, Integer isgraduated) {
		try {

			Object values[] = { userId, isgraduated };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.set_Graduated_patient, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getHealthGroup() {
		try {

			Object values[] = {};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_health_group, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addAppointmentTime(Integer time, Integer patientUserId, Integer createrUserId) {
		try {

			Object values[] = { time, patientUserId, createrUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_appointment_time, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object createEhcprotocol( Integer patient_id,  String startdate,
			 String enddate,  String protocol_id , String morning_alarm, String evening_alarm) {
		try {

			Object values[] = { patient_id, startdate, enddate, protocol_id , morning_alarm, evening_alarm};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.create_ehc_protocol, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	

	public Object getAppointmenTimeByPatientId(Integer patientUserId, Integer createrUserId) {
		try {

			Object values[] = { patientUserId, createrUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_appointment_time_by_patient_id,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getAllPatientByProviderIdFilter(Map<String, String> map) {
		try {

			String userId = map.get("userId") == null || map.get("userId").isEmpty() ? null : map.get("userId");

			String hypertensivestate = map.get("hypertensivestate") == null || map.get("hypertensivestate").isEmpty()
					? null
					: map.get("hypertensivestate");

			String patientType = map.get("patientType") == null || map.get("patientType").isEmpty() ? "3"
					: map.get("patientType");

			String dayno = map.get("dayno") == null || map.get("dayno").isEmpty() ? "7" : map.get("dayno");

			String trends = map.get("trends") == null || map.get("trends").isEmpty() ? null : map.get("trends");

			String filter = map.get("filter") == null || map.get("filter").isEmpty() ? null : map.get("filter");

			String patientCount = map.get("patientCount") == null || map.get("patientCount").isEmpty() ? null
					: map.get("patientCount");

			String pulseox = map.get("pulseox") == null || map.get("pulseox").isEmpty() ? null : map.get("pulseox");

			Object values[] = { userId, hypertensivestate, patientType, dayno, trends, filter, patientCount, pulseox };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_all_patient_by_provider_id_filter,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object jsonUserMappedWithMultipleHealthgroup(List<Map<String, String>> list) {

		List<Object> objList = new ArrayList<Object>();

		try {
			for (Map<String, String> map : list) {
				try {

					Integer userId = Integer.parseInt(map.get("userId"));
					Integer health_care_group_id = Integer.parseInt(map.get("health_care_group_id"));
					Integer masterUserId = Integer.parseInt(map.get("masterUserId"));

					Object values[] = { userId, health_care_group_id, masterUserId };

					Session session = getSession();
					Object obj = executeStoreProcedure(session, null, CustomQuery.Common.user_mapped_with_healthgroup,
							values);
					objList.add(obj);
				} catch (Exception e) {
					objList.add("" + e.getMessage());
				}
			}

			return objList;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getAllWearableDevice() {
		try {

			Object values[] = {};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_all_wearable_device, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getChartDataAccordingToUser(Integer dayno, Integer patientUserId, Integer from_date, Integer to_date) {
		try {

			Object values[] = { dayno, patientUserId , from_date, to_date};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_chart_data_according_to_user,
					values);
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object getProtocolDataAccordingToUser(Integer patientUserId) {
		try {

			Object values[] = { patientUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_protocol_data_according_to_user,
					values);	
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// add patient reading
	
	public Object addPatientReading(ReadingModel model[]) {
		try {	
			Session session = getSession();
						
			for (int i = 0; i < model.length; i++) {
				
				Integer countreading = 0;
				SQLQuery query = session.createSQLQuery("INSERT INTO `protech_bpcorrect`.`measurement`" + 
						"  (`patientId`, `device_id`, `measurement_service`, `measurement_datasource`, `measurement_value`, `timestamp`, `device_name`, `device_mac_address` , `is_abberant` , `protocol_id` , `entry_type`)" + 
						"VALUES(?,?,?,?,?,?,?,?,?,?,?);"+"");
		    for (int j =1; j< 4; j++) {    
		    	
		    	if (model[i].getProtocol_id() != null) {
		    		countreading ++;
		    	}
			query.setParameter(0, model[i].getPatientId());
			query.setParameter(1, model[i].getDevice_id());
			query.setParameter(3, j);
			if (j == 1) {
			query.setParameter(2, '1');
			query.setParameter(4, model[i].getSystolic());
			} else if(j == 2) {
				query.setParameter(2, '1');
				query.setParameter(4, model[i].getDiastolic());
			   } else {
				   query.setParameter(2, '2');
					query.setParameter(4, model[i].getPulse_data());
			   }
			query.setParameter(5, model[i].getReading_time());
			query.setParameter(6, model[i].getDevice_name());
			query.setParameter(7, model[i].getDevice_mac_address());
			query.setParameter(8, model[i].getIs_abberant());
			query.setParameter(9, model[i].getProtocol_id());
			query.setParameter(10, model[i].getProtocol_no());
			query.executeUpdate();
		        }
		    if (countreading > 0) {
		    	System.out.println("Protocol _ Id"+model[i].getProtocol_id()+" count "+countreading);
				Object values[] = {  model[i].getPatientId(), model[i].getProtocol_id()};
				 executeStoreProcedure(session, null, CustomQuery.Common.update_total_readings,	values);
			     }
			}	
			return "Data Updated Succesfully"; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// get device list 
	public Object getdeviceList() {
		try {
			Object values[] = {};
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_device_list,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	} 

	public Object getMasterComorbidity() {
		try {
			Object values[] = {};
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_master_comorbidity, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object removeAllergyComorbidity(Integer allergycomorbidityid) {
		try {
			Object values[] = { allergycomorbidityid };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.remove_allergy_comorbidity, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object bulkUserMappedWithMultipleUsers(List<Map<String, String>> list) {
		List<Object> objList = new ArrayList<Object>();
		try {
			for (Map<String, String> map : list) {
				try {
					Integer userId = Integer.parseInt(map.get("userId"));
					Integer mappedUserId = Integer.parseInt(map.get("mappedUserId"));
					Integer createrUserId = Integer.parseInt(map.get("createrUserId"));
					Object values[] = { userId, mappedUserId, createrUserId };
					Session session = getSession();
					Object obj = executeStoreProcedure(session, null, CustomQuery.Common.user_mapped_with_user, values);
					objList.add(obj);
				} catch (Exception e) {
					objList.add("" + e.getMessage());
				}
			}
			return objList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object removeUserMappedWithUser(Integer userId) {
		try {
			Object values[] = { userId };
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.remove_user_mapped_with_user_by_userId,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object getMasterMedication() {
		try {
			Object values[] = {};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_master_medication, values);

			return obj;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addNotesComments(Map<String, String> map) {
		try {

			String notes_id = map.get("notes_id") == null || map.get("notes_id").isEmpty() ? null : map.get("notes_id");

			String comments = map.get("comments") == null || map.get("comments").isEmpty() ? null : map.get("comments");

			String isInternelComment = map.get("isInternelComment") == null || map.get("isInternelComment").isEmpty() ? "0"
					: map.get("isInternelComment");

			Object values[] = { notes_id, comments, isInternelComment };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_notes_comments, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object addpaymentdetails(PrePayment pre, long epoch_time, String path) {
		try {

			String time = String.valueOf(epoch_time);
		
			Object values[] = { pre.getPractice_id(), pre.getMonth(), pre.getOrg_id(), pre.getWave_status(), time, 
					new Transaction().getTransaction(), path, pre.getEmail(),pre.getOrg_name(), pre.getTotalpatient(), pre.getYear()};
			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_payment_details, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object addpaymenttabledetails(PrePayment pre, long epoch_time, String path) {
		try {

			String time = String.valueOf(epoch_time);
			pre.setInvoice_id(time);
			Object values[] = { pre.getPractice_id(), pre.getMonth(), pre.getOrg_id(), pre.getWave_status(), pre.getInvoice_id(), 
					new Transaction().getTransaction(), path, pre.getEmail(),pre.getOrg_name(), pre.getTotalpatient(), pre.getYear()};

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.add_payment_table_details, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object uploadImage(String url, String userId) {
		try {

		
			Object values[] = {   userId,url };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.upload_profile_image, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	public Object finalpaymentstatus(String payment_date,String txn_id,Integer month,Integer year,String adminId,String amount,String coupon,
			String discount,String region, String payment_status) {
		try {

			Object values[] = { payment_date, txn_id, month, year, adminId, amount, coupon,
					 discount, region,  payment_status };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_payment_final_status, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getNotesComments(Map<String, String> map) {
		try {

			String notes_id = map.get("notes_id") == null || map.get("notes_id").isEmpty() ? null : map.get("notes_id");

			String comments_id = map.get("comments_id") == null || map.get("comments_id").isEmpty() ? null
					: map.get("comments_id");

			Object values[] = { notes_id, comments_id };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_notes_comments, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object getPatientSetting(Integer patientUserId) {
		try {

			Object values[] = { patientUserId };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_patient_setting, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	

	public Object setTimezone(String zone_name, String admin_id) {
		try {

			Object values[] = { zone_name, admin_id };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.set_timezone, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public Object getTimezone(Integer admin_id) {
		try {

			Object values[] = { admin_id };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_timezone, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public Object getCountry() {
		try {

			Object values[] = { };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_country, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
	
	public Object updatePatientSetting(Integer patientUserId, String type, Integer status) {

		try {

			Object values[] = { patientUserId, type, status };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_patient_setting, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/*public Object createPDFWithPatientDetails(Map<String, String> map) {
		try {

			Object values[] = { map.get("patientUserId") };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.create_pdf_with_patient_details,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}*/
	
	public Object createPDFWithPatientDetails(Integer month,Integer year) {
		try {

			Object values[] = { 0,month,year };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Admin.get_all_payment_record,
					values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Object addNewUsers(NewUsers model) {
		try {
			Object values[] = { model.getFirstname(), model.getMiddlename(), model.getLastname(), model.getDob(),
					model.getAddress1(), model.getAddress2(), model.getMobile1(), model.getMobile2(), model.getEmail(),
					model.getGender(), model.getCity(), model.getState(), model.getCountry(), model.getPhoto_url(),
					model.getZipcode(), model.getCreaterUserId(), model.getRole_type() };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.ADD_NEWUSER, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Object contactUs(String name, String subject, String message, String phone, String email) {
		try {

			Object values[] = { name, subject, message, phone , email };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.contact_us, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	// update protocol status
	public Object update_protocol_status() {
		try {

			Object values[] = { };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.update_protocol_status, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	public Object getAllUsersByUser(Integer userId, String userType) {
		try {
			Object values[] = { userId,userType };

			Session session = getSession();
			Object obj = executeStoreProcedure(session, null, CustomQuery.Common.get_all_users_by_user, values);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getExceptionString(Exception e) {
		StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return exceptionAsString;
	}

}
