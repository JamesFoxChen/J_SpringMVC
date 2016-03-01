package com.james.dao;

import java.util.List;

import org.testng.annotations.Test;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.spring.annotation.SpringBean;

import com.james.dao.spring311.ViewPointDao;
import com.james.domain.spring311.ViewPoint;
import com.james.test.dataset.util.XlsDataSetBeanFactory;

import static org.testng.Assert.*;

/**
 * ViewPointDao测试
 */
public class ViewPointTest extends BaseDaoTest {

	@SpringBean("viewPointDao")
	private ViewPointDao viewPointDao;
    // 验证数据库保存的正确性
    @Test
    @DataSet("ViewPointDao.EmptyViewPoints.xls")
    @ExpectedDataSet("ViewPointDao.ExpectedViewPoints.xls")// 准备验证数据
    public void saveUser()throws Exception  {
        //通过XlsDataSetBeanFactory数据集绑定工厂创建测试实体
        List<ViewPoint> viewPoints  = XlsDataSetBeanFactory.createBeans(ViewPointTest.class, "ViewPointDao.ViewPoints.xls", "t_view_point", ViewPoint.class);
        assertNotNull(viewPoints);
        for(ViewPoint viewPoint:viewPoints)
             viewPointDao.save(viewPoint);  //执行用户信息更新操作
    }
}
