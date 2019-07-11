package com.udemy.tutorial.app;


import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.udemy.tutorial.constants.EmailConstants;
import com.udemy.tutorial.constants.FileConstants;
import com.udemy.tutorial.data.DataSource;
import com.udemy.tutorial.data.PermanentMarker;
import com.udemy.tutorial.mailer.Emailer;

public class App {
	
	static BlockingQueue<List<String>> pendingEmailQueue;
	static BlockingQueue<List<String>> sentEmailQueue;
	
	public static void main(String args[]) throws InterruptedException {
		System.out.println("Main Thread Started");
		//long startTime = System.currentTimeMillis();
		
		CountDownLatch countDownLatch = new CountDownLatch(1);
		pendingEmailQueue = new ArrayBlockingQueue<>(FileConstants.TOTAL_EMAILS_IN_BATCH);
		sentEmailQueue = new ArrayBlockingQueue<>(FileConstants.TOTAL_EMAILS_IN_BATCH);
		
		DataSource ds = new DataSource(countDownLatch);
		System.out.println("Going to wait now for DataSource to get populated!!");
		
		List<List<String>> pendingEmail = ds.getEmailListPending();
		//System.out.println(pendingEmail);
		pendingEmailQueue.addAll(pendingEmail);
		
		System.out.println("Total Items pending in queue: "+pendingEmailQueue.size());
		
		try {
			
			countDownLatch.await();
			System.out.println("Main Thread:  wait Finished");
			System.out.println(pendingEmail);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExecutorService executors = Executors.newFixedThreadPool(EmailConstants.PARALLEL_WORKERS);
		
		Emailer emailer = new Emailer(pendingEmailQueue,sentEmailQueue);
		
		System.out.println("Starting Mail Threads");
		for(int i=0;i<EmailConstants.PARALLEL_WORKERS;i++) {
			System.out.println("Started Thread:"+i);
			executors.submit(emailer);
		}
		
		
		
		PermanentMarker marker = new PermanentMarker(sentEmailQueue);
		
		System.out.println("Starting Writers");
		ExecutorService writers = Executors.newFixedThreadPool(EmailConstants.PARALLEL_WRITERS);
		
		for(int i=0;i<EmailConstants.PARALLEL_WRITERS;i++) {
			
			writers.submit(marker);
		}
		System.out.println("Mailer Tasks Submitted");
		
		writers.shutdown();
		executors.shutdown();
		
		System.out.println("Executors and writers shutdown gracefully");
		
	}

}
