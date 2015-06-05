package com.motang.webapp.common.domain;

/**
 * 统一返回json的结果封装类
 * 
 * @author motang
 * @since version1.0 
 */
public class JsonResult<T> {
	private String code;
	
	private String message;
	
	private String timestamp;
	
	private T data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
