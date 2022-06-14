package com.api.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.maven.wagon.providers.http.httpclient.Header;
import org.apache.maven.wagon.providers.http.httpclient.client.ClientProtocolException;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.CloseableHttpResponse;
import org.apache.maven.wagon.providers.http.httpclient.util.EntityUtils;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.TestBase;
import com.api.client.RestClient;
import com.api.util.TestUtils;

import junit.framework.Assert;

public class GETAPITest extends TestBase{
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
	
	public void testGET() throws ClientProtocolException, IOException {
		
		restclient = new RestClient();
		
		httpResponse=  restclient.get(uri);
		
//		2.********************GET STATUS CODE*************************
		
		int StatusCode =httpResponse.getStatusLine().getStatusCode();
		
		System.out.println("The Status Code is  ===>  " + StatusCode);
		
		Assert.assertEquals(StatusCode, Response_Status_Code_200);
		

//		3.********************JSON RESPONSE*************************
		
		String responseString = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
		
		
		JSONObject responseJSON = new JSONObject(); //in brackets pass "responseString" >> to convert from string to JSON //*WOULD parse string into JSON.  //JSONObject deprecated in selenium 4
		
		
		System.out.println("the response string is  ===>  " + responseString );
		
		//get value from JSON Object
		
		String Per_Page_Value = TestUtils.getValueByJPath(responseJSON , "/per_page"); //put "/" before JSON KEY OBJECT //value of JSON OBject taken from POSTMAN
		
		System.out.println("the JSON Object Key per_page has the value" +  Per_Page_Value);
		
		Assert.assertEquals(3, Integer.parseInt(Per_Page_Value));
		
		//get value from JSON Array
		
		String id = TestUtils.getValueByJPath(responseJSON, "/data[0]/id");  //from JSON Array(data on POSTMAN) , 0 Index,FETCH value of these keys 
		String Email = TestUtils.getValueByJPath(responseJSON, "/data[0]/email");
		String FrstName = TestUtils.getValueByJPath(responseJSON, "/data[0]/first_name");
		String LastName = TestUtils.getValueByJPath(responseJSON, "/data[0]/last_name");
		
//		4.********************ALL HEADERS*************************
			
		Header[] headerArray = httpResponse.getAllHeaders();
		
		//convert headerArray TO hashmap and iterate, as it is easy key value pair;
		
		HashMap<String , String> headermap = new HashMap<String , String>();
		
		for(Header header :headerArray ) {
			
			headermap.put(header.getName(), header.getValue());
		
		}
		
		System.out.println("Headers in the GET API call is  ===> " +  headermap);
		
	}
	
	
	
	
	@AfterMethod
	
	public void TearDown() {
		System.out.println("GET Successfully Executed");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public GETAPITest() throws IOException {
		super();
		
	}

}
