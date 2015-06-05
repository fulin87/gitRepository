package com.motang.webapp.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtils {
	private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static String DateToStr(Date date) {
		if (date == null) {
			return null;
		}
		return DateToStr(date, FORMAT);
	}
	
	/**
	 * 日期转指定格式字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String DateToStr(Date date, String format) {
		if (date == null) {
			return null;
		}
		if (StringUtils.isEmpty(format)) {
			return null;
		}
		String sRet = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(format);
			sRet = formatter.format(date).toString();
		} catch (Exception ex) {
			sRet = null;
		}
		return sRet;
	}
}
