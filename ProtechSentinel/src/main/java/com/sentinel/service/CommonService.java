package com.sentinel.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.LogManager;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParser;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.itextpdf.text.log.SysoLogger;
import com.sentinel.constant.Constant;
import com.sentinel.constant.ConstantMessage;
import com.sentinel.constant.Logmanager;
import com.sentinel.dao.CommonDao;
import com.sentinel.model.Chartmodel;
import com.sentinel.model.NewUsers;
import com.sentinel.model.PracticeAdmin;
import com.sentinel.model.PrePayment;
import com.sentinel.model.ProtocolData;
import com.sentinel.model.ReadingModel;
import com.sentinel.model.UserFullModel;
import com.sentinel.model.UserFullModelIsSentinel;
import com.sentinel.model.UserUpdateFullModel;
import com.sentinel.pdf.PDFGeneration;
import com.sentinel.response.ResponseMessage;
import com.sentinel.velocity.VelocityTemplate;
import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;

import springfox.documentation.spring.web.json.Json;

@Service
public class CommonService {

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private VelocityTemplate velocityTemplate;

	public Object addNotes(Integer id, String note, Integer receiverId, Integer senderId, Integer isCritical, String date, String epoch) {

		try {

			Object obj = commonDao.addNotes(id, note, receiverId, senderId, isCritical, date, epoch);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_notes, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	// new code

	public Object getLoghours(Integer main_id, Integer admin_id, Integer patient_id, Integer monthno, Integer dateno,
			Integer role_id, Integer year) {

		try {

			Object obj = commonDao.getLoghours(main_id, admin_id, patient_id, monthno, dateno, role_id, year);

			if (obj != null) {
				
				return new ResponseMessage<>(ConstantMessage.add_notes, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	// new code
	
	public Object getLoghoursbyPatient(Integer year, Integer admin_id, Integer patient_id, Integer monthno, Integer dateno) {

		try {

			Object obj = commonDao.getLoghoursbyPatient(year, admin_id, patient_id, monthno, dateno);

			if (obj != null) {
				
				return new ResponseMessage<>(ConstantMessage.log_hours, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getLoghoursbyPatientfilterbyMonth( Integer admin_id, Integer monthno, Integer year) {

		try {

			Object obj = commonDao.getLoghoursbyPatientfilterbyMonth(admin_id, monthno, year);

			if (obj != null) {
				
				return new ResponseMessage<>(ConstantMessage.log_hours, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getEhcprotocol(String enddate, Integer userId) {

		try {
			Object obj = commonDao.getEhcprortocol(enddate, userId);
			if (obj != null) {
				//ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) obj;
				//return data.size() > 0 ? new ResponseEntity(success(data).get(0), HttpStatus.OK) :  new ResponseEntity(fail().get(0), HttpStatus.BAD_REQUEST) ;
				return new ResponseMessage<>(ConstantMessage.ehc, obj, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		//return new ResponseEntity(fail().get(0), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public Object getEhcprotocolByPatient(String patient_id) {
      String url_param = "patient_id: "+patient_id;
		try {

			Object obj = commonDao.getEhcprortocolBypatient(patient_id);

			if (obj != null) {
				Logmanager lm = new Logmanager(url_param, obj.toString(), 
						"", String.valueOf(System.currentTimeMillis()), "GET","/get/ehc/protocol/by/patient");
	            lm.initiateData();
				return new ResponseMessage<>(ConstantMessage.ehc, obj, true);
			}

		} catch (Exception e) {
			//System.out.println("exception");
			Logmanager lm = new Logmanager(url_param, "", 
					getExceptionString(e), String.valueOf(System.currentTimeMillis()), "GET","/get/ehc/protocol/by/patient");
            lm.initiateData();
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getPreviousprotocolByPatient(String patient_id) {
	      String url_param = "patient_id: "+patient_id;
			try {

				Object obj = commonDao.getPreviousprortocolBypatient(patient_id);

				if (obj != null) {
					Logmanager lm = new Logmanager(url_param, obj.toString(), 
							"", String.valueOf(System.currentTimeMillis()), "GET","/get/previous/protocol/by/patient");
		            lm.initiateData();
					return new ResponseMessage<>(ConstantMessage.ehc, obj, true);
				}

			} catch (Exception e) {
				//System.out.println("exception");
				Logmanager lm = new Logmanager(url_param, "", 
						getExceptionString(e), String.valueOf(System.currentTimeMillis()), "GET","/get/previous/protocol/by/patient");
	            lm.initiateData();
				e.printStackTrace();
			}

			return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		}

	
	public Object getstateList() {

		try {

			Object obj = commonDao.getstateList();

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.log_hours, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getLogs() {

		try {

			Object obj = commonDao.getLogs();

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.log_hours, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addPatient(UserFullModel userFullModel) {
		try {

			Object obj = commonDao.addPatient(userFullModel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.ADDPatient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public boolean paymentmethod(Token token, Integer amount,   String adminId,  String coupon_code,
			 String discount,  String date,  Integer month, Integer  year ) {
		boolean isDebitSuccess = true;
		Exception err = null;
		Charge charge = null;	

		try {
			Stripe.apiKey="sk_test_e87gRZjpE6y4TGLCszBOIwUD";
           System.out.println("payment started  ");
			Map<String, Object> params = new HashMap<>();
			params.put("amount", amount*100);
			params.put("currency", "usd");
			//params.put("customer", name);
			params.put("description", "Bpcorrect");
			params.put("source", token.getId());
			Map<String, String> metadata = new HashMap<>();
			metadata.put("order_id", adminId);
			metadata.put("coupon_code",coupon_code);
			metadata.put("user_name","test user");
			metadata.put("month",String.valueOf(month));
			metadata.put("year",String.valueOf(year));
			metadata.put("discount",discount);
			params.put("metadata", metadata);

			charge = Charge.create(params);
			System.out.println( charge.getStatus());
			
			try {

				Object obj = commonDao.finalpaymentstatus(String.valueOf(new Date().getTime()) , charge.getBalanceTransaction(), month, year, adminId, String.valueOf(amount), coupon_code,
						discount, "US", charge.getStatus());

				if (obj != null) {
					// return new ResponseMessage<>(ConstantMessage.ADDPatient, obj, true);
				}

			} catch (Exception e) {
				e.printStackTrace();
				paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			}
		//	return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		
		} catch (AuthenticationException e) {
			isDebitSuccess = false;
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			err = e;
			System.out.println("Auth exception");
		} catch (InvalidRequestException e) {
			isDebitSuccess = false;
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			err = e;
			System.out.println("Invalid Request exception");
		} catch (CardException e) {
			isDebitSuccess = false;
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			err = e;
			System.out.println( "Card exception");
		} catch (ApiConnectionException e) {
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			e.printStackTrace();
		} catch (ApiException e) {
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			e.printStackTrace();
		} catch (StripeException e) {
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			e.printStackTrace();
		}  catch (Exception e) {
			paymentfailed(commonDao, charge, discount, date, month, year, adminId, coupon_code, amount);
			e.printStackTrace();
		}
		return isDebitSuccess;
	}
	
	
	void paymentfailed(CommonDao commonDao, Charge charge,  String discount,  String date,  Integer month, Integer  year, String adminId,  String coupon_code, Integer amount ) {
		commonDao.finalpaymentstatus(String.valueOf(new Date().getTime()) , charge.getBalanceTransaction(), month, year, adminId, String.valueOf(amount), coupon_code,
				discount, "US", charge.getStatus());	
	}
	// register patient
	public Object registerPatient(String email, String password) {
		String url_param = "email: "+email+"<br> password: "+password;
		try {
			Object obj = commonDao.registerPatient(email, password);
			if (obj != null) {
				//ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) obj;
				Logmanager lm = new Logmanager(url_param, obj.toString(), "", String.valueOf(System.currentTimeMillis()), "POST","/register/patient");
	            lm.initiateData();
	            return new ResponseMessage<>(ConstantMessage.RegisterPatient, obj, true);
				// return  new ResponseEntity(data.get(0), HttpStatus.OK) ;
					
			}

		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "",  
					getExceptionString(e) , String.valueOf(System.currentTimeMillis()),"POST","/register/patient");
            lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addProvider(UserFullModel userFullModel) {
		try {
			Object obj = commonDao.addProvider(userFullModel);
			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.ADDProvider, obj, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object shareReadings( String patient_id, String request_type, boolean status) {
		String url_param = "patient_id: "+patient_id+"<br> request_type: "+request_type+"<br> status: "+status;
		try {
			Object obj = commonDao.shareReading( patient_id,request_type,status);
			if (obj != null) {
				Logmanager lm = new Logmanager(url_param, obj.toString(), 
						"", String.valueOf(System.currentTimeMillis()), "POST","/share/reading");
	            lm.initiateData();
				return new ResponseMessage<>(ConstantMessage.Share_Reading, obj, true);
			}

		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "",  
					getExceptionString(e) , String.valueOf(System.currentTimeMillis()),"POST","/share/reading");
            lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	// new code
	public Object getinfo(String userid) {
		try {
			Object obj = commonDao.getinfo(userid);
			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.ADDProvider, obj, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getPatientInformation(Integer patientUserId) {
		 String url_param = "Url_Param =>  <br> patientUserId="+patientUserId+"";
		try {
			Object obj = commonDao.getPatientInformation(patientUserId);
			if (obj != null) {
			//	ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) obj;
				Logmanager lm = new Logmanager(url_param, obj.toString(), 
						"", String.valueOf(System.currentTimeMillis()), "GEt","/get/patient/information");
	            lm.initiateData();
				//return new ResponseMessage<>(ConstantMessage.get_patient_information, chart.get(0), true);
			//	return data.size() > 0 ? new ResponseEntity(success(data).get(0), HttpStatus.OK) :  new ResponseEntity(fail().get(0), HttpStatus.BAD_REQUEST) ;
				return new ResponseMessage<>(ConstantMessage.get_patient_information, obj, true);
			}
		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "",  
					getExceptionString(e) , String.valueOf(System.currentTimeMillis()), "GET","/get/patient/information");
            lm.initiateData();
			e.printStackTrace();
		}
		//return new ResponseEntity(fail().get(0), HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	private ArrayList<Map<String, Object>> success(ArrayList<Map<String, Object>> data) {
		ArrayList<Map<String, Object>> parsedata = new ArrayList<Map<String, Object>>();
		Map<String, Object> hash_map = new HashMap<String, Object>(); 
        // Mapping string values to int keys 
        hash_map.put("valid", true);
        data.add(hash_map);
        Map<String, Object> nhash_map = new HashMap<String, Object>(); 
        // Mapping string values to int keys 
        nhash_map.put("data", data.get(0));
        nhash_map.put("status", data.get(1));
        parsedata.add(nhash_map);
		return parsedata;
	}
	
   private ArrayList<Map<String, Object>> fail() {
	   ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	   ArrayList<Map<String, Object>> parsedata = new ArrayList<Map<String, Object>>();
	   Map<String, Object> hash_map = new HashMap<String, Object>(); 
       // Mapping string values to int keys 
       hash_map.put("valid", false);
       data.add(hash_map);
       Map<String, Object> nhash_map = new HashMap<String, Object>(); 
       // Mapping string values to int keys 
       nhash_map.put("status", data.get(0));
       nhash_map.put("data", "No data available");
       parsedata.add(nhash_map);
	   return parsedata;
	}
	// get quick action
	public Object getquickactionPatients(Integer startdate, Integer enddate ,Integer userId) {
		// String url_param = "Url_Param =>  <br> patientUserId="+patientUserId+"";
		try {
           
			Object obj = commonDao.getQuickactionPatient(startdate, enddate, userId);
           
			if (obj != null) {
				//Logmanager lm = new Logmanager(url_param, obj.toString(), 
				//		"", String.valueOf(System.currentTimeMillis()), "GEt","/get/patient/information");
	         //   lm.initiateData();
				return new ResponseMessage<>(ConstantMessage.get_patient_information, obj, true);
			}

		} catch (Exception e) {
			//Logmanager lm = new Logmanager(url_param, "",  
				//	getExceptionString(e) , String.valueOf(System.currentTimeMillis()), "GET","/get/patient/information");
      //     lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object update_enable_disable(Integer userid, Integer enableid) {
		try {

			Object obj = commonDao.update_enable_disable(userid, enableid);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_patient_information, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object deleteProtocol(String protocol_id) {
		try {

			Object obj = commonDao.deleteProtocol(protocol_id);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_patient_information, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object getInactivepatients(Integer adminId) {
		try {

			Object obj = commonDao.getinactivePatient(adminId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_inactive_patient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object update_wave_off(Integer userid, Integer enableid) {
		try {
			Object obj = commonDao.update_wave_off(userid, enableid);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_patient_information, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object userMappedWithHealthgroup(Integer userId, Integer health_care_group_id, Integer createrUserId) {
		try {

			Object obj = commonDao.userMappedWithHealthgroup(userId, health_care_group_id, createrUserId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.user_mapped_with_healthgroup, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object userMappedWithUser(Integer userId, Integer mappedUserId, Integer createrUserId) {
		try {

			Object obj = commonDao.userMappedWithUser(userId, mappedUserId, createrUserId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.user_mapped_with_user, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getHealthgroupDetailsByUser(Integer userId) {
		try {

			Object obj = commonDao.getHealthgroupDetailsByUser(userId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_health_group_details_by_patient, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addAdminProvider(UserFullModelIsSentinel userFullModelIsSentinel) {
		try {

			Object obj = commonDao.addAdminProvider(userFullModelIsSentinel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_admin_provider_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	// new code to register practice admin

	public Object register_practice_admin(PracticeAdmin practiceAdmin) {
		try {

			Object obj = commonDao.registerAdminPractice(practiceAdmin);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.practice_admin_added_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addAdmin(UserFullModelIsSentinel userFullModelIsSentinel) {
		try {

			Object obj = commonDao.addAdmin(userFullModelIsSentinel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_admin_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addOfficestaff(UserFullModelIsSentinel userFullModelIsSentinel) {
		try {

			Object obj = commonDao.addOfficestaff(userFullModelIsSentinel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_officestaff_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

// add physiological data
	public Object addphysiologicalData(@RequestParam Integer patientId, @RequestParam Integer datavalue,
			@RequestParam Integer sourceid, @RequestParam Integer timestamp) {
		try {

			Object obj = commonDao.addphysiologicalData(patientId, datavalue, sourceid, timestamp);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_officestaff_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updateAdminProvider(UserUpdateFullModel userUpdateFullModel) {
		try {

			Object obj = commonDao.updateAdminProvider(userUpdateFullModel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_admin_provider_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updateAdmin(UserUpdateFullModel userUpdateFullModel) {
		try {
			Object obj = commonDao.updateAdmin(userUpdateFullModel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_admin_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	// new code
	public Object changePassword(Integer userId, String password) {
		try {

			Object obj = commonDao.changePassword(userId, password);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_password_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updateOfficestaff(UserUpdateFullModel userUpdateFullModel) {
		try {

			Object obj = commonDao.updateOfficestaff(userUpdateFullModel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_officestaff_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updatePatient(UserUpdateFullModel userUpdateFullModel) {
		String url_param = "firstname: "+userUpdateFullModel.getFirstname()+"<br> middlename"+userUpdateFullModel.getMiddlename()+"<br> lastname"+userUpdateFullModel.getLastname()+"<br> dob"+userUpdateFullModel.getDob()+
				"<br> address1"+userUpdateFullModel.getAddress1()+"<br> address2"+userUpdateFullModel.getAddress2()+"<br> email"+userUpdateFullModel.getEmail()+
				"<br> mobile1"+userUpdateFullModel.getMobile1()+"<br> mobile2"+userUpdateFullModel.getMobile2()+"<br> gender"+userUpdateFullModel.getGender()+
				"<br> city"+userUpdateFullModel.getCity()+"<br> state"+userUpdateFullModel.getState()+"<br> country"+userUpdateFullModel.getCountry()+"<br> photo_url"+userUpdateFullModel.getPhoto_url()+
				"<br> zipcode"+userUpdateFullModel.getZipcode()+"<br> userId"+userUpdateFullModel.getUserId()+"<br> mrnno"+userUpdateFullModel.getMrnno()+"<br> height"+userUpdateFullModel.getHeight()+
				"<br> weight"+userUpdateFullModel.getWeight();
		try {
			Object obj = commonDao.updatePatient(userUpdateFullModel);
			if (obj != null) {
				// ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) obj;
				Logmanager lm = new Logmanager(url_param, obj.toString(), 
						"", String.valueOf(System.currentTimeMillis()), "POST", "/update/patient");
	            lm.initiateData();
	            return new ResponseMessage<>(ConstantMessage.update_patient_message, obj, true);
	            // return data.size() > 0 ? new ResponseEntity(success(data).get(0), HttpStatus.OK) :  new ResponseEntity(fail().get(0), HttpStatus.BAD_REQUEST) ;
				
			}
		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "", 
					getExceptionString(e), String.valueOf(System.currentTimeMillis()), "POST", "/update/patient");
            lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		// return new ResponseEntity<>(fail().get(0),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public Object updateProvider(UserUpdateFullModel userUpdateFullModel) {
		try {

			Object obj = commonDao.updateProvider(userUpdateFullModel);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_provider_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getNotes(Integer receiverId, Integer senderid) {
		try {

			Object obj = commonDao.getNotes(receiverId, senderid);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.view_notes_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object setGraduatedPatient(Integer userId, Integer isgraduated) {
		try {

			Object obj = commonDao.setGraduatedPatient(userId, isgraduated);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.set_Graduated_patient_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getHealthGroup() {
		try {

			Object obj = commonDao.getHealthGroup();

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_health_group_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addAppointmentTime(Integer time, Integer patientUserId, Integer createrUserId) {
		try {

			Object obj = commonDao.addAppointmentTime(time, patientUserId, createrUserId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_appointment_time_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object createEhcprotocol( Integer patient_id,  String startdate,
			 String enddate,  String protocol_id, String morning_alarm, String evening_alarm) {
		String url_param = "patient_id: "+patient_id+"<br> startdate: "+startdate+"<br>enddate: "+enddate+"<br> protocol_id: "+protocol_id+"<br> morning_alarm: "
			 +morning_alarm+
				"<br> evening_alarm: "+evening_alarm;
		try {
			Object obj = commonDao.createEhcprotocol(patient_id, startdate, enddate, protocol_id, morning_alarm, evening_alarm);
			if (obj != null) {
				Logmanager lm = new Logmanager(url_param, obj.toString(), 
						"", String.valueOf(System.currentTimeMillis()), "POST", "/create/ehc/protocol");
	            lm.initiateData();
				return new ResponseMessage<>(ConstantMessage.create_ehc_protocol, obj, true);
			}
		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "",
					getExceptionString(e), String.valueOf(System.currentTimeMillis()), "POST", "/create/ehc/protocol");
            lm.initiateData();
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getAppointmenTimeByPatientId(Integer patientUserId, Integer createrUserId) {
		try {

			Object obj = commonDao.getAppointmenTimeByPatientId(patientUserId, createrUserId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_appointment_time_by_patient_id_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object jsonUserMappedWithMultipleHealthgroup(String jsonData) {
		if (jsonData == null) {
			return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		}

		@SuppressWarnings("serial")
		Type type = new TypeToken<List<Map<String, String>>>() {
		}.getType();

		List<Map<String, String>> list = new Gson().fromJson(jsonData, type);

		Object obj = commonDao.jsonUserMappedWithMultipleHealthgroup(list);
		if (obj != null) {
			return new ResponseMessage<>(ConstantMessage.bulk_user_mapped_with_multiple_healthgroup, obj, true);
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getAllWearableDevice() {
		try {

			Object obj = commonDao.getAllWearableDevice();

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_all_wearable_device_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getChartDataAccordingToUser(Integer dayno, Integer patientUserId, Integer fromdate, Integer todate) {
		String url_param = "dayno :"+dayno+"<br> patientUserId :"+patientUserId+"<br> fromdate :"+fromdate+"<br> todate :"+todate;
		try {   
			Object obj = commonDao.getChartDataAccordingToUser(dayno, patientUserId, fromdate, todate);

			if (obj != null) {
				String chartData;
				ArrayList<Map<String, Object>> chart = (ArrayList<Map<String, Object>>) obj;
				if ( chart.get(0).get("chartdata") != null) {
				 chartData = chart.get(0).get("chartdata").toString();
				}
				else  {
					chartData = new ArrayList<>().toString();
				}
				//System.out.println(chartData);
				Object object = new Gson().fromJson(chartData, ArrayList.class);
				chart.get(0).put("chartdata", object);
				Logmanager lm = new Logmanager(url_param, chart.get(0).toString(),"", String.valueOf(System.currentTimeMillis()), "GET", "/get/chart/data/according/to/user");
	            lm.initiateData();
	            return new ResponseMessage<>(ConstantMessage.get_chart_data_according_to_user_message, chart, true);
	           // return chart.get(0).get("chartdata") != null ? new ResponseEntity(success(chart).get(0), HttpStatus.OK) :  new ResponseEntity(fail().get(0), HttpStatus.BAD_REQUEST) ;	
			}
		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "",getExceptionString(e), String.valueOf(System.currentTimeMillis()), "GET", "/get/chart/data/according/to/user");
            lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		//return new ResponseEntity<>(fail().get(0),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	public Object getProtocolDataAccordingToUser( Integer patientUserId) {
		//String url_param = patientUserId :"+patientUserId+"<br> fromdate :"+fromdate+"<br> todate :"+todate;
		try {   
			Object obj = commonDao.getProtocolDataAccordingToUser(patientUserId);

			if (obj != null) {
				String chartData;
				ArrayList<Map<String, Object>> chart = (ArrayList<Map<String, Object>>) obj;
			
				get_Protocol_Data(chart);
			
			//	System.out.println(get_Protocol_Data(chart)[0].toString());
			//	Logmanager lm = new Logmanager(url_param, chart.get(0).toString(),"", String.valueOf(System.currentTimeMillis()), "GET", "/get/chart/data/according/to/user");
	        //    lm.initiateData();
				if (chart.size() >0)
	            return new ResponseMessage<>(ConstantMessage.get_protocol_data_according_to_user_message, get_Protocol_Data(chart), true);
				else
					return new ResponseMessage<>(ConstantMessage.get_protocol_data_according_to_user_message, new ArrayList<>(), false);
					
				// return chart.get(0).get("chartdata") != null ? new ResponseEntity(success(chart).get(0), HttpStatus.OK) :  new ResponseEntity(fail().get(0), HttpStatus.BAD_REQUEST) ;
			}
		} catch (Exception e) {
		//	Logmanager lm = new Logmanager(url_param, "",getExceptionString(e), String.valueOf(System.currentTimeMillis()), "GET", "/get/chart/data/according/to/user");
         //   lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		//return new ResponseEntity<>(fail().get(0),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// add patient reading
	public Object addPatientReading( ReadingModel model[]) {
		String url_param ="";
		try {
			Object obj = commonDao.addPatientReading(model);
			StringBuilder sb1 = new StringBuilder(""); 
			for (int i = 0; i < model.length; i++) { 
				sb1.append("patientId :"+ model[i].getPatientId()+ "<br>");
				sb1.append("device_id :"+ model[i].getDevice_id()+ "<br>");
				sb1.append("systolic :"+model[i].getSystolic()+ "<br>");
				sb1.append("diastolic :"+model[i].getDiastolic()+ "<br>");
				sb1.append("pulse_data :"+model[i].getPulse_data()+ "<br>");
				sb1.append("reading_time :"+model[i].getReading_time()+ "<br>");
				sb1.append("device_name :"+model[i].getDevice_name()+ "<br>");
				sb1.append("device_mac_address :"+model[i].getDevice_mac_address()+ "<br>");
				sb1.append("is_abberant :"+model[i].getIs_abberant()+ "<br>");
				sb1.append("protocol_id :"+model[i].getProtocol_id());
				sb1.append("protocol_no :"+model[i].getProtocol_no());
			}
		      url_param = "Url Param =>"+sb1;			
			if (obj != null) {
				//ArrayList<Map<String, Object>> data = (ArrayList<Map<String, Object>>) obj;
				Logmanager lm = new Logmanager(url_param, obj.toString(),"", String.valueOf(System.currentTimeMillis()), "POST", "/add/patient/reading");
	            lm.initiateData();
	            return new ResponseMessage<>(ConstantMessage.add_patient_data, obj, true);
	            // return data.size() > 0 ? new ResponseEntity(success(data).get(0), HttpStatus.OK) :  new ResponseEntity(fail().get(0), HttpStatus.BAD_REQUEST) ;
			}
		} catch (Exception e) {
			Logmanager lm = new Logmanager(url_param, "",getExceptionString(e), String.valueOf(System.currentTimeMillis()), "POST", "/add/patient/reading");
            lm.initiateData();
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		// return new ResponseEntity<>(fail().get(0),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// get device list
	public Object getdeviceList( ) {
		try {
			Object obj = commonDao.getdeviceList();
			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_device_list, obj, true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object bulkUserMappedWithMultipleUsers(String jsonData) {
		if (jsonData == null) {
			return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		}

		@SuppressWarnings("serial")
		Type type = new TypeToken<List<Map<String, String>>>() {
		}.getType();

		List<Map<String, String>> list = new Gson().fromJson(jsonData, type);

		boolean removeMapping = deleteMapping(list);

		System.out.println("delete Mapping:- " + removeMapping);

		Object obj = commonDao.bulkUserMappedWithMultipleUsers(list);
		if (obj != null) {
			return new ResponseMessage<>(ConstantMessage.bulk_user_mapped_with_multiple_users, obj, true);
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	private boolean deleteMapping(List<Map<String, String>> list) {
		try {

			Set<Integer> userIdSet = new HashSet<Integer>();

			for (Map<String, String> map : list) {
				userIdSet.add(Integer.parseInt(map.get("userId")));
			}

			System.out.println(userIdSet);

			for (Integer userId : userIdSet) {
				try {
					Object obj = commonDao.removeUserMappedWithUser(userId);
					if (obj != null) {
						System.out.println("Deleted UserId Mapping:- " + obj);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	/*public Object exportPDF(Map<String, String> map) {
		try {

			// Get the root path of tomcat server
			String rootPath = System.getProperty("catalina.home");
			// System.out.println("root Path:- " + rootPath);

			// Set the file Directory/Path on tomcat server
			File fileDirectory = new File(rootPath + File.separator + "webapps/tmpFiles");

			// If File Directory does not exists
			if (!fileDirectory.exists())
				fileDirectory.mkdirs();

			String path = fileDirectory.getAbsolutePath() + "/patient-" + new Date().getTime() + ".pdf";

			// String htmlPath = fileDirectory.getAbsolutePath() + "/pdfsample.vm";

			Object objData = commonDao.createPDFWithPatientDetails(map);

			System.out.println("OBJ Data:- " + objData);

			String templateData = velocityTemplate.readTemplate(fileDirectory.getAbsolutePath(), "pdfsample.vm",
					objData);

			System.out.println("TemplateData:-" + templateData);

			String htmlPath = fileDirectory.getAbsolutePath() + "/finalPatientHtml.vm";

			BufferedWriter out = new BufferedWriter(new FileWriter(htmlPath));
			out.write(templateData);
			out.close();

			boolean flag = PDFGeneration.createPDF(path, htmlPath);

			if (!flag) {
				return new ResponseMessage<Object>("Some Error on PDF Generation.", false);
			}

			Object obj = path;

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.pdf_reponse_path, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}*/
	public Object uploadfile(MultipartFile file, String userId) {
		try {
			// Get the root path of tomcat server
			String rootPath = System.getProperty("catalina.home");
			// System.out.println("root Path:- " + rootPath);

			// Set the file Directory/Path on tomcat server
			File fileDirectory = new File(rootPath + File.separator + "webapps/profileImages");
           //  System.out.println(file.getBytes());
			String fileType = file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1);
			// If File Directory does not exists
			if (!fileDirectory.exists())
				fileDirectory.mkdirs();
            long epoch_time = new Date().getTime();

			String htmlPath = fileDirectory.getAbsolutePath() + "/"+userId+"_"+epoch_time+"."+fileType;
			// Object upload = commonDao.uploadImage("/"+userId+"_"+epoch_time+".jpg", userId);
	      //     System.out.println(upload.toString());
	      //     if (upload.toString().length() > 5) {}
			String path = "/profileImages"+"/"+userId+"_"+epoch_time+"."+fileType;
			File f =new File(htmlPath);
			byte[] bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(htmlPath));
			stream.write(bytes);
			stream.close();
			return path;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
          }
	//  test code
	
	public Object exportPDF(String key, Integer month, Integer year) {
		try {
              if (!key.equals("e74a562f-b5ed-11e9-9c6c-00163e11554c"))
            	  return new ResponseMessage<>(ConstantMessage.invalid_key, false);
			// Get the root path of tomcat server
			String rootPath = System.getProperty("catalina.home");
			// System.out.println("root Path:- " + rootPath);

			// Set the file Directory/Path on tomcat server
			File fileDirectory = new File(rootPath + File.separator + "webapps/tmpFiles");

			// If File Directory does not exists
			if (!fileDirectory.exists())
				fileDirectory.mkdirs();
            long epoch_time = new Date().getTime();
			// String path = fileDirectory.getAbsolutePath() + "/monthlybill-" + epoch_time + ".pdf";

			// String htmlPath = fileDirectory.getAbsolutePath() + "/pdfsample.vm";

			ArrayList<PrePayment> objData = (ArrayList<PrePayment>) commonDao.createPDFWithPatientDetails(month , year);
            for( int j=0; j<objData.size(); j++)  {
            	String payment_json = new Gson().toJson(objData.get(j));
    			PrePayment pr = new ObjectMapper().readValue(payment_json, PrePayment.class);
    			Object preinsert = commonDao.addpaymenttabledetails(pr, epoch_time+j, "");
    			
    			if (preinsert == null) {
    				return new ResponseMessage<>(ConstantMessage.pdf_data_insertion_error, true);
    			}
            }
            ArrayList<PrePayment> newobjData = (ArrayList<PrePayment>) commonDao.createPDFWithPatientDetails(month , year);
			for (int i=0; i<newobjData.size(); i++) {
				String path = fileDirectory.getAbsolutePath() + "/monthlybill-" + epoch_time+i + ".pdf";
			System.out.println("OBJ Data:- " + newobjData.get(i));
			String json = new Gson().toJson(newobjData.get(i));
			PrePayment p = new ObjectMapper().readValue(json, PrePayment.class);
			
			System.out.println(p.getCity());
		    
			// create store procedure to insert into payment, invoice(id month pdfpath) , emaildetails with pdf attached
			String templateData = velocityTemplate.readTemplate(fileDirectory.getAbsolutePath(), "pdfsample.vm",
					newobjData.get(i),String.valueOf(epoch_time+i), getmonth(p.getMonth()) , getamount(p.getTotalpatient(), p.getCharge_amount()));

			System.out.println("TemplateData:-" + templateData);

			String htmlPath = fileDirectory.getAbsolutePath() + "/finalPatientHtml.vm";

			BufferedWriter out = new BufferedWriter(new FileWriter(htmlPath));
			out.write(templateData);
			out.close();

			boolean flag = PDFGeneration.createPDF(path, htmlPath);

			if (!flag) {
				return new ResponseMessage<Object>("Some Error on PDF Generation.", false);
			}

			Object obj = path;
			String pdfpath = ((String) path).valueOf(obj);
			String newpath = pdfpath.substring(51, pdfpath.length());
			System.out.println(pdfpath);
			Object insert = commonDao.addpaymentdetails(p, epoch_time+i, newpath);
			
			if (obj != null && i == (objData.size()-1)) {
				return new ResponseMessage<>(ConstantMessage.pdf_reponse_path, obj, true);
			}
			
			if (insert == null) {
				return new ResponseMessage<>(ConstantMessage.pdf_data_insertion_error, true);
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	String getamount(String total, String charge) {
		Integer i = Integer.parseInt(total);
		Integer j = Integer.parseInt(charge);
		System.out.println("amount  "+String.valueOf(i*j));
		return String.valueOf(i*j);
		
	}
	
	String getmonth(String number) {
		Integer mno = Integer.parseInt(number);
		String[] months = new String[13];
	       months[0] = null ;
	       months[1] = "January";
	       months[2] = "February";
	       months[3] = "March";
	       months[4] = "April";
	       months[5] = "May";
	       months[6] = "June";
	       months[7] = "July";
	       months[8] = "August";
	       months[9] = "September";
	       months[10] = "October";
	       months[11] = "November";
	       months[12] = "December";
		return months[mno];
		
		
	}
	
	// test method
	public static Map<String,Object> parseJSONObjectToMap(JSONObject jsonObject) throws JSONException{
	    Map<String, Object> mapData = new HashMap<String, Object>();
	    Iterator<String> keysItr = jsonObject.keys();
	        while(keysItr.hasNext()) {
	            String key = keysItr.next();
	            Object value = jsonObject.get(key);

	           if(value instanceof JSONObject) {
	                value = parseJSONObjectToMap((JSONObject) value);
	            }
	            mapData.put(key, value);
	        }
	    return mapData;
	}

	public Object addNotesComments(Map<String, String> map) {
		try {

			Object obj = commonDao.addNotesComments(map);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.add_notes_comments, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getNotesComments(Map<String, String> map) {
		try {

			Object obj = commonDao.getNotesComments(map);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_notes_comments, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getPatientSetting(Integer patientUserId) {
		try {

			Object obj = commonDao.getPatientSetting(patientUserId);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_patient_setting_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object updatePatientSetting(Integer patientUserId, String type, Integer status) {
		try {

			Object obj = commonDao.updatePatientSetting(patientUserId, type, status);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_patient_setting_message, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object addNewUsers(NewUsers newUsers) {
		try {

			Object obj = commonDao.addNewUsers(newUsers);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.ADDNewUser, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object getAllUsersByUser(Integer userId, String userType) {
		try {

			Object obj = commonDao.getAllUsersByUser(userId, userType);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_all_users_by_user, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	public Object setTimezone(String zone_name, String admin_id) {
		try {

			Object obj = commonDao.setTimezone(zone_name, admin_id);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_all_users_by_user, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	// new code

	public Object getTimezone(Integer admin_id) {
		try {

			Object obj = commonDao.getTimezone(admin_id);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_all_users_by_user, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}

	// new code
	public Object getCountry() {
		try {

			Object obj = commonDao.getCountry();

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.get_all_users_by_user, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	public Object contactUs(String name, String subject, String message, String phone, String email) {
		try {

			Object obj = commonDao.contactUs(name, subject, message, phone, email);

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.contact_us, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	// update status
	
	public Object update_protocol_status() {
		try {

			Object obj = commonDao.update_protocol_status();

			if (obj != null) {
				return new ResponseMessage<>(ConstantMessage.update_protocol_status, obj, true);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
	}
	
	// register patient
		public Object notauthorised() {
			try {

				Object obj = commonDao.notauthorised();

				if (obj != null) {
					return new ResponseMessage<>(ConstantMessage.notAddPatient, obj, true);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new ResponseMessage<Object>(ConstantMessage.ErrorMessage, false);
		}
		
		public String getExceptionString(Exception e) {
			StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return exceptionAsString;
		}
		
	public	ProtocolData[]	get_Protocol_Data(ArrayList<Map<String, Object>> chart) {
    ProtocolData[] data = new ProtocolData[chart.size()];
		for (int i=0; i<chart.size();i++) { 
			ProtocolData p = new ProtocolData();
			System.out.println(chart.get(i).get("device_name").toString());
			p.setDevice_name(chart.get(i).get("device_name").toString());
			p.setEnd_date (chart.get(i).get("end_date").toString());
			
			p.setMeasurement_service (chart.get(i).get("measurement_service").toString());
			p.setPatient_id (chart.get(i).get("patient_id").toString());
			p.setProtocol_id (chart.get(i).get("protocol_id").toString());
			p.setStart_date (chart.get(i).get("start_date").toString());
			p.setTimestamp (chart.get(i).get("timestamp").toString());
			p.set_key_value(chart.get(i).get("datasource").toString(), chart.get(i).get("value").toString());
			p.setDatasource(chart.get(i).get("datasource").toString());
			p.setValue (chart.get(i).get("value").toString());
			data[i] = p;
		}
			return data;
		}
	
}
