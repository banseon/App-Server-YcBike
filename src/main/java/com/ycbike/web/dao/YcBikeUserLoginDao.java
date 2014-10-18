package com.ycbike.web.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ycbike.core.dao.BaseDaoImpl;
import com.ycbike.web.domain.YcBikeUserLogin;


//@Component("YcBikeUserLoginDao")
public class YcBikeUserLoginDao extends BaseDaoImpl<YcBikeUserLogin> implements IYcBikeUserLoginDao {

	public static void main(String[] args) {
		ApplicationContext ac = null;
		Logger logger = Logger.getLogger(YcBikeCircleDao.class.getName());
		
		try{
			ac = new FileSystemXmlApplicationContext("classpath:spring.xml"); 
			logger.debug("debug ApplicationContext : "+ ac);
			IYcBikeCircleDao dao = (IYcBikeCircleDao)ac.getBean("YcBikeCircleDao");
			logger.debug("debug YcBikeCircleDao : "+ dao);
			logger.info("begin invoke select method .");
			List list = dao.selectAll();
			logger.info("end invoke select method .");
			logger.debug("select method result: "+list.size());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}
}
