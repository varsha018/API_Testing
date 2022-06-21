package com.api.data;

//POJO: Plain Old JAVA Object

public class Users {

	String name;
	String job;
	String id;
	String Created_At;
	
	//to create getters and setters >> right click on page>>source>> generate getters and setters >> choose variable>> Generate.

	public Users() {

	}

	public Users(String name, String job) {
		this.name = name;
		this.job = job;

	}
	//getters and setters method
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreated_At() {
		return Created_At;
	}

	public void setCreated_At(String created_At) {
		Created_At = created_At;
	}

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

	
	
	

}
