package com.james.dao;

import java.util.List;

import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.james.dao.spring311.ViewSpaceDao;
import com.james.domain.spring311.User;
import com.james.domain.spring311.ViewSpace;
import com.james.test.dataset.util.XlsDataSetBeanFactory;

import static org.testng.Assert.*;

/**
 * ViewSpaceDao 测试
 */
public class ViewSpaceDaoTest extends BaseDaoTest {
	
	 //④ 使用父类的Spring上下文  
    //@Test  
   /* public void testApplicationContext(){  
        assertNotNull(applicationContext);  
    }  */
	
	//从Spring容器中加载viewSpaceDao
	@SpringBean("viewSpaceDao")
	//@SpringBeanByName  //从Spring容器中加载与userService相同名称的Bean 
	//@SpringBeanByType  // 从Spring容器中加载与UserService相同类型的Bean
	private ViewSpaceDao viewSpaceDao;
	
	@Test
	@ExpectedDataSet("ViewSpaceDao.ExpectedViewSpaces.xls")// 验证数据，和数据库中的数据进行对比
	public void save()throws Exception {
		
		//通过XlsDataSetBeanFactory数据集绑定工厂创建测试实体
		 //从数据集读取数据并转换成实体类
	    List<ViewSpace> viewSpaces = XlsDataSetBeanFactory.createBeans(ViewSpaceDaoTest.class, "ViewSpaceDao.SaveViewSpaces.xls", "t_view_space", ViewSpace.class);
	    User user = new User();
        user.setUserId(1);
        for(ViewSpace viewSpace:viewSpaces){
            viewSpace.setUser(user);
	    	viewSpaceDao.save(viewSpace);
	    }
	}

    @Test
    @DataSet("ViewSpaceDao.ViewSpaces.xls")
    @ExpectedDataSet("ViewSpaceDao.ExpectedViewSpaces.xls")
    public void remove()throws Exception {
        List<ViewSpace> viewSpaces = XlsDataSetBeanFactory.createBeans(ViewSpaceDaoTest.class, "ViewSpaceDao.RemoveViewSpaces.xls", "t_view_space", ViewSpace.class);
        for(ViewSpace viewSpace:viewSpaces){
            viewSpaceDao.remove(viewSpace);
        }
        viewSpaceDao.getHibernateTemplate().clear();
    }

    @Test
    //@DataSet("ViewSpaceDao.ViewSpaces.xls")
    public void queryByName() throws Exception {
       List viewSpaces = viewSpaceDao.queryByName("鼓浪屿");
       assertNotNull(viewSpaces);
       assertEquals(viewSpaces.size(),1);
    }

}
