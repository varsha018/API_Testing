package com.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;



public class LoginTest {
	WebDriver driver;
	Logger log = Logger.getLogger(LoginTest.class);
	
	
	
	@BeforeMethod
	public void setup() {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\chromedriver.exe"); 
		driver= new ChromeDriver();
		
		//give warnings,info,errors and fatality with log reference of Logger class
		
		log.info("***********************************Launching chrome with amazon*********************************** ");
		log.fatal("***********************************this is a fatal message***********************************");
		log.error(driver);
		log.warn("***********************************this is a warning message***********************************");
		
		
		//all these are user defined messages generated inside console and logs folder
		
		
	}

	@Test
	
	public void loginIN() {
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().getImplicitWaitTimeout();
		
		driver.get("https://www.amazon.com");
		
		
		String title = driver.getTitle();
		System.out.println(title);
		
		if(title.equals("Amazon.com. Spend less. Smile more.")) {
			System.out.println("correct title");
		}else {
			System.out.println("incorrect title");
			
			log.warn("***********************************this is incorrect title****************************");
		}
		
		
		System.out.println(driver.getCurrentUrl());
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
}
