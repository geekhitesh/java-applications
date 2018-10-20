package com.udemy.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamIntersectionExample {

	private static List<String> list1 = Arrays.asList("1","2","3","4","5","6");
	private static List<String> list2 = Arrays.asList("1","2","5");
	private static Predicate<String> p1 = (s) -> !list2.contains(s);
	
	public static List<String> intersect() {
		
		return list1.stream()
					.filter(p1)
					.collect(Collectors.toList());
	}
	
	public static void main(String args[]) {
		
		System.out.println("Intersection");
		System.out.println(intersect());
	}
}
