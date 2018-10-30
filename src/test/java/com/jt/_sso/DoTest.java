package com.jt._sso;

import org.junit.Test;

import com.jt.sso.utils.RandomCode;
import com.jt.sso.utils.SendValidateCode;

public class DoTest {
	@Test
	public void test01() throws Exception{
		int code=RandomCode.getValidateCode();
		String phone="18702506365";
		SendValidateCode.send(code, phone);
		
	}
}
