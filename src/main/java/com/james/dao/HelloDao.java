package com.james.dao;

import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {
	public void helloMvc() {
		System.out.println("进入：HelloDao-->helloMvc");
		
		System.out.println("进入：HelloDao-->helloMvc2");
	}
}
