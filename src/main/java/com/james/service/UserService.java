package com.james.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.james.dao.LoginLogDao;
import com.james.dao.UserDao;
import com.james.domain.*;

@Service
public class UserService extends BaseService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userName, String password) {
		int matchCount = userDao.getMatchCount(userName, password);
		return matchCount > 0;
	}

	public UserOld findUserByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	public void loginSuccess(UserOld user) {
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserId());
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(user.getLastVisit());
		loginLogDao.insertLoginLog(loginLog);
	}
}
