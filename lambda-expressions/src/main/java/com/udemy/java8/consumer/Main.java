package com.udemy.java8.consumer;

import java.util.List;
import java.util.function.Consumer;

public class Main {
	
	static Consumer<Student> c2 = (student) -> System.out.println(student); 
	
	static Consumer<Student> c3 = (student) -> System.out.print(student.getName());
	static Consumer<Student> c4 = (student) -> System.out.println(student.getActivities());
	
	public static void printName() {
		
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach(c2);
	}
	
	public static void printNameAndActivities( ) {
	
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach(c3.andThen(c4));
	}
	
	public static void printNameUsingconditions() {
		
		List<Student> studentList = StudentDataBase.getAllStudents();
		
		studentList.forEach((student) -> {
			
			if(student.getGpa() > 3) {
			//	System.out.println(student);
				c3.andThen(c4);
			}
			
		});
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Consumer<String> consumer  = (s) -> System.out.println(s.toUpperCase());
		
		//consumer.accept("Hello");
		
		//printName();
		
		//printNameAndActivities();
		
		printNameUsingconditions();
		
	}

}
