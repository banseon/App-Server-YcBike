package com.ycbike.web.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ycbike.web.domain.YcBikeCircle;

public class IYcBikeCircleDaoTest {
	
	ApplicationContext ac = null;
	Logger logger = Logger.getLogger(IYcBikeCircleDaoTest.class.getName());
	
	@Before
	public void setUp() throws Exception {
		ac = new FileSystemXmlApplicationContext("classpath:spring.xml"); 
		logger.debug("Create ApplicationContext : "+ac);
	}

	@Test
	public void testSelectAll() {
		logger.debug("getBean method invoke........");
		IYcBikeCircleDao dao = (IYcBikeCircleDao)ac.getBean("YcBikeCircleDao");
		logger.debug("IYcBikeCircleDao : "+dao);
		logger.debug("selectAll method invoke ............");
		List<YcBikeCircle> list = dao.selectAll();
		logger.debug("list :"+list);
		assertNotNull(list);
	}

}
