package com.motang.webapp.web.plugin.view;

import java.util.Map;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.motang.webapp.common.base.SystemConstants;
import com.motang.webapp.common.domain.JsonResult;
import com.motang.webapp.common.utils.JsonResultUtils;

/**
 * @since version1.0 
 */
public class CustomizeFastJsonJsonView extends FastJsonJsonView {
	@Override
	protected Object filterModel(Map<String, Object> model) {
		Object value = super.filterModel(model);
		if (value != null && !(value instanceof JsonResult)) {
			value = JsonResultUtils.getJsonResult(value, SystemConstants.RESPONSE_STATUS_SUCCESS, null);
		}
		
		return value;
	}
}
