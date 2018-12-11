package com.general.common;

import org.testng.ITestContext;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.LogStatus;

public class UtilityMethods{

	private final static String JS_GET_VIEWPORT_WIDTH = "var width = undefined; if (window.innerWidth) {width = window.innerWidth;} else if (document.documentElement && document.documentElement.clientWidth) {width = document.documentElement.clientWidth;} else { var b = document.getElementsByTagName('body')[0]; if (b.clientWidth) {width = b.clientWidth;}};return width;";
	private final static String JS_GET_VIEWPORT_HEIGHT = "var height = undefined;  if (window.innerHeight) {height = window.innerHeight;}  else if (document.documentElement && document.documentElement.clientHeight) {height = document.documentElement.clientHeight;}  else { var b = document.getElementsByTagName('body')[0]; if (b.clientHeight) {height = b.clientHeight;}};return height;";
	
	public static ReadProperties prop;

	public static String getParamValue(ITestContext ctx, String param) {
		String value = System.getProperty(param);

		if (value!=null) {
			return value;
		}
		try {
			return (ctx.getCurrentXmlTest().getParameter(param)).toString();

		} catch (Exception e) {
			try {
				//new ReadProperties();
				return new ReadProperties().getProperty(param);
			} catch (Throwable e1) {
			}
		}
		return "";
	}

	public  static String getBrowserName(ITestResult testResult) {
		String browserName = UtilityMethods.getParamValue(testResult.getTestContext(), "browser");
		String breakPoint = UtilityMethods.getParamValue(testResult.getTestContext(), "breakPoint");

		return browserName + "> BP " + breakPoint;
	}
	


}
