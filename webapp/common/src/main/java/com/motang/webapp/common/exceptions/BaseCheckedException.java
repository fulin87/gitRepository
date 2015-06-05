package com.motang.webapp.common.exceptions;

/**
 * 编译时异常系统父类
 * 
 * @since version1.0 
 */
public class BaseCheckedException extends Exception {

	private static final long serialVersionUID = -6711610611939453971L;
	
	private String code;
	
	public BaseCheckedException() {
		super();
	}

	public BaseCheckedException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}
	
	public BaseCheckedException(String code, String message) {
		super(message);
		this.code = code;
	}
	
	public BaseCheckedException(String code, Throwable cause) {
		this(code, code, cause);
	}
	
	public BaseCheckedException(String code) {
		this(code, code);
	}

	public BaseCheckedException(Throwable cause) {
		super(cause);
	}

	public String getCode() {
		return code;
	}

}
