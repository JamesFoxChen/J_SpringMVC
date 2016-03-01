package com.james.service.spring311;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.james.dao.spring311.LoginLogDao;
import com.james.dao.spring311.UserDao;
import com.james.domain.spring311.LoginLog;
import com.james.domain.spring311.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public User findUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	public User getUserById(int userId) {
		return userDao.get(userId);
	}

	public void saveLoginInfo(User user) {
		LoginLog loginLog = new LoginLog();
		loginLog.setUser(user);
		loginLog.setIp(user.getLastLoginIp());
		loginLog.setLoginDate(user.getLastLoginTime());
		userDao.update(user);
		loginLogDao.save(loginLog);
	}
}
