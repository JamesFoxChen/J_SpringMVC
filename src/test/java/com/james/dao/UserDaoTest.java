package com.james.dao;

import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.james.dao.spring311.UserDao;
import com.james.domain.spring311.User;
import com.james.test.dataset.util.XlsDataSetBeanFactory;

import static org.testng.Assert.*;

import java.util.List;

public class UserDaoTest extends BaseDaoTest {
	
	//注入用户Dao
	//从Spring容器中加载UserDao
	@SpringBean("userDao")
	private UserDao userDao;

	@Test
	@DataSet("UserDao.Users.xls")//准备数据
	public void findUserByUserName() {
		User user = userDao.getUserByUserName("test");
		
		assertNull(user,"不存在用户名为test的用户!");
		user = userDao.getUserByUserName("admin");
		assertNotNull(user,"admin用户存在！");
		assertEquals("admin", user.getUserName());
		assertEquals("123456",user.getPassword());
	}

	// 验证数据库保存的正确性
	 @Test
     @DataSet("UserDao.EmptyUser.xls")
	 @ExpectedDataSet("UserDao.ExpectedSaveUser.xls")// 准备验证数据，加载数据集
	public void saveUser()throws Exception  {
		//通过XlsDataSetBeanFactory数据集绑定工厂创建测试实体
		 //从数据集读取数据并转换成实体类
		User u  = XlsDataSetBeanFactory.createBean(UserDaoTest.class, "UserDao.SaveUser.xls", "t_user", User.class);
		userDao.save(u);  //执行用户信息更新操作,持久化数据
	}
	
	//验证数据库保存的正确性
	@Test
	@ExpectedDataSet("UserDao.ExpectedSaveUsers.xls")// 准备验证数据
	public void saveUsers()throws Exception  {
		List<User> users  = XlsDataSetBeanFactory.createBeans(UserDaoTest.class,"UserDao.SaveUsers.xls", "t_user", User.class);
		
		for(User u:users){
		     userDao.save(u);
		}
	}
}
