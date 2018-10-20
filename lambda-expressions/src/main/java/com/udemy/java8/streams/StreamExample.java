package com.udemy.java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class StreamExample {

	
	public static Long count = StudentDataBase.
			getAllStudents()
			.stream()
			.count();
	
	//public static 
	
	public static void main(String args[]) {
		
		Map<String,List<String>> studentMap = StudentDataBase.getAllStudents()
				                                             .stream()
				                                             .filter((student) -> student.getGpa() > 2)
				                                             .collect(Collectors.toMap(Student::getName, 
				                                            		 				   Student::getActivities));
		System.out.println(studentMap);
		
		System.out.println("Total Count: "+count);
	}
}
