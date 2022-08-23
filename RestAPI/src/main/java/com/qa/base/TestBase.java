package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public int response_status_code_200 = 200;
	public int response_status_code_201 = 201;
	public int response_status_code_400 = 400;
	public int response_status_code_401 = 401;
	
	
	
	//create constructor of base class
	
	public TestBase() {
		
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\admin\\git\\API_Testing\\RestAPI\\API_Testing\\RestAPI\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
