package com.udemy.java8.streams;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class StreamOfGenerate {

	static Consumer<String> c1 = (print) -> System.out.println(print);
	 
	
	public static void main(String args[]) {
		
		Stream<Integer> integerStream = Stream.of(1,2,3,4,5,6,7,8,20,23,45,12);
		
		//System.out.println(stringStream);
		
		//stringStream.forEach(System.out::println);
		
		
		
		//stringStream.forEach(c1);
		
		//integerStream.reduce(accumulator)
		
	}
}
