package com.udemy.java8.app.streamsandlambdas;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private String activity;
    
    
    
	public Activity(String name, String activity) {
		super();
		this.name = name;
		this.activity = activity;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	

	@Override
	public String toString() {
		return "[activity=" + activity + "]";
	}
    
    

}
