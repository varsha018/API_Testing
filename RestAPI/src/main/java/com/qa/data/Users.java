package com.qa.data;


//POJO :Plain Old JAVA Object

public class Users {
	
	//define variables that you pass in JSON PAYLOAD in POST Call in POSTMAN
	
	String name;
	String job;
	String id;
	String createdAt;
	
	
	//create constructor
	public Users() {
		
	}
	
	public Users(String name, String job) {
		this.name = name;
		this.job = job;
		
		
	}
	
	
	//create getters and setters for variables defined at class level
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	

}
