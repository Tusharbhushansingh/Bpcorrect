package com.sentinel.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.json.JSONObject;
import org.json.JSONString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spring.web.json.Json;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.sentinel.constant.Logmanager;
import com.sentinel.model.NewUsers;
import com.sentinel.model.ReadingModel;
import com.sentinel.model.UserFullModel;
import com.sentinel.model.UserFullModelIsSentinel;
import com.sentinel.model.UserUpdateFullModel;
import com.sentinel.response.ResponseMessage;
import com.sentinel.service.CommonService;
import com.sentinel.swagger.model.ResponseObject;
import com.stripe.Stripe;
import com.stripe.exception.ApiConnectionException;
import com.stripe.exception.ApiException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.StripeObject;
import com.stripe.model.Token;

@RestController
@RequestMapping("/common")
@Api(value = "/common", description = "Common Controller")
@Configuration
@EnableScheduling
public class CommonController {

	@Autowired
	private CommonService commonService;

	@ApiOperation(value = "/add/notes", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "note", value = "Notes which is send", required = true, access = "query", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "receiverId", value = "receiver id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "senderId", value = "sender id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "isCritical", value = "is Critical", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/notes", method = RequestMethod.POST)
	public Object addNotes(@RequestParam Integer id ,@RequestParam String note, @RequestParam Integer receiverId, @RequestParam Integer senderId,
			@RequestParam Integer isCritical, @RequestParam String date, @RequestParam String epoch_time) {
		return commonService.addNotes(id, note, receiverId, senderId, isCritical,date, epoch_time);
	}

	@ApiOperation(value = "/add/patient", notes = "Add Patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"), })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/patient", method = RequestMethod.POST)
	public Object addPatient(@ModelAttribute UserFullModel userFullModel) {
		return commonService.addPatient(userFullModel);
	}

	@ApiOperation(value = "/initiate/payment", notes = "Initiate Payment", response = ResponseMessage.class)
	@ApiImplicitParams({
			 @ApiImplicitParam(name = "access_token", value = "Access token", required =
			 true, access = "query", paramType = "query")
	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })

	@RequestMapping(value = "/initiate/payment", method = RequestMethod.POST)
	public Object initiatePayment(@RequestParam String token, @RequestParam String amount, @RequestParam String adminId, @RequestParam String coupon_code,
			@RequestParam String discount, @RequestParam String date, @RequestParam Integer month , @RequestParam Integer year  ) throws ClassNotFoundException, IOException {
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(token);
		JsonObject o = parser.parse(token).getAsJsonObject();
		ObjectMapper objectMapper = new ObjectMapper();
		Gson gson = new Gson();
		Token obj = gson.fromJson(o.toString(), Token.class);
		System.out.println(obj);
		int result = Integer.parseInt(amount);		
		return commonService.paymentmethod(obj, result, adminId, coupon_code, discount, date, month, year);
	}
	
	

	@ApiOperation(value = "/register/patient", notes = "Add Patient", response = ResponseMessage.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "registration email", required = true, access = "query", paramType = "query", dataType = "String"),
		@ApiImplicitParam(name = "password", value = "registration password", required = true, access = "query", paramType = "query", dataType = "String")
	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/register/patient", method = RequestMethod.POST)
	public Object registerPatient(@RequestParam String email, @RequestParam String password) {
	//	@RequestHeader(value = "User-Agent") String useragent
		// String[] splited = useragent.split("\\s+");
		// if (useragent.contains("Android") || useragent.contains("iPhone "))
			return commonService.registerPatient(email, password);
		// else 
			// return commonService.notauthorised();
	}

	@ApiOperation(value = "/add/provider", notes = "Add Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/provider", method = RequestMethod.POST)
	public Object addProvider(@ModelAttribute UserFullModel userFullModel) {
		return commonService.addProvider(userFullModel);
	}
	
	@ApiOperation(value = "/share/reading", notes = "Add Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/share/reading", method = RequestMethod.POST)
	public Object shareReadings(@RequestParam String patient_id, @RequestParam String request_type, @RequestParam boolean status) {
		return commonService.shareReadings(patient_id, request_type, status);
	}

	// new code

	@ApiOperation(value = "/get/info", notes = "GEt Info", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/info", method = RequestMethod.GET)
	public Object addProvider(@RequestParam String userid) {
		return commonService.getinfo(userid);
	}

	@ApiOperation(value = "/get/patient/information", notes = "Get All patient information", response = ResponseObject.GetPatientInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "patientUserId", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/patient/information", method = RequestMethod.GET)
	public Object getPatientInformation(@RequestParam Integer patientUserId) {
		
		return commonService.getPatientInformation(patientUserId);
	}
	
	@ApiOperation(value = "/get/quick/action/patients", notes = "Get All patient information", response = ResponseObject.GetPatientInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")
			})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/quick/action/patients", method = RequestMethod.GET)
	public Object getPatientInformation(@RequestParam Integer startdate, @RequestParam Integer enddate, @RequestParam Integer userId ) {
		
		return commonService.getquickactionPatients(startdate, enddate, userId);
	}

	@ApiOperation(value = "/update/enable/disable", notes = "Get All patient information", response = ResponseObject.GetPatientInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userid", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "enableid", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/enable/disable", method = RequestMethod.POST)
	public Object update_enable_disable(@RequestParam Integer userid, Integer enableid) {
		return commonService.update_enable_disable(userid, enableid);
	}
	
	@ApiOperation(value = "/delete/protocol", notes = "Get All patient information", response = ResponseObject.GetPatientInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "protocol_id", value = "provider patient user id", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/delete/protocol", method = RequestMethod.POST)
	public Object deleteProtocol(@RequestParam String protocol_id) {
		return commonService.deleteProtocol(protocol_id);
	}
	
	@ApiOperation(value = "/get/inactive/patients", notes = "Get All patient information", response = ResponseObject.GetPatientInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "adminId", value = "Admin ID", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/inactive/patients", method = RequestMethod.GET)
	public Object getinactivePatient(@RequestParam Integer adminId) {
		return commonService.getInactivepatients(adminId);
	}
	
	@ApiOperation(value = "/update/waveoff/status", notes = "Get All patient information", response = ResponseObject.GetPatientInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userid", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "enableid", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/waveoff/status", method = RequestMethod.POST)
	public Object update_wave_off(@RequestParam Integer userid, Integer enableid) {
		return commonService.update_wave_off(userid, enableid);
	}

	@ApiOperation(value = "/user/mapped/with/healthgroup", notes = "User Mapped With Health Group", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "User Id(mapped User Id)", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "health_care_group_id", value = "Health Group Id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "createrUserId", value = "ID of user which is mapped user with health group", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/user/mapped/with/healthgroup", method = RequestMethod.POST)
	public Object userMappedWithHealthgroup(@RequestParam Integer userId, @RequestParam Integer health_care_group_id,
			@RequestParam Integer createrUserId) {
		return commonService.userMappedWithHealthgroup(userId, health_care_group_id, createrUserId);
	}

	@ApiOperation(value = "/user/mapped/with/user", notes = "User mapped with user", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "User Id(mapped User Id)", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "mappedUserId", value = "mapped User Id ", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "createrUserId", value = "ID of user which is mapped user with user", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/user/mapped/with/user", method = RequestMethod.POST)
	public Object userMappedWithUser(@RequestParam Integer userId, @RequestParam Integer mappedUserId,
			@RequestParam Integer createrUserId) {
		return commonService.userMappedWithUser(userId, mappedUserId, createrUserId);
	}

	@ApiOperation(value = "/get/healthgroup/details/by/user", notes = "Get Health Group Details By Patient Id", response = ResponseObject.GetHealthGroupDetailsByPatient.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "Users user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/healthgroup/details/by/user", method = RequestMethod.GET)
	public Object getHealthgroupDetailsByUser(@RequestParam Integer userId) {
		return commonService.getHealthgroupDetailsByUser(userId);
	}

	// New APIs

	@ApiOperation(value = "/add/admin/provider", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/admin/provider", method = RequestMethod.POST)
	public Object addAdminProvider(@ModelAttribute UserFullModelIsSentinel userFullModelIsSentinel) {
		return commonService.addAdminProvider(userFullModelIsSentinel);
	}

	@ApiOperation(value = "/add/admin", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/admin", method = RequestMethod.POST)
	public Object addAdmin(@ModelAttribute UserFullModelIsSentinel userFullModelIsSentinel) {
		return commonService.addAdmin(userFullModelIsSentinel);
	}

	@ApiOperation(value = "/add/officestaff", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/officestaff", method = RequestMethod.POST)
	public Object addOfficestaff(@ModelAttribute UserFullModelIsSentinel userFullModelIsSentinel) {
		return commonService.addOfficestaff(userFullModelIsSentinel);
	}

	@ApiOperation(value = "/update/admin/provider", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/admin/provider", method = RequestMethod.POST)
	public Object updateAdminProvider(@ModelAttribute UserUpdateFullModel userUpdateFullModel) {
		return commonService.updateAdminProvider(userUpdateFullModel);
	}

	@ApiOperation(value = "/update/admin", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/admin", method = RequestMethod.POST)
	public Object updateAdmin(@ModelAttribute UserUpdateFullModel userUpdateFullModel) {
		return commonService.updateAdmin(userUpdateFullModel);
	}

	// new api

	@ApiOperation(value = "/change/password", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "Users user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/change/password", method = RequestMethod.POST)
	public Object changePassword(@RequestParam Integer userId, @RequestParam String password) {
		return commonService.changePassword(userId, password);
	}

	@ApiOperation(value = "/update/officestaff", notes = "Add Admin Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/officestaff", method = RequestMethod.POST)
	public Object updateOfficestaff(@ModelAttribute UserUpdateFullModel userUpdateFullModel) {
		return commonService.updateOfficestaff(userUpdateFullModel);
	}

	@ApiOperation(value = "/update/patient", notes = "Add Patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/patient", method = RequestMethod.POST)
	public Object updatePatient(@ModelAttribute UserUpdateFullModel userUpdateFullModel) {
		return commonService.updatePatient(userUpdateFullModel);
	}

	@ApiOperation(value = "/update/provider", notes = "Add Provider", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/provider", method = RequestMethod.POST)
	public Object updateProvider(@ModelAttribute UserUpdateFullModel userUpdateFullModel) {
		return commonService.updateProvider(userUpdateFullModel);
	}

	@ApiOperation(value = "/get/notes", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "receiverId", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "senderid", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/notes", method = RequestMethod.GET)
	public Object getNotes(@RequestParam Integer receiverId, @RequestParam Integer senderid) {
		return commonService.getNotes(receiverId, senderid);
	}

	@ApiOperation(value = "/add/physiological/data", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientId", value = "patient id ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "datavalue", value = "reading value ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "sourceid", value = "source value ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "timestamp", value = "timestamp value ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/physiological/data", method = RequestMethod.POST)
	public Object addphysiologicalData(@RequestParam Integer patientId, @RequestParam Integer datavalue,
			@RequestParam Integer sourceid, @RequestParam Integer timestamp) {
		return commonService.addphysiologicalData(patientId, datavalue, sourceid, timestamp);
	}

	@ApiOperation(value = "/set/graduated/patient", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "User ID ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "isgraduated", value = "isgraduated true and false ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/set/graduated/patient", method = RequestMethod.GET)
	public Object setGraduatedPatient(@RequestParam Integer userId, @RequestParam Integer isgraduated) {
		return commonService.setGraduatedPatient(userId, isgraduated);
	}

	@ApiOperation(value = "/get/health/group", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/health/group", method = RequestMethod.GET)
	public Object getHealthGroup() {
		return commonService.getHealthGroup();
	}

	@ApiOperation(value = "/add/appointment/time", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "time", value = "User ID ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "User ID ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "createrUserId", value = "creater User Id  ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/appointment/time", method = RequestMethod.POST)
	public Object addAppointmentTime(@RequestParam Integer time, @RequestParam Integer patientUserId,
			@RequestParam Integer createrUserId) {
		return commonService.addAppointmentTime(time, patientUserId, createrUserId);
	}
	
	@ApiOperation(value = "/create/ehc/protocol", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patient_id", value = "patient_id ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "startdate", value = "start_date", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "enddate", value = "end_date ", required = true, access = "query", paramType = "query") ,
			@ApiImplicitParam(name = "protocol_id", value = "protocol_id", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "morning_alarm", value = "Morning Alarm Time ", required = true, access = "query", paramType = "query") ,
			@ApiImplicitParam(name = "evening_alarm", value = "Evening alarm tIme", required = true, access = "query", paramType = "query"),
			
			})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/create/ehc/protocol", method = RequestMethod.POST)
	public Object createEhcprotocol( @RequestParam Integer patient_id, @RequestParam String startdate,
			@RequestParam String enddate, @RequestParam String protocol_id, @RequestParam String morning_alarm, @RequestParam String evening_alarm) {
		return commonService.createEhcprotocol( patient_id, startdate, enddate, protocol_id, morning_alarm, evening_alarm );
	}
	

	@ApiOperation(value = "/get/appointment/time/by/patient/id", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "Patient User ID ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "createrUserId", value = "creater User Id  ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/appointment/time/by/patient/id", method = RequestMethod.GET)
	public Object getAppointmenTimeByPatientId(@RequestParam Integer patientUserId,
			@RequestParam Integer createrUserId) {
		return commonService.getAppointmenTimeByPatientId(patientUserId, createrUserId);
	}
	

	@ApiOperation(value = "/bulk/user/mapped/with/multiple/healthgroup", notes = "User Mapped With Health Group", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "jsonData", value = "Json Data", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/bulk/user/mapped/with/multiple/healthgroup", method = RequestMethod.POST)
	public Object jsonUserMappedWithMultipleHealthgroup(@RequestParam String jsonData) {
		return commonService.jsonUserMappedWithMultipleHealthgroup(jsonData);
	}

	@ApiOperation(value = "/get/all/wearable/device", notes = "User Mapped With Health Group", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/all/wearable/device", method = RequestMethod.POST)
	public Object getAllWearableDevice() {
		return commonService.getAllWearableDevice();
	}

	@ApiOperation(value = "/get/chart/data/according/to/user", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "Patient User ID ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "dayno", value = "day no  ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "fromdate", value = "from date no  ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "todate", value = "to date no  ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/chart/data/according/to/user", method = RequestMethod.GET)
	public Object getChartDataAccordingToUser(@RequestParam Integer dayno, @RequestParam Integer patientUserId,
			@RequestParam Integer fromdate, @RequestParam Integer todate) {
		// System.out.println(commonService.getChartDataAccordingToUser(dayno, patientUserId, fromdate, todate).toString());
		 return commonService.getChartDataAccordingToUser(dayno, patientUserId, fromdate, todate);
		//return null;
	}
	
	@ApiOperation(value = "/get/protocol/data/according/to/user", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "Patient User ID ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/protocol/data/according/to/user", method = RequestMethod.GET)
	public Object getProtocolDataAccordingToUser(@RequestParam Integer patientUserId) {
		// System.out.println(commonService.getChartDataAccordingToUser(dayno, patientUserId, fromdate, todate).toString());
		 return commonService.getProtocolDataAccordingToUser(patientUserId);
		//return null;
	}

	// add patient reading
	@ApiOperation(value = "/add/patient/reading", notes = "Add Patient Reading", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "json", value = "Json Format of measurement Data", required = true, access = "query", paramType = "body", dataType = "String")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/patient/reading", method = RequestMethod.POST)
	public Object addPatientReading(@RequestBody String json) {
		Gson g = new Gson(); 
		ReadingModel p[] = g.fromJson( json, ReadingModel[].class);
			return commonService.addPatientReading(p);
	}

	@ApiOperation(value = "/bulk/user/mapped/with/multiple/users", notes = "Add bulk User mapped with users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "json", value = "Json Format of measurement Data", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/bulk/user/mapped/with/multiple/users", method = RequestMethod.POST)
	public Object bulkUserMappedWithMultipleUsers(@RequestParam String json) {
		
		return commonService.bulkUserMappedWithMultipleUsers(json);
	}

	// get device list
	@ApiOperation(value = "/get/device/list", notes = "Add bulk User mapped with users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"), })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/device/list", method = RequestMethod.GET)
	public Object getdeviceList(@RequestHeader(value = "User-Agent") String useragent) {
		String[] splited = useragent.split("\\s+");
		if (useragent.contains("Android") || useragent.contains("iPhone "))
			return commonService.getdeviceList();
		else
			return commonService.notauthorised();
	}

	/*@ApiOperation(value = "/export/pdf", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "Patient User Id ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/export/pdf", method = RequestMethod.POST)
	public Object exportPDF(@ApiIgnore @RequestParam Map<String, String> map) {
		return commonService.exportPDF(map);
	}*/
	
	@ApiOperation(value = "/export/pdf", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "key", value = "Token key  ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "month", value = "Patient User Id ", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "year", value = "Patient User Id ", required = true, access = "query", paramType = "query", dataType = "int")})
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/export/pdf", method = RequestMethod.POST)
	public Object exportPDF(@RequestParam String key, @RequestParam Integer month, @RequestParam Integer year) {
		return commonService.exportPDF(key,month, year);
	}
	
	/** Chron to generate bill every month 1st **/
	@Scheduled(cron = "0 0 0 1 * ?")
	public void bill_generate_chron_job() {
		Date d= new Date();
		Logmanager lm =new Logmanager(String.valueOf(d.getMonth()), commonService.exportPDF("e74a562f-b5ed-11e9-9c6c-00163e11554c",d.getMonth(), (d.getYear()+1900)).toString(), "",String.valueOf(new Date().getTime()), "", "generate_bill");
		lm.initiateChronLogData();
		//commonService.exportPDF("e74a562f-b5ed-11e9-9c6c-00163e11554c",d.getMonth(), (d.getYear()+1900));
	}

	
	// new uploads image api
	
	@ApiOperation(value = "/upload/image", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			})
	@ApiResponses(value = {    
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/upload/image", method = RequestMethod.POST)
	public Object uploadfile( @RequestParam("file") MultipartFile file, @RequestParam String userId) {
		 return commonService.uploadfile(file, userId);
	}

	// new apis

	@ApiOperation(value = "/add/notes/comments", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "notes_id", value = "notes id ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "comments", value = "comments ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "isInternelComment", value = "isCritical ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/notes/comments", method = RequestMethod.POST)
	public Object addNotesComments(@ApiIgnore @RequestParam Map<String, String> map) {
		
		return commonService.addNotesComments(map);
	}

	@ApiOperation(value = "/get/notes/comments", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "notes_id", value = "notes Id ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "comments_id", value = "comments Id ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/notes/comments", method = RequestMethod.GET)
	public Object getNotesComments(@ApiIgnore @RequestParam Map<String, String> map) {
		return commonService.getNotesComments(map);
	}

	@ApiOperation(value = "/get/patient/setting", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "notes Id ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/patient/setting", method = RequestMethod.GET)
	public Object getPatientSetting(@RequestParam Integer patientUserId) {
		return commonService.getPatientSetting(patientUserId);
	}

	@ApiOperation(value = "/update/patient/setting", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "patientUserId", value = "notes Id ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "Setting Type", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "status", value = "Setting Type Status ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/patient/setting", method = RequestMethod.POST)
	public Object updatePatientSetting(@RequestParam Integer patientUserId, @RequestParam String type,
			@RequestParam Integer status) {
		return commonService.updatePatientSetting(patientUserId, type, status);
	}

	@ApiOperation(value = "/add/new/users", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/new/users", method = RequestMethod.POST)
	public Object addNewUsers(@ModelAttribute NewUsers newUsers) {
		return commonService.addNewUsers(newUsers);
	}

	@ApiOperation(value = "/get/all/users/by/user", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "userType", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/all/users/by/user", method = RequestMethod.POST)
	public Object getAllUsersByUser(@RequestParam Integer userId, @RequestParam String userType) {
		return commonService.getAllUsersByUser(userId, userType);
	}

	// new code

	@ApiOperation(value = "/set/timezone/by/admin", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "zone_name", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "admin_id", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/set/timezone/by/admin", method = RequestMethod.POST)
	public Object setTimezone(@RequestParam String zone_name, @RequestParam String admin_id) {
		return commonService.setTimezone(zone_name, admin_id);
	}
	
	@ApiOperation(value = "/set/default/timezone", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "zone_name", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "admin_id", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/set/default/timezone", method = RequestMethod.POST)
	public Object setdefaultTimezone(@RequestParam String zone_name, @RequestParam String admin_id) {
		return commonService.setTimezone(zone_name, admin_id);
	}

	// new code

	@ApiOperation(value = "/get/timezone/by/admin", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "admin_id", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/timezone/by/admin", method = RequestMethod.GET)
	public Object getTimezone(@RequestParam Integer admin_id) {
		return commonService.getTimezone(admin_id);
	}

	// new code

	@ApiOperation(value = "/get/country/by/admin", notes = "Get All Medication", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"), })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/country/by/admin", method = RequestMethod.GET)
	public Object getCountry() {
		return commonService.getCountry();
	}

	// new code

	@ApiOperation(value = "/get/log/hours", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "main_id", value = "practice admin id", required = true, access = "query", paramType = "query", dataType = "String"),
			@ApiImplicitParam(name = "admin_id", value = "doctor/provider id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "patient_id", value = "patient id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "monthno", value = " month no", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "dateno", value = " date no ", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "role_id", value = " role id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "year", value = " role id", required = true, access = "query", paramType = "query", dataType = "int")})
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/log/hours", method = RequestMethod.GET)
	public Object getLoghours(@RequestParam Integer main_id, @RequestParam Integer admin_id,
			@RequestParam Integer patient_id, @RequestParam Integer monthno, @RequestParam Integer dateno,
			@RequestParam Integer role_id, @RequestParam Integer year) {
		return commonService.getLoghours(main_id, admin_id, patient_id, monthno, dateno, role_id, year);
	}
	
	@ApiOperation(value = "/get/log/hours/by/patient", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "year", value = "practice admin id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "admin_id", value = "doctor/provider id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "patient_id", value = "patient id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "monthno", value = " month no", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "dateno", value = " date no ", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/log/hours/by/patient", method = RequestMethod.GET)
	public Object getLoghoursbyPatient(@RequestParam Integer year, @RequestParam Integer admin_id,
			@RequestParam Integer patient_id, @RequestParam Integer monthno, @RequestParam Integer dateno) {
		return commonService.getLoghoursbyPatient(year, admin_id, patient_id, monthno, dateno );
	}
	
	/** Get Log hous filter by all patient month **/
	@ApiOperation(value = "/get/log/hours/by/patient/filter/by/month", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "year", value = "practice admin id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "admin_id", value = "doctor/provider id", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "monthno", value = " month no", required = true, access = "query", paramType = "query", dataType = "int")
			 })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/log/hours/by/patient/filter/by/month", method = RequestMethod.GET)
	public Object getLoghoursbyPatientfilterbyMonth(@RequestParam Integer admin_id, @RequestParam Integer monthno,@RequestParam Integer year
			) {
		return commonService.getLoghoursbyPatientfilterbyMonth(admin_id, monthno, year);
	}
	
	
	
	@ApiOperation(value = "/get/ehc/protocol", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "enddate", value = "endate", required = true, access = "query", paramType = "query", dataType = "int"),
			@ApiImplicitParam(name = "userId", value = "User Id", required = true, access = "query", paramType = "query", dataType = "int")
			 })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/ehc/protocol", method = RequestMethod.GET)
	public Object getEhcprotocol(@RequestParam String enddate, @RequestParam Integer userId) {
		return commonService.getEhcprotocol(enddate, userId);
	}
	
	@ApiOperation(value = "/get/ehc/protocol/by/patient", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "patient_id", value = "Patient Id", required = true, access = "query", paramType = "query", dataType = "int")
			 })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/ehc/protocol/by/patient", method = RequestMethod.GET)
	public Object getEhcprotocolbyPatient(@RequestParam String patient_id) {
		return commonService.getEhcprotocolByPatient(patient_id);
	}
	
	@ApiOperation(value = "/get/previous/protocol/by/patient", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "patient_id", value = "Patient Id", required = true, access = "query", paramType = "query", dataType = "int")
			 })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/previous/protocol/by/patient", method = RequestMethod.GET)
	public Object getPreviousprotocolbyPatient(@RequestParam String patient_id) {
		return commonService.getPreviousprotocolByPatient(patient_id);
	}
	
	
	@ApiOperation(value = "/get/state/list", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({ })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/state/list", method = RequestMethod.GET)
	public Object getStateList() {
		return commonService.getstateList( );
	} 
	
	@ApiOperation(value = "/get/log/data", notes = "Get total Log hours of patient", response = ResponseMessage.class)
	@ApiImplicitParams({ })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/log/data", method = RequestMethod.GET)
	public Object getLogs() {
		return commonService.getLogs();
	}
	
	@ApiOperation(value = "/contact/us", notes = "Contact Us", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "User Name ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "subject", value = "Subject ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "message", value = "message ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "phone", value = "phone ", required = true, access = "query", paramType = "query"), 
			@ApiImplicitParam(name = "email", value = "email ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/contact/us",  method = RequestMethod.POST)
	public Object contactUs (@RequestParam String name, @RequestParam String subject, @RequestParam String message, @RequestParam String phone, @RequestParam String email) {
		return commonService.contactUs(name, subject, message, phone, email);
	}
	
/*	@ApiOperation(value = "/update/protocol/status", notes = "Update Protocol Status", response = ResponseMessage.class)

	@ApiResponses(value = { @ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/protocol/status",  method = RequestMethod.GET)*/
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void update_protocol_status () {
		Logmanager lm =new Logmanager("no params", commonService.update_protocol_status().toString(), "",String.valueOf(new Date().getTime()), "", "protocol");
		lm.initiateChronLogData();
		// commonService.update_protocol_status();
	}
}
