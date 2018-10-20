package com.udemy.java8.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.udemy.java8.consumer.Student;
import com.udemy.java8.consumer.StudentDataBase;

public class SteamFlatMapExample {

	
	public static List<List<String>> activityListList = StudentDataBase.getAllStudents()
			.stream()
			.map(Student::getActivities)
			.collect(Collectors.toList());
	
	public static List<String> activityList = StudentDataBase.getAllStudents()
			.stream()
			.map(Student::getActivities)
			.flatMap(List::stream)
			.collect(Collectors.toList());
	
	public static List<String> distictSortedActivityList = StudentDataBase.getAllStudents()
			.stream()
			.map(Student::getActivities)
			.flatMap(List::stream)
			.distinct()
			.sorted()
			.collect(Collectors.toList());
	
	public static List<String> sortedListByComparator = StudentDataBase.getAllStudents()
			.stream()
			.map(Student::getActivities)
			.flatMap(List::stream)
			//.sorted(Comparator<String>.comparing(Student::getName))
			.collect(Collectors.toList());
	
	public static void main(String[] args) {
		
		System.out.println("Student Activities");
		System.out.println(activityListList);
		
		System.out.println("Student Activities Combined List");
		
		System.out.println(activityList);
		
		System.out.println("Distinct Activities");
		
		System.out.println(distictSortedActivityList);
		
		System.out.println("Sorted By Comparators");
		
		System.out.println(sortedListByComparator);
	}

}
