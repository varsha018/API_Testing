package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;





public class RestClient {
	
	
	

	// create GET Method without headers

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault(); // 1..create one client connection,not object

		HttpGet httpget = new HttpGet(url); // 2..create object of HttpGet

		CloseableHttpResponse httpresponse = httpclient.execute(httpget); // 3..httpclient with hit the httpget api/url

		return httpresponse;

	}
	
	
	
	
	

	// create GET Method with headers

	public CloseableHttpResponse get(String url, HashMap<String, String> HeaderMap) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault(); // 1..create one client connection,not object

		HttpGet httpget = new HttpGet(url); // 2..create object of HttpGet
		

		//3.. To parse the headers, use Map.Entry<K,V> and EntrySet with HeaderMap

		for (Map.Entry<String, String> entry : HeaderMap.entrySet()) {

			httpget.addHeader(entry.getKey(), entry.getValue());

		}

		CloseableHttpResponse httpresponse = httpclient.execute(httpget); // 4..httpclient with hit the httpget api/url

		return httpresponse;

	}
	
	
	
	
	
	// create POST with headers -->pass URL, JSON PayLod, Headers
	
	public CloseableHttpResponse post(String url, String JSONPayLoad, HashMap<String,String> HeaderMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();//1...create default connection
		
		HttpPost httpPost = new HttpPost(url); //2...create POST request
		
		//3...we need to pass and set the entity in POST call , setEntity is used to define the payload
		
		httpPost.setEntity(new StringEntity(JSONPayLoad));
		
		//4...for passing headers
		
		for(Map.Entry<String, String> entry : HeaderMap.entrySet() ) {
			
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpresponse = httpClient.execute(httpPost); //5...Execute HTTPPOST, return httpresponse from this POST Method
		
		return httpresponse;
		
		
	}

}
