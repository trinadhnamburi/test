package com.project.feature;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.general.common.Base;
import com.general.common.UtilityMethods;

public class Feature1 extends Base{
	
	@BeforeClass
	public void beforeClass(){
		System.out.println("Feature1 Before Class");
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println("Feature1 before Method");
	}
	
	@Test
	public void test(){
		System.out.println("Test");
		driver.get("http://www.google.com");
		driver.get(prop.getProperty("url"));
		System.out.println(driver.findElement(By.xpath("//span[@class='slds-checkbox--faux']")).getCssValue("font-family"));
		passReport("pass");
	}

}
