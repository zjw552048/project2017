package com.z.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.z.entity.common.TestBean;

@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(){
		return "test/test";
	}
	
	@RequestMapping("/sendTest")
	public String sendTest(){
		return "test/sendTest";
	}
	
	@RequestMapping("/other")
	public String other(){
		return "test/other";
	}
	
	@RequestMapping("/testBean")
	@ResponseBody
	public TestBean testBean(){
		TestBean bean = new TestBean();
		bean.setId(1);
		bean.setDate(new Date());
		return bean;
	}
}
