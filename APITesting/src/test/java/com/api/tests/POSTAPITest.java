package com.api.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.CloseableHttpResponse;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.client.RestClient;
import com.api.data.Users;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class POSTAPITest extends TestBase{
	
	
	public POSTAPITest() throws IOException {
		super();
		
	}


	TestBase baseclass;
	RestClient restclient;
	public String uri;
	CloseableHttpResponse httpResponse;
	
	
	@BeforeMethod
	
	public void Setup() throws IOException {
		baseclass= new TestBase();
		String s1 =prop.getProperty("url");
		String s2 =prop.getProperty("serviceURL");
		uri = s1 + s2;
		
	}
	
	@Test
	public void PostAPITest() throws StreamWriteException, DatabindException, IOException {
		restclient =new RestClient();
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("OAM_REMOTE_USER", "vgupt05");
		headermap.put("DIVISION", "25");
		
		
		//we need to give JSON Payload in POST Method.. created USERS CLASS(java class object) and we will convert this into JSON using JACKSON API.
		//JAVA CLASS OBJ >>>convert into >> JSON PAYLOAD --->> MARSHELLING
		
		//a veryfamous class>>ObjectMapper
		
		ObjectMapper mapper = new ObjectMapper();  //available in JACKSON
		Users users = new Users("varsha","leader");  ///>>>JSON PAYLOAD THAT WE PASSED
		
		
		//Object to JSON file.. it will converts Users java class into JSON file and write the JSON PAYLOAD IN the JSON TEXT FILE. 
		mapper.writeValue(new File("C:\\Users\\admin\\git\\API_Testing\\APITesting\\src\\main\\java\\com\\api\\data\\Users.json"), users);  
		
		//Object to JSON file String 
		String usersJSONString = mapper.writeValueAsString(users);
		System.out.println(usersJSONString);
		
		
		httpResponse=  restclient.Post(uri, usersJSONString, headermap);
		
		int statuscode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, Response_Status_Code_201);
		
		
		//TO GET JSONString
		String JSONString = EntityUtils.toString((HttpEntity) httpResponse.getEntity(),"UTF-8");
		JSONObject responseJSON = new JSONObject(JSONString);
		System.out.println("The response from API is ===>>  " + responseJSON );
		
		//JSON to JAVA >>>UN-MARSHELLING
		Users userObj = mapper.readValue(JSONString, Users.class);
		System.out.println(userObj);   //>>>>>RESPONSE compare with >JSON PAYLOAD THAT WE PASSED
		
		//comparison
		System.out.println(users.getName().equals(userObj.getName()));
		System.out.println(users.getJob().equals(userObj.getJob()));
		
		//if you get error>>some extra params in JSON RESPONSE>> pass them AS variables and as getters and setters in JAVA POJO
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
