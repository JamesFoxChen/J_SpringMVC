package com.james.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.james.service.HelloService;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private HelloService service;
	
	@RequestMapping("/mvc")
	public String helloMvc(HttpSession httpSession) {

		System.out.println("进入：HelloController-->helloMvc");
		this.service.helloMvc();
		
		// 视图渲染，/WEB-INF/views/home.jsp
		return "home";
	}

}