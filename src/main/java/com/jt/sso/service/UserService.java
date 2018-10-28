package com.jt.sso.service;

import java.io.StringWriter;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.redis.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;

	public SysResult checkParam(String param, Integer type) {

		User _user = new User();
		Integer count = 0;
		if (type == 1) {
			_user.setUsername(param);
			count = userMapper.selectCount(_user);
		} else {
			_user.setPhone(param);
			count = userMapper.selectCount(_user);
		}
		if (count == 0) {
			return SysResult.oK(false);
		} else {
			return SysResult.oK(true);
		}
	}

	public void saveUser(User user) {
		user.setEmail(user.getUsername());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userMapper.insertSelective(user);

	}

	@Autowired
	RedisService redis;

	public String doLogin(String username, String password) throws Exception {
		User _user = new User();
		_user.setUsername(username);
		_user.setPassword(DigestUtils.md5Hex(password));
		User user = userMapper.selectOne(_user);
		if (user != null) {
			String ticket = DigestUtils.md5Hex("JT_TICKET_" + System.currentTimeMillis() + username);

			String userJson = new ObjectMapper().writeValueAsString(user);

			redis.set(ticket, userJson);
			return ticket;
			
		}
		return "";
	}

	public String queryUserJson(String ticket) {
		return redis.get(ticket);

	}

}
