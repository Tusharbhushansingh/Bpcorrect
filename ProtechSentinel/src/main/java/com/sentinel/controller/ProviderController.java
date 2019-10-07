package com.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinel.service.PracticeAdminService;
import com.sentinel.service.ProviderService;
import com.sentinel.swagger.model.ResponseObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/provider")
@Api(value = "/provider", description = "Provider Controller")
public class ProviderController {

	@Autowired
	ProviderService providerService;
	@Autowired
	PracticeAdminService practiceAdminService;

	public ProviderController() {

	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	@ApiOperation(value = "/get/all/patient/by/provider", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "provider user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/all/patient/by/provider", method = RequestMethod.GET)
	public Object getAllPatientByProvider(@RequestParam Integer userId) {
		return providerService.getAllPatientByProvider(userId, 2);
	}
	
	@ApiOperation(value = "/get/common/staff/by/provider", notes = "Get All STAFF list", response = ResponseObject.GetAllpatient.class)
 	@ApiImplicitParams({
 			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

 			@ApiImplicitParam(name = "userId", value = "provider user id", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "adminId", value = "practice admin id", required = true, access = "query", paramType = "query", dataType = "int")})
 
	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/get/common/staff/by/provider", method = RequestMethod.GET)
 	public Object getAllstaffByAdmin(@RequestParam Integer userId, Integer adminId) {
 		return providerService.getCommonstaffByProvider(userId,adminId);
 	}
	
	
	@ApiOperation(value = "/get/all/providers/by/provider", notes = "Get All Providers By provider", response = ResponseObject.getProviderInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "Users user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/all/providers/by/provider", method = RequestMethod.GET)
	public Object getAllProvidersByProvider(@RequestParam Integer userId) {
		return providerService.getAllProvidersByProvider(userId);
	}

	
	@ApiOperation(value = "/get/blood/pressure/by/patient", notes = "Get Blood Pressure By Patient", response = ResponseObject.GetBloodPressureByPatient.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "patientUserId", value = "provider patient user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/blood/pressure/by/patient", method = RequestMethod.GET)
	public Object getBloodPressureByPatient(@RequestParam Integer patientUserId) {
		return providerService.getBloodPressureByPatient(patientUserId);
	}
	
	@ApiOperation(value = "/get/assigned/patient/by/provider", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
 	@ApiImplicitParams({
 			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

 			@ApiImplicitParam(name = "userId", value = "patient user id", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "admin_id", value = "practice user id", required = true, access = "query", paramType = "query", dataType = "int")})
 	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/get/assigned/patient/by/provider", method = RequestMethod.GET)
 	public Object getAllPatientBySuperAdmin(@RequestParam Integer userId, Integer admin_id) {
 		return providerService.getCommonPatientBySuperProvider(userId, admin_id);
 	}
	
	
	@ApiOperation(value = "/add/patient/loghours", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
 	@ApiImplicitParams({
 			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

 			@ApiImplicitParam(name = "userId", value = " user id", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "admin_id", value = "admin id", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "inTime", value = "intime", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "outTime", value = "outtime", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "time", value = "time", required = true, access = "query", paramType = "query", dataType = "Double"),
 			@ApiImplicitParam(name = "day", value = "day", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "month", value = "month", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "year", value = "year", required = true, access = "query", paramType = "query", dataType = "int"),})
 	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/add/patient/loghours", method = RequestMethod.POST)
 	public Object addPatientLoghours(@RequestParam Integer userId, Integer admin_id, String inTime, String outTime, Double time, Integer day, Integer month, Integer year) {
 		return providerService.addPatientLoghours(userId, admin_id, inTime, outTime, time, day, month, year);
 	}

}
