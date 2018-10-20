package com.udemy.java8.predicate;

import java.util.List;
import java.util.function.Predicate;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class PredicateStudentExample {
	
	
	static Predicate<Student> p1 = (s) -> s.getGradeLevel() >=3 && s.getGender().equals("male");
	static Predicate<Student> p2 = (s) -> s.getGpa() > 3.4;
	
	public static void filterByGrade() {
		
		System.out.println("Filter Students by Grade:");
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach((student) -> {
			
			if(p1.test(student))
				System.out.println(student);
			
		});
	
	}
	
	public static void filterByGpa() {
		System.out.println("Filter Students by GPA:");
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach((student) -> {
			
			if(p2.test(student))
				System.out.println(student);
			
		});
		
	}
	
	public static void filterByGradeAndGpa() {
		
		System.out.println("Filter Students By Grade and GPA");
		
		List<Student> studentList = StudentDataBase.getAllStudents();
		studentList.forEach((student) -> {
			
			if(p1.and(p2).test(student))
				System.out.println(student);
			
		}); 
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		filterByGrade();
		filterByGpa();
		filterByGradeAndGpa();
		
		
	}

}
