package com.udemy.java8.streams;

import java.util.List;
import java.util.stream.Collectors;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class StreamMapExample {

	public static List<String> nameList() {

		return StudentDataBase.getAllStudents().stream().map(Student::getName).collect(Collectors.toList());

		// return null;
	}

	public static void main(String args[]) {

		System.out.println(nameList());
	}

}
