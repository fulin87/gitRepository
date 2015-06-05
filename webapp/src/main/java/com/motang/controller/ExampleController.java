package com.motang.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ExampleController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		// 添加模型数据 可以是任意的POJO对象
		mv.addObject("message", "Hello World!====");
		ModelMap model = mv.getModelMap();
		model.addAttribute("message", "Hello World!");
		// 设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面
		mv.setViewName("example");
		return mv;
	}

}
