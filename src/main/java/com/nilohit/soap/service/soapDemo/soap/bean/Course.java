package com.nilohit.soap.service.soapDemo.soap.bean;

public class Course {
	
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	private String name;
	private String description;
	
	
	public Course(int id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}

}
