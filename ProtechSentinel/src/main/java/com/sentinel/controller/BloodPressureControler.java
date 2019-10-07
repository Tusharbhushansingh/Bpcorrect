/*package com.sentinel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.reflect.TypeToken;
import com.sentinel.model.BloodPresureFullStatistic;
import com.sentinel.model.BloodPresureStatistic;
import com.sentinel.service.BloodPressureService;
import com.sentinel.swagger.model.ResponseObject;

@Controller
@RequestMapping(value = "/bloodpresure")
@Api(value = "/bloodpresure", description = "BloodPresure Controller")
public class BloodPressureControler {

	@Autowired
	BloodPressureService bloodPressureService;

	static final class BloodPresureFullStatisticType extends BloodPresureFullStatistic<BloodPresureStatistic> {}
	
	public BloodPressureControler() {

	}

	@ApiOperation(value = "/userBloodPresure", notes = "Blood Presure Full Statistic", response = BloodPresureFullStatisticType.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/userBloodPresure", method = RequestMethod.POST)
	public @ResponseBody String bloodPresureFullStatistic() {
		return bloodPressureService.bloodPresureFullStatistic();

	}

	@ApiOperation(value = "/lastweek", notes = "Last Week Blood Presure", response = ResponseObject.MidleBloodPresure.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/lastweek", method = RequestMethod.POST)
	public @ResponseBody String lastWeekBloodPresure() {
		return bloodPressureService.lastWeekBloodPresure();

	}

	@ApiOperation(value = "/userBloodPresureFromId", notes = "User Blood Presure From Id", response = ResponseObject.BloodPresureUserData.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/userBloodPresureFromId", method = RequestMethod.POST)
	public @ResponseBody String userBloodPresureFromId(@RequestParam Integer id) {
		return bloodPressureService.bloodPresureFromId(id);

	}

}
*/