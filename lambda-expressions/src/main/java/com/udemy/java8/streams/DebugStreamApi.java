package com.udemy.java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class DebugStreamApi {

	public static void main(String args[]) {

		Map<String, List<String>> studentMap = StudentDataBase.getAllStudents().stream()
				.peek((student) ->{
					System.out.println("**********All Students Debug Started********");
					System.out.println(student);
					System.out.println("**********All Students Debug Ended********");
				})
				.filter((student) -> student.getGpa() > 3.5)
				.peek((student) ->{
					System.out.println("**********First Filter Debug Started********");
					System.out.println(student);
					System.out.println("**********First Filter Debug Ended********");
				})
				.collect(Collectors.toMap(Student::getName, Student::getActivities));

		System.out.println(studentMap);
	}
}
