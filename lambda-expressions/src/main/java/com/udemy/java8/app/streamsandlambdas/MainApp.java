package com.udemy.java8.app.streamsandlambdas;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MainApp {
	

	/*
	 *1. list of students with highest grade 
	 *2. activity list as map of studentname -> list of activities 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	public static Predicate<Student> checkGrade() {
		
		Predicate<Student> studentPredicate = (student) -> {
			
			if(student.getGradeLevel() > 2) {
				return true;
			}else {
				return false;
			}
		} ;
		
		return studentPredicate;
	}
	
	public static List<Student> getStudentsWithHighestGrade() {
		
		
		List<Student> students = StudentDataBase.getAllStudents()
				.stream()
				.filter(checkGrade()).collect(Collectors.toList());
		
		
		return students;
		
	}
	
	public static Map<String,List<Activity>> nameActivityListMap() {
		
		Map<String, List<Activity>> activityMap = StudentActivityDatabase.getAllStudentActivities()
																	  .stream()
																	  .collect(Collectors.groupingBy(Activity::getName));
		return activityMap;
	}
	

	public static Map<String,Integer> nameActivityCountMap() {
		
		Map<String,Long> nameActivityCountMap = StudentActivityDatabase.getAllStudentActivities()
				    .stream()
				    .map((activity) -> activity.getName())
				    .collect(Collectors.groupingBy((name) -> name,Collectors.counting()));
		Iterator iterator = nameActivityCountMap.keySet().iterator();
		
		while(iterator.hasNext()) {
			String name = (String) iterator.next();
			System.out.println(name+" count:"+nameActivityCountMap.get(name));
		}
		return null;
	}
	
	public static void main(String[] args) {
	
		//System.out.println(MainApp.getStudentsWithHighestGrade());
		//System.out.println(nameActivityListMap());
		
		System.out.println(nameActivityCountMap());
	}
}
