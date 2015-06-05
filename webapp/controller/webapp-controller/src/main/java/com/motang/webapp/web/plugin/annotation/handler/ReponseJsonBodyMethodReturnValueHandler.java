package com.motang.webapp.web.plugin.annotation.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.motang.webapp.common.base.SystemConstants;
import com.motang.webapp.common.domain.JsonResult;
import com.motang.webapp.common.utils.JsonResultUtils;
import com.motang.webapp.web.plugin.annotation.ResponseJsonBody;

/**
 * 支持ResponseJsonBody注解的返回值handler
 * 
 * @since version1.0
 */
public class ReponseJsonBodyMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

	public boolean supportsReturnType(MethodParameter returnType) {
		return returnType.getMethodAnnotation(ResponseJsonBody.class) != null;
	}

	public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		this.messageConverters.addAll(messageConverters);
	}

	@SuppressWarnings({ "resource", "unchecked" })
	public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
		mavContainer.setRequestHandled(true);
		if (returnValue != null) {
			HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
			ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(response);
			Class<?> returnValueClass = returnValue.getClass();
			
			for (HttpMessageConverter<?> messageConverter : messageConverters) {
				if (messageConverter.canWrite(returnValueClass, MediaType.APPLICATION_JSON)) {
					Object result = returnValue;
					if (!(returnValue instanceof JsonResult)) {
						result = JsonResultUtils.getJsonResult(returnValue, SystemConstants.RESPONSE_STATUS_SUCCESS, null);
					}

					((HttpMessageConverter<Object>) messageConverter).write(result, MediaType.APPLICATION_JSON, outputMessage);
					if (logger.isDebugEnabled()) {
						logger.debug("Written [{}] as \"{}\" using [{}]", new Object[]{returnValue, MediaType.APPLICATION_JSON, messageConverter});
					}

					return;
				}
			}
		}
	}

}
