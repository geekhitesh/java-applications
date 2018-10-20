package com.daarks.concurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import studentlibrary.Book;
import studentlibrary.Constants;

public class Service1 implements Runnable {
	
	private String name;
	private CyclicBarrier barrier;
	private BlockingQueue<Integer> queue;
	
	
	public Service1(String name, CyclicBarrier barrier,BlockingQueue<Integer> queue)
	{
		this.name = name;
		this.barrier = barrier;
		this.queue = queue;
	}


	public void run() {
	
		this.doWork();
		
		try {
			System.out.println("Work finished for service "+Thread.currentThread().getName());
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	private void doWork() {
	
		System.out.println("Service "+this.name+ " in execution");
		this.getRandomNumbers();
		
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void getRandomNumbers() {

		Random random = new Random();

		int number = random.nextInt(20);
		// System.out.println(this.book);
		try {
			queue.put(number);
			queue.put(random.nextInt(20));
			queue.put(random.nextInt(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
