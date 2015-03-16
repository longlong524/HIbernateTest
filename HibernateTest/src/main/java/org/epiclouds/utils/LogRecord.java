/**
 * @author Administrator
 * @created 2015 2015年1月19日 下午3:16:10
 * @version 1.0
 */
package org.epiclouds.utils;


import org.apache.log4j.Logger;

public class LogRecord {
	private static Logger info = Logger.getLogger("InfoLogger");
	private static Logger error = Logger.getLogger("ErrorLogger");
	public LogRecord(){}
	
	/**
	 * 一般情况记录到/logs/infoLog.txt
	 */
	public static void info(Throwable infomation){
		info.info(infomation);
	}
	
	/**
	 * 错误信息记录到/logs/errorLog.txt
	 */
	public static void error(Throwable infomation){
		error.error(infomation);
	}
}
