package com.motang.webapp.web.controller.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.motang.webapp.domain.test.TestVo;

/**
 * Test controller for testing
 * 
 * @author motang
 * @since version1.0 
 */
@RequestMapping("/test")
@Controller
public class TestController {
	
	@Autowired
	public Validator validator;

	//URL is /test/test, will show jsp/test/test.jsp
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public void test() {
		
	}
	
	//URL is /test/testJson.json, will response a JSON data with Map
	@RequestMapping(value="/testJson", method=RequestMethod.GET)
	public Map<String, Object> testJson() {
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("int", 123456);
		value.put("double", 123456.01d);
		value.put("float", 123456.01f);
		value.put("string", "test");
		value.put("date", new Date());
		value.put("sqlDate", new java.sql.Date(new Date().getTime()));
		value.put("map", value);
		
		TestVo vo = new TestVo();
		vo.setId("TestVoId");
		vo.setDate(new Date());
		vo.setName("TestVoName");
		value.put("bean", vo);
		
		return value;
	}
	
	@RequestMapping("/validate")
	public void validate(TestVo vo, BindingResult bindingResult) {//, Errors errors) {
		Set<ConstraintViolation<TestVo>> validateProperty = validator.validate(vo);//vo所有的
		//Set<ConstraintViolation<TestVo>> validateProperty = validator.validateProperty(vo, "id");//只验证vo中的id字段
		if (validateProperty != null && validateProperty.size() > 0) {
			for (ConstraintViolation<TestVo> constraintViolation : validateProperty) {
				String message = constraintViolation.getMessage();
				
				bindingResult.reject(message, String.format("%s为空", constraintViolation.getPropertyPath().toString()));
				//errors.reject(message, "id为空");
			}
		}
		
	}
	
}
