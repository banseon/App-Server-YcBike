package com.ycbike.core.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestLog {

	static Logger logger = LogManager.getLogger(TestLog.class.getName());

    public boolean hello() {
        logger.entry();   //trace级别的 message，单独列出来是希望你在某个方法或者程序逻辑开始的时候调用，和logger.trace("entry")基本一个意思
        logger.error("Did it again!");   //error级别的 message，参数就是你输出的 message
        logger.info("this's info message");    //info级别的 message
        logger.debug("This's debug message");
        logger.warn("This's warn message");
        logger.fatal("This's fatal message");
        logger.log(Level.DEBUG, "This's debug message");   //这个就是制定Level类型的调用：谁闲着没事调用这个，也不一定哦！
        logger.exit();    //和entry()对应的结束方法，和logger.trace("exit");一个意思
        return false;
    }
    
    public static void main(String[] args) {
    	TestLog log = new TestLog();
    	log.hello();
	}
}
