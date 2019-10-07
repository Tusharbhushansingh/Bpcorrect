package com.sentinel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sentinel.service.UsersInformationService;
import com.sentinel.swagger.model.ResponseObject;


@RestController
@Api(value = "/", description = "Provider Controller")
public class UsersInformationController {
	
	@Autowired
	private UsersInformationService usersInformationService;
	
	@ApiOperation(value = "/get/users/basic/details", notes = "Get All Users Basic Details", response = ResponseObject.GetUserInfo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),

			@ApiImplicitParam(name = "userId", value = "Users user id", required = true, access = "query", paramType = "query", dataType = "String") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/get/users/basic/details", method = RequestMethod.GET)
	public Object getUsersBasicDetails(@RequestParam Integer userId) {
		return usersInformationService.getUsersBasicDetails(userId);
	}
	
}
