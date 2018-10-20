package com.udemy.java8.streams.terminal;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class StreamMinMaxExample {

	
	public static Optional<Student> minByExample() {
		
		
		return StudentDataBase.getAllStudents()
				.stream()
				.collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));
	}
	
	public static void main(String args[]) {
		
		System.out.println(minByExample());
	}
}
