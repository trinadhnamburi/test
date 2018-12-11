package com.general.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportManager {
	
	static Map extentTestMap = new HashMap();
	static ExtentReports extent;
	public static String reportLocation = null;
	final public static String reportPath = getReportLocation() + "/SummaryReport.html";
	public static String report = "";
	public static ExtentTest logger;
	
	public static String getReportLocation(){
		
		if(reportLocation == null ){
			reportLocation = ReadProperties.projectLocation+"/Reports/Project_Automation_Report_"
					+(new Date()).toString().replace(":", "_").replace(" ", "_");
		}
		
		return reportLocation;
	}
	
	public synchronized static ExtentReports getReport(){
		if(extent == null){
			extent = new ExtentReports(reportPath, true);
			getReportLocation();
		}
		
		return extent;
	}
	
	public static synchronized ExtentTest getTest(){
		return (ExtentTest) extentTestMap.get((int)(long)(Thread.currentThread().getId()));
	}
	
	public static synchronized void endTest(){
		extent.endTest( (ExtentTest) extentTestMap.get((int)(long)(Thread.currentThread().getId())));
	}
	
	public static synchronized ExtentTest startTest(String testName){
		return startTest(testName, "");
	}

	private static ExtentTest startTest(String testName, String desc) {
		
		ExtentTest test = extent.startTest(testName, desc);
		
		logger = test;
		extentTestMap.put((int)(long)(Thread.currentThread().getId()), test);
		
		return test;
	}


}
