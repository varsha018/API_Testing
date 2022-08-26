package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
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
		
		//5...obj(POJO) to JSON file >> writeValue method in mapper class
		
		objmapper.writeValue(new File("C:\\Users\\admin\\git\\API_Testing\\RestAPI\\API_Testing\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), JAVAUsers);
		
		//6...obj(POJO) to JSON String >>writeValueAsString method in mapper class
		
		String JSONStringUsers = objmapper.writeValueAsString(JAVAUsers);
		
		System.out.println("JSON String after converting JAVA Userdata into JSON using writeValueAsString==>" +  JSONStringUsers);
		
		
		//7... call the POST method from RestClient and pass all the params to execute post mthod
		
		httpresponse= RClient.post(url, JSONStringUsers, HeaderMap);
		
		//8.From this response, get Status Code
		
		int StatusCode = httpresponse.getStatusLine().getStatusCode();
		
		System.out.println("status code is ===> " +  StatusCode);
		
		Assert.assertEquals(StatusCode, response_status_code_201);
		
		//9...JSON Utility >>convertion the response into entity >>unmarshelling
		
		String ResponseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		
		JSONObject JobjResponse = new JSONObject(ResponseString);
		
		System.out.println("Reponse from API is==> " + JobjResponse );
		
		
		//10..read the json string from mapper JSON TO java >> unmarshlling
		
		Users USERoBJ = objmapper.readValue(ResponseString, Users.class);
		System.out.println("READ VALUE USING MAPPER CLASS ===> " + USERoBJ);
		
		
		
		
	}

}
