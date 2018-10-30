package com.jt.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.redis.RedisService;
import com.jt.sso.utils.RandomCode;
import com.jt.sso.utils.SendValidateCode;
@Service
public class ValidateService {
	@Autowired
	RedisService redis;
	
	public int sendValidate(String phone) throws Exception {
		int code=RandomCode.getValidateCode();
		//int returnValue=SendValidateCode.send(code, phone);
		String codeValue=code+"";
		redis.set("validate"+code, codeValue);
		return 200;
	}

}
