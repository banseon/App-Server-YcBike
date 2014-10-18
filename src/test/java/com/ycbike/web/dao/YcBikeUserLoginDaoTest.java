package com.ycbike.web.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ycbike.core.dao.BaseDao;
import com.ycbike.web.dao.YcBikeUserLoginDao;
import com.ycbike.web.domain.YcBikeUserLogin;

public class YcBikeUserLoginDaoTest {

	
	ApplicationContext ac = null;
	
	
	@Before
	public void setUp() throws Exception {
		ac = new FileSystemXmlApplicationContext("classpath:spring.xml"); 
		 
	}
	
	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSelectAll() {
		
		
	}

}
