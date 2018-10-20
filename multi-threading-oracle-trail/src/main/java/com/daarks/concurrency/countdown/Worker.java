package com.daarks.concurrency.countdown;

import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

	private CountDownLatch countDownLatch;
	private int id;
	
	public Worker(int id, CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
		this.id = id;
	}
	
	public void run() {
		System.out.println("Running for thread: "+id);
		doWork();
		countDownLatch.countDown();
	}
	
	public void doWork() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}

}
