package com.daarks.concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class DBConnectionApp {

	public static void main(String[] args) {
		
		
		
		ExecutorService executors = Executors.newCachedThreadPool();
		
		for(int i=0;i<100;i++) {
		
			executors.submit(new Runnable() {

				@Override
				public void run() {
					
				DBConnection.getInstance().connect();
					
				}
				
			});
		}
	}
}
