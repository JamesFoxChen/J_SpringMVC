package com.james.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.james.dao.HelloDao;

@Service
public class HelloService {
	
	@Autowired
	private HelloDao dao;
	
	public void helloMvc() {
		System.out.println("进入：HelloService-->helloMvc");
		
		this.dao.helloMvc();
	}
}
