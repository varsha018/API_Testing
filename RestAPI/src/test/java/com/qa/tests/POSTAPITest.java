package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class POSTAPITest extends TestBase {
	
	TestBase TBase; //create object of base class..
	RestClient RClient;
	String url;
	String apiURL;
	String serviceURL;
	CloseableHttpResponse  httpresponse ;
	
	
	@BeforeMethod
	
	public void Setup() {
		
		TBase = new TestBase();
		serviceURL = prop.getProperty("serviceURL");
		apiURL = prop.getProperty("apiURL");
		
		url = serviceURL + apiURL;
		
		
	}
	
	
	@Test
	public void PostAPI() throws StreamWriteException, DatabindException, IOException {
		
		//1...create obj of RestClient to call post method that you have created in RestClient
		
		RClient= new RestClient();
		
		//2...for headers content that we want to put in...
		HashMap<String, String> HeaderMap = new HashMap<String, String>();
		
		HeaderMap.put("Content-Type", "application/json; charset=utf-8");
		
		//3...JACKSON API: to cnvert JAVA Code (POJO) into JSON >> marshelling
		
		ObjectMapper objmapper = new ObjectMapper();
		
		//4...import JAVA POJO from data package
		
		Users JAVAUsers = new Users("Varsha","leader");
		
		//5...obj(POJO) to JSON >> MARSHELLING
		
		objmapper.writeValue(new File("C:\\Users\\admin\\git\\API_Testing\\RestAPI\\API_Testing\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), JAVAUsers);
		
	}

}
