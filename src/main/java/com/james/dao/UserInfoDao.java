package com.james.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserInfoDao {
	
	
	public void Test()
	{
		System.err.println("Test");
	}
	
	/*@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserInfo getById(long id) {
		String sqlStr = " SELECT * from  userinfo where id=?";
		final UserInfo user = new UserInfo();
		jdbcTemplate.query(sqlStr, new Object[] { id }, new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getLong("id"));
				user.setUserName(rs.getString("UserName"));
			}
		});
		return user;
	}

	public int insertLoginLog(UserInfo userInfo) {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = df.format(new Date());

		String sqlStr = "INSERT INTO userinfo " + "VALUES(?,?,?,?,?,?)";

		Object[] args = { userInfo.getUserName(), userInfo.getPassword(), userInfo.getMobile(), currentDate,
				"127.0.0.1", currentDate, };
		return jdbcTemplate.update(sqlStr, args);
	}*/
}