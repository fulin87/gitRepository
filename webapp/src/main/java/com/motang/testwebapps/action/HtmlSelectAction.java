package com.motang.testwebapps.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.motang.testwebapps.vo.LabelValueBean;
import com.opensymphony.xwork2.ActionSupport;

public class HtmlSelectAction extends ActionSupport{
private Map<String, List<LabelValueBean>> mapValues = null;
	
	private String insuredProvince = "Testomg";
	
	@Override
	public String execute() throws Exception {
		mapValues = new HashMap<String, List<LabelValueBean>>();
		
		List<LabelValueBean> test1 = new ArrayList<LabelValueBean>();
		test1.add(new LabelValueBean("北京","1"));
		test1.add(new LabelValueBean("广东","2"));
		mapValues.put("p0", test1);
		
		List<LabelValueBean> test2 = new ArrayList<LabelValueBean>();
		test2.add(new LabelValueBean("北京市","1000"));
		test2.add(new LabelValueBean("广州市","2000"));
		test2.add(new LabelValueBean("深圳市","2001"));
		mapValues.put("p1", test2);
		
		return SUCCESS;
	}

	public String getInsuredProvince() {
		return insuredProvince;
	}

	public void setInsuredProvince(String insuredProvince) {
		this.insuredProvince = insuredProvince;
	}

	public Map<String, List<LabelValueBean>> getMapValues() {
		return mapValues;
	}

	public void setMapValues(Map<String, List<LabelValueBean>> mapValues) {
		this.mapValues = mapValues;
	}

	
}
