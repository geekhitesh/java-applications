package com.udemy.java8.app;

import java.util.Comparator;

public class LambdaApp {

	public static void main(String args[]) {
	
		Comparator<String> stringComparator = new Comparator<String>() {

			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return o1.compareToIgnoreCase(o2);
			}
			
			
		};
		
		int result = stringComparator.compare("Hello", "Hell0");
		System.out.println(result);
	
		
	Shape s =	new Shape() {

			@Override
			public void draw(String name) {
				System.out.println("Drawing: "+name);
				
			}

			@Override
			public void printMessage(String message) {
				// TODO Auto-generated method stub
				
			}
			
		};
	
		s.draw("Square");
		
		Comparator<String> stringComparatorLambda = (s1,s2) -> s1.compareToIgnoreCase(s2);
		
		int comparision = stringComparatorLambda.compare("Hello", "Hell0");
		
		System.out.println(comparision);
		
		
		//Shape s1 = (name) -> System.out.println("Drawing "+name);
		
		//s1.draw("Rectangle");
		
		
	}
	

	
	
}
