package com.james.service;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

@SpringApplicationContext({ "classpath:/viewspace-service.xml","classpath:/viewspace-dao.xml" })
public class BaseServiceTest extends UnitilsTestNG {
	
	@SpringBean("hibernateTemplate")
	public HibernateTemplate hibernateTemplate;

}
