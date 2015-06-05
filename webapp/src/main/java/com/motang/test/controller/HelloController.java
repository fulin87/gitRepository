package com.motang.test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.motang.test.service.ProductService;

public class HelloController implements Controller{
    protected final Logger logger = Logger.getLogger(HelloController.class);
    
    private ProductService productService;
    
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String now = (new Date()).toString();
        
        logger.info("Returning hello view");

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        myModel.put("products", this.productService.getProducts());
        
        return new ModelAndView("hello.jsp", "model", myModel);
    }

}
