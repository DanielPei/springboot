package com.daniel.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class RandomUtils {
	
	/**
	 * 生成安全随机数，可用于生成用户私有盐
	 * @return "6b4f89a54e2d27ecd7e8da05b4ab8fd9"
	 */
	public static String getSecureRandom(){
		return new SecureRandomNumberGenerator().nextBytes().toHex();
	}
	
	/**
	 * 生成安全随机数，可用于生成用户私有盐
	 *  相同的seed的返回值相同
	 * @param seed 随机数种子值
	 * @return
	 */
	public static String getSecureRandom(String seed){
		SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();  
		randomNumberGenerator.setSeed(seed.getBytes());  
		return randomNumberGenerator.nextBytes().toHex();  
	}
	
	public static void main(String[] args) {
//		String seed = "hello";
//		System.out.println(getSecureRandom(seed));
//		System.out.println(getSecureRandom());
//		System.out.println(getSecureRandom(seed));
//		System.out.println(getSecureRandom());
		
	    //得到盐值加密后的密码：只用于方便数据库测试，后期不会用到。
		System.out.println(org.apache.shiro.crypto.hash.Sha256Hash.ALGORITHM_NAME);
	    Object md = new SimpleHash(org.apache.shiro.crypto.hash.Sha256Hash.ALGORITHM_NAME,"hello","helloaaa",2);
//	    Object md = new SimpleHash("SHA1","hello","helloaaa",2);
	    System.out.println("盐值加密后的密码："+md);
	}
	

}
