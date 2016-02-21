package com.james.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HelloDao {

	//所有都使用注解JdbcTemplate才不会报null异常
	//如果使用new UserInfoDao().getById(1); 就会抛出JdbcTemplate的null异常
	
	@Autowired
	private UserInfoDao userInfoDao;

	public void helloMvc() {
		System.out.println("进入：HelloDao-->helloMvc");

		System.out.println("进入：HelloDao-->helloMvc2");

		if (this.userInfoDao == null) {
			System.err.println("this.userInfoDao为null");
		} else {
			System.err.println("this.userInfoDao不为null");
			//this.userInfoDao.getById(1);
		}
	}
}
