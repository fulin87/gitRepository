package com.motang.webapp.web.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.motang.webapp.common.base.SystemConstants;
import com.motang.webapp.common.domain.JsonResult;
import com.motang.webapp.common.exceptions.BaseCheckedException;
import com.motang.webapp.common.exceptions.BaseRuntimeException;
import com.motang.webapp.common.utils.JsonResultUtils;

@ControllerAdvice
public class SystemExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(value={Throwable.class})
	@ResponseBody
	public JsonResult<?> returnNoPage(Throwable ex) {
		logger.error("", ex);
		
		String message = ex.getMessage();
		if (ex instanceof BaseRuntimeException) {
			String code = ((BaseRuntimeException) ex).getCode();
			message = messageSource.getMessage(code, null, null);
		} else if (ex instanceof BaseCheckedException) {
			String code = ((BaseCheckedException) ex).getCode();
			message = messageSource.getMessage(code, null, null);
		}
		
		JsonResult<?> result = JsonResultUtils.getJsonResult(null, SystemConstants.RESPONSE_STATUS_FAILURE, message);
		return result;
	}

}
