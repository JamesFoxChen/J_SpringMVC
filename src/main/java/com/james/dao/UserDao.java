package com.james.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.james.dao.BaseDao;
import com.james.domain.UserOld;

@Repository
public class UserDao extends BaseDao {

	 public int getMatchCount(String userName, String password) {
	        String sqlStr = " SELECT count(*) FROM t_user "
	                + " WHERE user_name =? and password=? ";
	        return jdbcTemplate.queryForInt(sqlStr, new Object[]{userName, password});
	    }

	    public UserOld findUserByUserName(final String userName) {
	        String sqlStr = " SELECT user_id,user_name "
	                + " FROM t_user WHERE user_name =? ";
	        final UserOld user = new UserOld();
	        jdbcTemplate.query(sqlStr, new Object[]{userName},
	                new RowCallbackHandler() {
	                    public void processRow(ResultSet rs) throws SQLException {
	                        user.setUserId(rs.getInt("user_id"));
	                        user.setUserName(userName);
	                    }
	                });
	        return user;
	    }

	    public void updateLoginInfo(UserOld user) {
	        String sqlStr = " UPDATE t_user SET last_visit=?,last_ip=?"
	                + " WHERE user_id =?";
	        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(),
	                user.getLastIp(), user.getUserId()});
	    }
}
