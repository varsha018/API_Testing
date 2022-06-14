package com.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	
	public Properties prop;
	public int Response_Status_Code_200 = 200;
	
	
	public TestBase() throws IOException{
		
		prop = new Properties();
		FileInputStream ip = new FileInputStream("C:\\Users\\admin\\eclipse\\APITesting\\src\\main\\java\\com\\api\\config\\config.properties");
		prop.load(ip);
		
			
	}
	

}
