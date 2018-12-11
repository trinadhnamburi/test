package com.general.common;

import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.LogStatus;

public class Base{
	
	private static ThreadLocal<HashMap<String, Object>> driverChrome;
	
	public static WebDriver driver;
	public static String projectLocation = "src/test/resources/";
	
	
	public static ReadProperties prop;
	public ITestContext ctx;
	public String reportFileName;
	public String browser;
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext ctx) throws Throwable{
		System.out.println("From before suite");
		this.ctx = ctx;
		prop = new ReadProperties();
		reportFileName = (new Date()).toString().replace(":", "_").replace(" ", "_");
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver",projectLocation+"drivers/chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		
	}
	
	public String getParamValue(ITestContext ctx, String param){
		String value = System.getProperty(param);
		
		if(value == null){
			try{
				value = (ctx.getCurrentXmlTest().getParameter(param)).toString();
			}catch(Exception e){
				value =prop.getProperty(param);
			}
		}
		return value;
	}
	
	@BeforeTest
	public void beforeTest(){
		System.out.println("From Base before test");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod1(){
		System.out.println("From Base Before Method");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(){
		
	}
	
	@AfterTest(alwaysRun = true)
	public void afterTest(){
		System.out.println("From after test");
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite(){
		System.out.println("After suite ");
		driver.quit();
	}
	
	public void skipReport(String info){
		try{
			ExtentReportManager.getTest().log(LogStatus.SKIP, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void passReport(String info){
		try{
			ExtentReportManager.getTest().log(LogStatus.PASS, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void failReport(String info){
		try{
			ExtentReportManager.getTest().log(LogStatus.FAIL, info);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void setProjectExecutionVariables(ITestContext ctx){
		browser = getParamValue(ctx, "browser");
	}

}
