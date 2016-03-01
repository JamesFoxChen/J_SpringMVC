package com.james.dao;

import org.unitils.UnitilsTestNG;
import org.unitils.spring.annotation.SpringApplicationContext;

@SpringApplicationContext( {"classpath:/viewspace-dao.xml" })
public class BaseDaoTest extends UnitilsTestNG{
	//加载Spring上下文  
   /* @SpringApplicationContext  
    public ApplicationContext applicationContext*/;  
}
