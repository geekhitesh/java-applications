package com.udemy.java8.app;

public interface Shape {

	public void draw( String name);
	
	public static void printMessage(String message) {
		
		System.out.println("Hello: "+ message);
	}
}
