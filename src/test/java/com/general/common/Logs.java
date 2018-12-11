package com.general.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;


public class Logs {
	
	static Properties props = new Properties();
	static Logger log;
	
	public static String getConsoleFormatedText(String text){
		return text.replace("<br>", "\n\t\t").replace("<b>", "").replace("</b>", "").replace("<pre>", "")
				.replace("</pre>", "").replace("</br>", "\n\t");
	}
	
	private static void init(){
		if(log == null){
			try{
				InputStream in = Logs.class.getClassLoader().getResourceAsStream("log4j.properties");
				PropertyConfigurator.configure(props);
				
				FileAppender fa = new FileAppender();
				
				fa.setFile("FileLogger");
				fa.setFile(ExtentReportManager.getReportLocation()+"/Logs/main.log");
				fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
				fa.setThreshold(Level.INFO);
				fa.setAppend(true);
				fa.activateOptions();
				
				Logger.getLogger("rootLogger");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	static public synchronized void info(String details){
		init();
		log.info(getConsoleFormatedText(details));
	}

	static public synchronized void error(String details){
		init();
		log.info(getConsoleFormatedText(details));
	}
	
	static public synchronized void warn(String details){
		init();
		log.info(getConsoleFormatedText(details));
	}

	static public synchronized void fatal(String details){
		init();
		log.info(getConsoleFormatedText(details));
	}


}
