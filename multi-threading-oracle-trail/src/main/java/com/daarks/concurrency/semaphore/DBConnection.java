package com.daarks.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class DBConnection {

	private static DBConnection connection = new DBConnection();
	private static int count=0;
	private Semaphore semaphore = new Semaphore(30);
	
	private DBConnection() {
		
	}
	
	public static DBConnection getInstance() {
		
		
		return connection;
	}
	
	public void connect() {
		
		try {
			semaphore.acquire();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		synchronized (this) {
			count++;
			System.out.println("Current Connections:"+count);
		}
		
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (this) {
			count--;
			System.out.println("Current Connections:"+count);
		}
		
		semaphore.release();
	}
	
}
