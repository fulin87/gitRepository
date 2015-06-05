package com.motang.webapp.web.plugin.messageconverter;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.motang.webapp.common.base.SystemConstants;
import com.motang.webapp.common.domain.JsonResult;
import com.motang.webapp.common.utils.JsonResultUtils;

/**
 * 返回统一的json格式
 * @see JsonResult
 * 
 * @since version1.0
 */
public class CustomizeFastJsonHttpMessageConverter extends FastJsonHttpMessageConverter {
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		Object value = obj;
		if (obj != null && !(obj instanceof JsonResult)) {
			value = JsonResultUtils.getJsonResult(obj, SystemConstants.RESPONSE_STATUS_SUCCESS, null);
		}
		
		OutputStream out = outputMessage.getBody();
		String text = JSON.toJSONString(value, getFeatures());
		byte[] bytes = text.getBytes(getCharset());
		out.write(bytes);
	}
}
