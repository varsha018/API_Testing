package com.api.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.maven.wagon.providers.http.httpclient.Header;
import org.apache.maven.wagon.providers.http.httpclient.client.ClientProtocolException;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.CloseableHttpResponse;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.HttpGet;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.HttpUriRequest;
import org.apache.maven.wagon.providers.http.httpclient.impl.client.CloseableHttpClient;
import org.apache.maven.wagon.providers.http.httpclient.impl.client.HttpClients;
import org.apache.maven.wagon.providers.http.httpclient.util.EntityUtils;
import org.json.simple.JSONObject;  //deprecated

public class RestClient{
	
	
//	1.********************GET Method (without header)*************************
	
	public CloseableHttpResponse  get(String url) throws ClientProtocolException, IOException {
		
	CloseableHttpClient HttpClient =	HttpClients.createDefault();   //HttpClients is a class, createDefault will create one client connection and will return closable http client object
		
	HttpGet httpget = new HttpGet(url); //HttpGet is a GET Class, to get the url ,you want to send
	
	CloseableHttpResponse httpResponse = HttpClient.execute(httpget);  //HttpClient will open the connection and execute to get the URL//HIT the SEND button
								 									   //it will returen closeable http response object,we convert to string and then again to json
																		//it has all the status code, response,headers,json everything

	return httpResponse;

	
	}
	
	

//	2.********************GET Method (with header)*************************
	
	public CloseableHttpResponse  get(String url, HashMap<String, String > headermap ) throws ClientProtocolException, IOException {
																		//header is in key pair format..so hashmap is used for headers
	CloseableHttpClient HttpClient =	HttpClients.createDefault();   //HttpClients is a class, createDefault will create one client connection and will return closable http client object
		
	HttpGet httpget = new HttpGet(url); //HttpGet is a GET Class, to get the url ,you want to send
	
	for(Map.Entry<String, String> entry : headermap.entrySet()) { //httpget has url and header
		
		httpget.addHeader(entry.getKey(), entry.getValue());
	}
	
	
	CloseableHttpResponse httpResponse = HttpClient.execute(httpget);  //HttpClient will open the connection and execute to get the URL//HIT the SEND button
								 									   //it will returen closeable http response object,we convert to string and then again to json
																		//it has all the status code, response,headers,json everything

	return httpResponse;

	
	}

	
//	3.********************POST Method (with header)*************************
	
	
	public CloseableHttpResponse Post(String url, String EntityString, HashMap<String, String> headermap) throws ClientProtocolException, IOException {
		CloseableHttpClient HttpClient =   HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);                        	 //post request
		httpPost.setEntity(new StringEntity(EntityString));				// for payload
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse httpResponse = HttpClient.execute((HttpUriRequest) httpPost);
		
		return httpResponse;
	}
	
	
}

	

