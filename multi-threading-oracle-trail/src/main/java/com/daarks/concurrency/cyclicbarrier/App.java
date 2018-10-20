package com.daarks.concurrency.cyclicbarrier;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class App {

	private static CyclicBarrier barrier;
	private static BlockingQueue<Integer> queue;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		queue = new ArrayBlockingQueue<Integer>(300);
		barrier = new CyclicBarrier(4, new Runnable() {
			
			public void run() {
				System.out.println("Now Running Final Validation by thread "+Thread.currentThread().getName());
				System.out.println(queue.size());
				System.out.println(queue);
				
			}
		});
		
		Thread t1 = new Thread( new Service1("1", barrier, queue),"Service 1");
		Thread t2 = new Thread( new Service2("2", barrier, queue),"Service 2");
		Thread t3 = new Thread( new Service1("3", barrier, queue),"Service 3");
		Thread t4 = new Thread( new Service2("4", barrier, queue),"Service 4");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		System.out.println("Tasks submitted by main thread");
	}

}
