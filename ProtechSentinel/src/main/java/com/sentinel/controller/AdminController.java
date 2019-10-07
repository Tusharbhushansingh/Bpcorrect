package com.sentinel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinel.model.HealthGroupDetails;
import com.sentinel.model.PracticeMappingOrg;
import com.sentinel.model.UpdateHealthGroupDetails;
import com.sentinel.model.WearableDevice;
import com.sentinel.response.ResponseMessage;
import com.sentinel.service.AdminService;
import com.sentinel.service.ProviderService;
import com.sentinel.swagger.model.ResponseObject;

@RestController
@RequestMapping("/admin")
@Api(value = "/admin", description = "Common Controller")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	ProviderService providerService;

	
	@ApiOperation(value = "/add/wearable/device", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/wearable/device", method = RequestMethod.POST)
	public Object addWearableDevice(
			@ModelAttribute WearableDevice.AddWearableDevice addWearableDevice) {
		return adminService.addWearableDevice(addWearableDevice);
	}

	@ApiOperation(value = "/update/wearable/device", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/wearable/device", method = RequestMethod.POST)
	public Object updateWearableDevice(
			@ModelAttribute WearableDevice.UpdateWearableDevice updateWearableDevice) {
		return adminService.updateWearableDevice(updateWearableDevice);
	}

	@ApiOperation(value = "/mapping/patient/with/wearable/device", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/mapping/patient/with/wearable/device", method = RequestMethod.POST)
	public Object mappingPatientWithDevice(@RequestParam Integer patientUserId,
			@RequestParam Integer deviceId, @RequestParam Integer createrUserId) {
		return adminService.mappingPatientWithDevice(patientUserId, deviceId,
				createrUserId);
	}

	@ApiOperation(value = "/get/patient/wearable/device/by/patient/id", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/patient/wearable/device/by/patient/id", method = RequestMethod.GET)
	public Object getPatientWearableDeviceByPatientId(
			@RequestParam Integer userId) {
		return adminService.getPatientWearableDeviceByPatientId(userId);
	}
	
	@ApiOperation(value = "/add/update/coupon", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/update/coupon", method = RequestMethod.POST)
	public Object addupdateCoupon(
			@RequestParam Integer coupon_id, @RequestParam String code, @RequestParam String startdate, @RequestParam String enddate, @RequestParam Integer discount, 
			@RequestParam Integer status  ) {
		return adminService.addupdatecoupon(coupon_id,code,startdate, enddate, discount,status);
	}
	
//	
	@ApiOperation(value = "/get/coupon/list", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/coupon/list", method = RequestMethod.GET)
	public Object getcouponList(
			@RequestParam Integer coupon_id, @RequestParam Integer admin_id ) {
		return adminService.getcoupon(coupon_id, admin_id);
	}

	@ApiOperation(value = "/add/healthgroup/by/sentinel/admin", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/healthgroup/by/sentinel/admin", method = RequestMethod.POST)
	public Object addHealthgroupBySentinelAdmin(
			@ModelAttribute HealthGroupDetails healthGroupDetails) {
		return adminService.addHealthgroupBySentinelAdmin(healthGroupDetails);
	}
	
	// new code to set charge amount
	
	@ApiOperation(value = "/add/charge/amount", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/add/charge/amount", method = RequestMethod.POST)
	public Object addchargeamount(
			@RequestParam Integer amount) {
		return adminService.addchargeamount(amount);
	}
	
	@ApiOperation(value = "/get/charge/amount", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/charge/amount", method = RequestMethod.GET)
	public Object getchargeamount() {
		return adminService.getchargeamount();
	}
	
	@ApiOperation(value = "/get/all/payment/record", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") 
			})
	@RequestMapping(value = "/get/all/payment/record", method = RequestMethod.GET)
	public Object getallpaymentrecord(@RequestParam Integer id, @RequestParam Integer monthno, @RequestParam Integer year) {
		return adminService.getallpaymentrecord(id, monthno, year);
	}
	
	@ApiOperation(value = "/activate/deactivate/coupon", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/activate/deactivate/coupon", method = RequestMethod.POST)
	public Object activate_deactivate(
			@RequestParam Integer id, @RequestParam Integer status) {
		return adminService.activatedeactivatecoupon(id, status);
	}

	@ApiOperation(value = "/update/healthgroup/by/sentinel/admin", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/update/healthgroup/by/sentinel/admin", method = RequestMethod.POST)
	public Object updateHealthgroupBySentinelAdmin(
			@ModelAttribute UpdateHealthGroupDetails updateHealthGroupDetails) {
		return adminService
				.updateHealthgroupBySentinelAdmin(updateHealthGroupDetails);
	}

	@ApiOperation(value = "/get/healthgroup/by/sentinel/admin", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "adminuserId", value = "Admin User Id ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/healthgroup/by/sentinel/admin", method = RequestMethod.GET)
	public Object getHealthgroupBySentinelAdmin(
			@RequestParam Integer adminuserId) {
		return adminService.getHealthgroupBySentinelAdmin(adminuserId);
	}

	@ApiOperation(value = "/get/superadmin/chart", notes = "Add Notes of users", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "startdate", value = "Start date ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "enddate", value = "End date ", required = true, access = "query", paramType = "query")})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/superadmin/chart", method = RequestMethod.GET)
	public Object getChartbyAdmin(
			@RequestParam Integer startdate, @RequestParam Integer enddate) {
		return adminService.getChartbyAdmin(startdate, enddate);
	}
	
	
	@ApiOperation(value = "/mapping/practice/admin", notes = "Add Notes of users", response = ResponseMessage.class)
	
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/mapping/practice/admin", method = RequestMethod.POST)
	public Object mapPracticeAdmin(
			@ModelAttribute PracticeMappingOrg practicemappingorg) {
		return adminService.practiceorgmapping(practicemappingorg);
	}
	
	@ApiOperation(value = "/get/all/practice/admin", notes = "Get All Providers By provider", response = ResponseObject.getProviderInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "Users user id", required = true, access = "query", paramType = "query", dataType = "int") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/all/practice/admin", method = RequestMethod.GET)
	public Object getAllPracticeOrg(@RequestParam Integer userId) {
		return adminService.getAllPracticeAdmin(userId);
	}
	
	@ApiOperation(value = "/get/quickStats/by/admin", notes = "Get All Providers By provider", response = ResponseObject.getProviderInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "userid", value = "Users user id", required = true, access = "query", paramType = "query", dataType = "int")

	 })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/quickStats/by/admin", method = RequestMethod.GET)
	public Object getquickStats(@RequestParam int userid) {
		return adminService.getQuickStats(userid);
	}
	
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
 	public Object getAllPatientBySuperAdmin(@RequestParam Integer userId, Integer admin_id) {
 		return providerService.getAllPatientBySuperAdmin(userId, admin_id);
 	}
}
