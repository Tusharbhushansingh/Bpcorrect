package com.sentinel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinel.model.HealthGroupDetails;
import com.sentinel.model.PracticeAdmin;
import com.sentinel.model.UserFullModel;
import com.sentinel.model.UserFullModelIsSentinel;
import com.sentinel.response.ResponseMessage;
import com.sentinel.service.AdminService;
import com.sentinel.service.CommonService;
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
@RequestMapping("/practiceadmin")
@Api(value = "/practiceadmin", description = "Practice Controller")

public class PracticeAdminController {
	@Autowired
	private CommonService commonService;

	@Autowired
	private AdminService adminService;
	@Autowired
	ProviderService providerService;
	@Autowired
	PracticeAdminService practiceAdminService;

     public PracticeAdminController() {
		// TODO Auto-generated constructor stub
	}
     
     /**
 	 * 
 	 * @param userId
 	 * @return
 	 */
 	@ApiOperation(value = "/get/all/patient/by/practiceadmin", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
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
 	@RequestMapping(value = "/get/all/patient/by/practiceadmin", method = RequestMethod.GET)
 	public Object getAllPatientByProvider(@RequestParam Integer userId, Integer admin_id) {
 		return providerService.getAllPatientByProvider(userId, admin_id);
 	}
 	
 	
 	@ApiOperation(value = "/apply/coupon", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
 	@ApiImplicitParams({
 			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

 			@ApiImplicitParam(name = "code", value = "patient user id", required = true, access = "query", paramType = "query")})
 	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/apply/coupon", method = RequestMethod.POST)
 	public Object getAllPatientByProvider(@RequestParam String code) {
 		return providerService.applycoupon(code);
 	}
 	
 	
 	
 // New APIs

 	@ApiOperation(value = "/add/practice/admin", notes = "Add Admin Practice", response = ResponseMessage.class)
 	@ApiImplicitParams({ 
 		@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
 	// @ApiImplicitParam(name = "healthemail", value = "health email id", required = true, access = "query", paramType = "query", dataType = "String")
 	})
 	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/add/practice/admin", method = RequestMethod.POST)
 	public Object addAdminProvider(
 			@ModelAttribute PracticeAdmin practiceAdmin ) {
 		return commonService.register_practice_admin(practiceAdmin);
 	}
 	
 	@ApiOperation(value = "/register/healthgroup/by/admin", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/register/healthgroup/by/admin", method = RequestMethod.POST)
	public Object addHealthgroupBySentinelAdmin(
			@ModelAttribute HealthGroupDetails healthGroupDetails) {
		return adminService.addHealthgroupBySentinelAdmin(healthGroupDetails);
	}
 	
 	@ApiOperation(value = "/register/practice/admin", notes = "Add Admin Practice", response = ResponseMessage.class)
 	@ApiImplicitParams({
 	// @ApiImplicitParam(name = "healthemail", value = "health email id", required = true, access = "query", paramType = "query", dataType = "String") 
 	})
 	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/register/practice/admin", method = RequestMethod.POST)
 	public Object registerAdminProvider(
 			@ModelAttribute PracticeAdmin practiceAdmin ) {
 		return commonService.register_practice_admin(practiceAdmin);
 	}
 	
 	@ApiOperation(value = "/add/provider", notes = "Add Provider", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/provider", method = RequestMethod.POST)
	public Object addProvider(@ModelAttribute UserFullModel userFullModel) {
		return commonService.addProvider(userFullModel);
	}
	
	@ApiOperation(value = "/get/all/provider/by/practiceadmin", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
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
 	@RequestMapping(value = "/get/all/provider/by/practiceadmin", method = RequestMethod.GET)
 	public Object getAllProviderByAdmin(@RequestParam Integer userId, Integer adminId) {
 		return practiceAdminService.getAllProviderByAdmin(userId,adminId);
 	}
	
	@ApiOperation(value = "/get/practice/admin/chart", notes = "Get All patient list", response = ResponseObject.GetAllpatient.class)
 	@ApiImplicitParams({
 			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

 			@ApiImplicitParam(name = "userId", value = "provider user id", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "startdate", value = "practice admin id", required = true, access = "query", paramType = "query", dataType = "int"),
 			@ApiImplicitParam(name = "enddate", value = "end date", required = true, access = "query", paramType = "query", dataType = "int")})
 
	@ApiResponses(value = {
 			@ApiResponse(code = 201, message = "Get The Response Code"),
 			@ApiResponse(code = 400, message = "Bad Request"),
 			@ApiResponse(code = 403, message = "Forbidden"),
 			@ApiResponse(code = 500, message = "Internal Server Error"),
 			@ApiResponse(code = 401, message = "Unauthorized") })
 	@RequestMapping(value = "/get/practice/admin/chart", method = RequestMethod.GET)
 	public Object getPracticeAdminChart(@RequestParam Integer userId,@RequestParam Integer startdate,@RequestParam Integer enddate) {
 		return practiceAdminService.getPracticeAdminChart(userId,startdate, enddate);
 	}
	
	
	@ApiOperation(value = "/get/all/staff/by/practiceadmin", notes = "Get All STAFF list", response = ResponseObject.GetAllpatient.class)
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
 	@RequestMapping(value = "/get/all/staff/by/practiceadmin", method = RequestMethod.GET)
 	public Object getAllstaffByAdmin(@RequestParam Integer userId, Integer adminId) {
 		return practiceAdminService.getAllstaffByAdmin(userId,adminId);
 	}

}
