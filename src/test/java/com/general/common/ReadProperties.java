package com.general.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
	
	public static Properties pro;
	public static String projectLocation = "src/test/resources";
	public static String browser = null;
	public static FileInputStream fis;
	
	public String getProperty(String key){
		try{
			return pro.getProperty(key);
		}catch(Exception e){
			return "";
		}
	}
	
	public ReadProperties() throws Throwable {
		if(pro!=null){
			return;
		}
		pro = new Properties();
		
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		pro.load(in);
		
		File userConfig = new File(projectLocation+"/config.properties");
		
		if(userConfig.exists()){
			fis = new FileInputStream(userConfig);
		}
		
		browser = pro.getProperty("browser");
	}
	
	public void loadConfig(String filePath){
		File loadProfile = new File(filePath);
		
		if(loadProfile.exists()){
		}
	}
}
