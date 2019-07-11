package com.daarks.examples.streams.services.entities;


public class Order {

	private Integer id;
	private String name;
	private String comments;
	
	public Order() {
		
	}
	
	public Order(Integer id, String name, String comments) {
		super();
		this.id = id;
		this.name = name;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
	
}
