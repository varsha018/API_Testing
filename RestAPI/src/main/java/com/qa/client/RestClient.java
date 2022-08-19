package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//create GET Method
	
	public void get(String url) {
		
		
		CloseableHttpClient httpclient = HttpClients.createDefault(); //1..create one client connection,not object
		
		HttpGet httpget = new HttpGet(url);    //2..create object of HttpGet
		
		
		try {
			
			CloseableHttpResponse  httpresponse = httpclient.execute(httpget);	//3..httpclient with hit the httpget api/url
			int statuscode = httpresponse.getStatusLine().getStatusCode(); //4..get status code from status line from httpResponse
			System.out.println("Status Code is ==> " + statuscode ); //5.print the status code
			
			//6..converting EntityUtils to string and storing in String
			String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8"); //get entity from httpresponse,JSON body payload,format string in UTF-8 format
			
			//7..we don't want string, we want JSON..need to convert string to JSON..using JSONObject
			
			JSONObject responsejsonobj = new JSONObject(responseString);
			
			System.out.println("Response JSON from API ==>" + responsejsonobj);
			
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
			
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //
	}

}
