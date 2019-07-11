package com.udemy.tutorial.data;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.opencsv.CSVWriter;
import com.udemy.tutorial.constants.FileConstants;

public class PermanentMarker implements Runnable {

	private BlockingQueue<List<String>> sentEmailQueue;
	private Lock writerLock = new ReentrantLock();
	private Integer writtenEmailCount=0;


	public PermanentMarker(BlockingQueue<List<String>> sentEmailQueue) {
		super();
		this.sentEmailQueue = sentEmailQueue;
	}

	@Override
	public void run() {
		
		writetoCSV(FileConstants.EMAIL_OUTPUT_FILE_NAME);
		
	}
	
	private void writetoCSV(String fileName) {
		
		System.out.println("Permanent Marker request by: "+Thread.currentThread().getName());
		//URL url = getClass().getResource(fileName);
		File file = new File(fileName); 

		try {
			    
			while( writtenEmailCount < FileConstants.TOTAL_EMAILS_IN_BATCH) {
				
				
				if(! sentEmailQueue.isEmpty())
				{
					System.out.println("Pending email to be written: "+ sentEmailQueue.size());
					writerLock.lock();
					System.out.println("Permanent Marker taken by: "+Thread.currentThread().getName());
				 	FileWriter outputfile = new FileWriter(file,true); 
			        // create CSVWriter object filewriter object as parameter 
			        CSVWriter writer = new CSVWriter(outputfile); 
			  
			        // create a List which contains String array 
			        List<String> emailRecord =  sentEmailQueue.take();
			        String[] data = { emailRecord.get(0), emailRecord.get(1) }; 
			        writer.writeNext(data); 
			  
			        // closing writer connection 
			        writer.close(); 
			        writtenEmailCount++;
			        System.out.println("Permanent Marker released by: "+Thread.currentThread().getName());
			        writerLock.unlock();
		        
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
}
