package com.daarks.concurrency.countdown;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		
	    ExecutorService executorService = Executors.newSingleThreadExecutor();
	    CountDownLatch latch = new CountDownLatch(6);
	    
	    for(int i=0; i<5;i++) {
	    	executorService.execute(new Worker(i,latch));
	    }

	    try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    System.out.println("Exectuors finished. Shutting down executors");
	    
	    executorService.shutdown();
	}

}
