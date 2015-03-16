/**
 * @author Administrator
 * @created 2015 2015年1月21日 下午8:03:15
 * @version 1.0
 */
package org.epiclouds.utils;

import java.util.Random;

/**
 * @author Administrator
 *
 */
public class RandomCaptcha {
	public static final String CHS=new String("abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	public static final int CAPTCHALENGTH=4;
	public static String getRandomCaptcha(){
		char[] s=new char[CAPTCHALENGTH];
		Random r=new Random();
		for(int i=0;i<CAPTCHALENGTH;i++){
			s[i]=CHS.charAt(r.nextInt(CHS.length()));
		}
		return new String(s);
	}
}