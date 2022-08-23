package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utils.TestUtil;

public class GetAPIwithHeaders extends TestBase {
	
	
	
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

	@Test(priority=1)
	
	public void GetTestWithHeaders() throws ClientProtocolException, IOException {
		
		RClient = new RestClient();
		
		//create HashMap for Headers, only these lines headermap changes
		
		HashMap<String, String> HeaderMap =  new HashMap<String, String>();
		
		HeaderMap.put("Content-Type", "application/json");
	//	HeaderMap.put("Host", "<calculated when request is sent>");
	//	HeaderMap.put("User-Agent", "PostmanRuntime/7.29.2");
		
		System.out.println("Headers got inserted ");
		
		httpresponse = RClient.get(url, HeaderMap);
		
		int statuscode = httpresponse.getStatusLine().getStatusCode(); //4..get status code from status line from httpResponse
		System.out.println("Status Code is ==> " + statuscode ); //5.print the status code
		
		Assert.assertEquals(statuscode, response_status_code_200,"Status code is not 200");
		
		//get entity from httpresponse,JSON body payload,format string in UTF-8 format
		
		String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8"); //6..converting EntityUtils to string and storing in String

		JSONObject responsejsonobj = new JSONObject(responseString); //7..we don't want string, we want JSON..need to convert string to JSON..using JSONObject
		
		System.out.println("Response JSON from API ==>" + responsejsonobj);
		
		//create getValueByJpath utility to parse JSON Response
		
		String PerPageValue =	TestUtil.getValueByJpath(responsejsonobj, "/per_page");
		System.out.println("Per page value from JSON Response is ==> " + PerPageValue);
	
	
		Assert.assertEquals(Integer.parseInt(PerPageValue), 6);
	
		String total =	TestUtil.getValueByJpath(responsejsonobj, "/total");
		System.out.println("total value from JSON Response is ==> " + total);
	
	
		Assert.assertEquals(Integer.parseInt(total), 12);
		
		
		//Get value from JSON ARRAY
		
		String Email_0 = TestUtil.getValueByJpath(responsejsonobj, "/data[0]/email");
		System.out.println("Email at 0 index ==> " + Email_0);
		
		String first_name_1 = TestUtil.getValueByJpath(responsejsonobj, "/data[1]/first_name");
		System.out.println("first_name_1 at 1 index ==> " + first_name_1);
		
		String last_name_1 = TestUtil.getValueByJpath(responsejsonobj, "/data[1]/last_name");
		System.out.println("last_name_1 at 1 index ==> " + last_name_1);
		
		String avatar_5 = TestUtil.getValueByJpath(responsejsonobj, "/data[5]/avatar");
		System.out.println("avatar_5 at 5 index ==> " + avatar_5);
		
		
		
		
		//8..To get all headers from httpresponse,
		
		Header[] headerarray = httpresponse.getAllHeaders();
		
		//header array is an arraylist, but it has key-value pair concept, so we will store these headers in HashMap
		//9..create a hashmap to store key-value pair format
		
		HashMap<String, String> allHeader = new HashMap<String, String>();
		
		//10..Iterate the Header array and store value in HashMap
		
		for(Header h1 : headerarray ) {
			
			allHeader.put(h1.getName(), h1.getValue());
			
		}
		
		//11..print the hashmap "allHeader"
		
		System.out.println("Headers array ===> " + allHeader);
		
	}
	
		

	




}
