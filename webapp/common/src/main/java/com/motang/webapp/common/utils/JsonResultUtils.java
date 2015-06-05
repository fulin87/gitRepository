package com.motang.webapp.common.utils;

import java.util.Date;

import com.motang.webapp.common.domain.JsonResult;


public final class JsonResultUtils {

	public static <T> JsonResult<T> getJsonResult(T data, String code, String message) {
		JsonResult<T> result = new JsonResult<T>();
		result.setCode(code);
		result.setData(data);
		result.setMessage(message);
		result.setTimestamp(DateUtils.DateToStr(new Date()));
		
		return result;
	}
}
