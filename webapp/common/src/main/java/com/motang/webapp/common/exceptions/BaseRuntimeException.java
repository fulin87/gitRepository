package com.motang.webapp.common.exceptions;

/**
 * 运行时系统异常父类
 * 
 * @since version1.0
 */
public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 6835064766067430918L;
	
	private String code;

	public BaseRuntimeException() {
		super();
	}

	public BaseRuntimeException(String code, Throwable cause) {
		this(code, code, cause);
	}

	public BaseRuntimeException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public BaseRuntimeException(String code) {
		this(code, code);
	}

	public BaseRuntimeException(String code, String message) {
		super(message);
		this.code = code;
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}

	public String getCode() {
		return code;
	}

}
