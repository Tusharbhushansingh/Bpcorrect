package com.sentinel.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sentinel.response.ResponseMessage;
import com.sentinel.service.BasicService;

@RestController
@RequestMapping("/basic")
@Api(value = "/basic", description = "Basic Operation Controller")
public class BasicController {

	@Autowired
	private BasicService basicService;

	@ApiOperation(value = "/forgot/password", notes = "Forgot Password", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "email", value = "User Email Id", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/forgot/password", method = RequestMethod.POST)
	public Object forgotPassword(@RequestParam String email) {
		return basicService.forgotPassword(email);
	}
	
	@Resource(name="tokenServices")
	ConsumerTokenServices tokenServices;
	@ApiOperation(value = "/tokens/revoke/{tokenId:.*}", notes = "Add Notes of users", response = ResponseMessage.class)    
	@RequestMapping(method = RequestMethod.POST, value = "/tokens/revoke/{tokenId:.*}")
	@ResponseBody
	public String revokeToken(@PathVariable String tokenId) {
	    tokenServices.revokeToken(tokenId);
	    return tokenId;
	}

	@ApiOperation(value = "/reset/password", notes = "Forgot Password", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "User Email Id", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "User Email Id", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/reset/password", method = RequestMethod.POST)
	public Object resetPassword(@RequestParam String code,
			@RequestParam String password) {
		return basicService.resetPassword(code, password);
	}

	@ApiOperation(value = "/verify/email", notes = "Verify Email", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query"),
			@ApiImplicitParam(name = "email", value = "User Email Id", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/verify/email", method = RequestMethod.GET)
	public Object verifyEmail(@RequestParam String email) {
		return basicService.verifyEmail(email);
	}
	
	// New Api
	@ApiOperation(value = "/check/email", notes = "Verify Email", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "email", value = "User code", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/check/email", method = RequestMethod.GET)
	public Object emailExist(@RequestParam String email) {
		return basicService.emailExists(email);
	}
	
	// New Api
	@ApiOperation(value = "/activate/account", notes = "Verify Email", response = ResponseMessage.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "User code", required = true, access = "query", paramType = "query")

	})
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/activate/account", method = RequestMethod.POST)
	public Object checkEmail(@RequestParam String code) {
		return basicService.verifyEmail(code);
	}

	@ApiOperation(value = "/logout", notes = "Logout Service", response = ResponseMessage.class)
	@ApiImplicitParams({ @ApiImplicitParam(name = "access_token", value = "Token is generated to access API ", required = true, access = "query", paramType = "query") })
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Get The Response Code"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public Object Logout(@RequestParam String access_token) {
		return basicService.Logout(access_token);
	}

}
