package com.qa.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetAPITest extends TestBase {
	
	TestBase TBase; //create object of base class..
	RestClient RClient;
	String url;
	String apiURL;
	String serviceURL;
	
	
	@BeforeMethod
	
	public void Setup() {
		
		TBase = new TestBase();
		serviceURL = prop.getProperty("serviceURL");
		apiURL = prop.getProperty("apiURL");
		
		url = serviceURL + apiURL;
		
		
	}

	@Test
	
	public void GetTest() {
		
		RClient = new RestClient();
		RClient.get(url);
		
	}
	
	@AfterMethod
	
	public void tearDown() {
		System.out.println("Quitting the browser");
	}
	

}
