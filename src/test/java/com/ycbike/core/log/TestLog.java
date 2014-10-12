package com.ycbike.core.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {

	static Logger logger = LogManager.getLogger(TestLog.class.getName());
 
    public boolean hello() {
        logger.entry();   //trace����� message�������г�����ϣ������ĳ���������߳����߼���ʼ��ʱ����ã���logger.trace("entry")����һ����˼
        logger.error("Did it again!");   //error����� message����������������� message
        logger.info("this's info message");    //info����� message
        logger.debug("This's debug message");
        logger.warn("This's warn message");
        logger.fatal("This's fatal message");
        logger.log(Level.DEBUG, "This's debug message");   //��������ƶ�Level���͵ĵ��ã�˭����û�µ��������Ҳ��һ��Ŷ��
        logger.exit();    //��entry()��Ӧ�Ľ�����������logger.trace("exit");һ����˼
        return false;
    }
    
    public static void main(String[] args) {
    	TestLog log = new TestLog();
    	log.hello();
	}
}
