package com.jt.sso.utils;

import java.util.Random;

public class RandomCode {
	public static int getValidateCode(){
		Random random=new Random();
		int number=random.nextInt(8999)+1000;
		return number;
		
	}
}
