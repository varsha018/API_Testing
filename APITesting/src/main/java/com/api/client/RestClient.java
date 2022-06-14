package com.api.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.maven.wagon.providers.http.httpclient.Header;
import org.apache.maven.wagon.providers.http.httpclient.client.ClientProtocolException;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.CloseableHttpResponse;
import org.apache.maven.wagon.providers.http.httpclient.client.methods.HttpGet;
import org.apache.maven.wagon.providers.http.httpclient.impl.client.CloseableHttpClient;
import org.apache.maven.wagon.providers.http.httpclient.impl.client.HttpClients;
import org.apache.maven.wagon.providers.http.httpclient.util.EntityUtils;
import org.json.simple.JSONObject;  //deprecated

public class RestClient{
	
	
//	1.********************GET Method*************************
	
	public CloseableHttpResponse  get(String url) throws ClientProtocolException, IOException {
		
	CloseableHttpClient HttpClient =	HttpClients.createDefault();   //HttpClients is a class, createDefault will create one client connection and will return closable http client object
		
	HttpGet httpget = new HttpGet(url); //HttpGet is a GET Class, to get the url ,you want to send
	
	CloseableHttpResponse httpResponse = HttpClient.execute(httpget);  //HttpClient will open the connection and execute to get the URL//HIT the SEND button
								 									   //it will returen closeable http response object,we convert to string and then again to json
																		//it has all the status code, response,headers,json everything

	return httpResponse;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

	
	
}

	

