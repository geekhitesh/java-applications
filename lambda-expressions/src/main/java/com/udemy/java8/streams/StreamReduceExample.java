package com.udemy.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamReduceExample {

	
	
	public static void main(String args[]) {
	
		List<Integer> integerList = Arrays.asList(2,3,4,5,6,7,8,20,23,45,12);
		
		Integer max = integerList.stream()
					.reduce(0, (x,y) -> x>y ? x : y );
		
		System.out.println(max);
		
		Integer min = integerList.stream()
				.reduce(999999, (x,y) -> x<y ? x:y);
		
		System.out.println(min);
		
	}
	
	
}
