package com.udemy.java8.function;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class FunctionExample {

	static Predicate<Student> p1 = (student) -> student.getGpa() > 2; 
	
	static BiFunction<List<Student>, Predicate, Map<String,Double>> biFunction = ((studentList,studentPredicate) -> {
	
		Map<String,Double> studentMap = new HashMap<>();
		
		studentList.forEach((student) -> {
			 if(studentPredicate.test(student))
			 {
				 studentMap.put(student.getName(), student.getGpa());
			 }
		});
		
		
		return studentMap;
	
	});
	
	public static void main(String args[]) {
		
		System.out.println(biFunction.apply(StudentDataBase.getAllStudents(),p1));
		
	}
}
