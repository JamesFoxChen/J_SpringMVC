package com.james.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.james.domain.UserInfo;

@Repository
public class UserInfoDao {
	
	public void Test()
	{
		System.err.println("Test");
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserInfo getById(long id) {
		
		if(this.jdbcTemplate!=null)
		{
			System.err.println("this.JdbcTemplate不为null");
		}
		else
		{
			System.err.println("this.JdbcTemplate为null");
		}
		String sqlStr = " SELECT * from  userinfo where id=?";
		final UserInfo user = new UserInfo();
		jdbcTemplate.query(sqlStr, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("UserName"));
			}
		});
		
		System.err.println("Id为1对应的UserName："+user.getUserName());
		return user;
	}

	public int insertLoginLog(UserInfo userInfo) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = df.format(new Date());

		String sqlStr = "INSERT INTO userinfo " + "VALUES(?,?,?,?,?,?)";

		Object[] args = { userInfo.getUserName(), userInfo.getPassword(), userInfo.getMobile(), currentDate,
				"127.0.0.1", currentDate, };
		return jdbcTemplate.update(sqlStr, args);
	}
}