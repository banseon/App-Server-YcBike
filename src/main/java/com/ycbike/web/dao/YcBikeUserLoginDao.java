package com.ycbike.web.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.ycbike.core.dao.BaseDaoImpl;
import com.ycbike.core.model.YcBikeUserLogin;


@Repository
public class YcBikeUserLoginDao extends BaseDaoImpl<YcBikeUserLogin> implements IYcBikeUserLoginDao {

	public static void main(String[] args) {
		ApplicationContext ac = null;
		IYcBikeUserLoginDao dao = null;
		Logger logger = Logger.getLogger(YcBikeUserLoginDao.class.getName());
		
		try{
			ac = new FileSystemXmlApplicationContext("classpath:spring.xml"); 
			logger.debug("debug ApplicationContext : "+ ac);
			dao = (IYcBikeUserLoginDao)ac.getBean("YcBikeUserLoginDao");
			logger.debug("debug YcBikeUserLoginDao : "+ dao);
			logger.info("begin invoke select method .");
			List list = dao.selectAll();
			logger.info("end invoke select method .");
			logger.debug("select method result: "+list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		
	}
}
