package com.james.dao;

import org.springframework.stereotype.Repository;

import com.james.domain.LoginLog;

@Repository
public class LoginLogDao extends BaseDao{

	public void insertLoginLog(LoginLog loginLog) {
        String sqlStr = "INSERT INTO t_login_log(user_id,ip,login_datetime) "
                + "VALUES(?,?,?)";
        Object[] args = {loginLog.getUserId(), loginLog.getIp(),
                loginLog.getLoginDate()};
        jdbcTemplate.update(sqlStr, args);
    }
}
