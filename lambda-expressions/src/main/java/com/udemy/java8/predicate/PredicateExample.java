package com.udemy.java8.predicate;

import java.util.function.Predicate;

public class PredicateExample {

	static Predicate<Integer> p1 = (i) -> i % 2 == 0;
	static Predicate<Integer> p2 = (i) -> i % 5 == 0;

	public static void predicateChainingAnd(int num) {

		System.out.println("Predicate And Result is: " + p1.and(p2).test(num));
	}

	public static void predicateChainingOR(int num) {

		System.out.println("Predicate OR Result is: " + p1.or(p2).test(num));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		predicateChainingAnd(8);
		predicateChainingOR(8);

	}

}
