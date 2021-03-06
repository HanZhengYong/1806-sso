package com.jt.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.ObjectUtil;
import com.jt.common.vo.SysResult;
import com.jt.sso.service.UserService;

@Controller
public class QueryUserByTicket {
	@Autowired
	UserService userService;

	@RequestMapping("user/query/{ticket}")
	@ResponseBody
	public String queryUserByTicket(@PathVariable String ticket, String callback) throws Exception {
		String userJson = userService.queryUserJson(ticket);
		if (callback == null) {
			return ObjectUtil.mapper.writeValueAsString(SysResult.oK(userJson));
		}

		return callback + "(" + ObjectUtil.mapper.writeValueAsString(SysResult.oK(userJson)) + ")";

	}
}
