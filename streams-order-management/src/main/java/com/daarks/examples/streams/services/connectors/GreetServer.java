package com.daarks.examples.streams.services.connectors;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daarks.examples.streams.services.entities.Order;
import com.daarks.examples.streams.services.producers.OrderProducer;

@Component
public class GreetServer {

	private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static long startTime=0;
    @Autowired
	private OrderProducer orderProducer;

 
    public void start(int port) {

        try {
        	System.out.println("Socket Server Started");
			serverSocket = new ServerSocket(port);
            Order order = new Order(1,"hello", "hello");
            orderProducer.push(order);
			while (true)
	            new EchoClientHandler(serverSocket.accept()).start();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        

    }
 
    public void stop() {
        try {
        	
			in.close();
	        out.close();
	        clientSocket.close();
	        serverSocket.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

    }
    
    
    private  class EchoClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

 
        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
            if(startTime ==0) {
            	startTime = System.currentTimeMillis();
            	System.out.println("Started at:"+startTime);
            }
        }
 
        public void run() {
            try {
            
				out = new PrintWriter(clientSocket.getOutputStream(), true);
	            in = new BufferedReader(
	                    new InputStreamReader(clientSocket.getInputStream()));
	            
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                if (".".equals(inputLine)) {
	                    out.println("bye");
	                    break;
	                }
	                //System.out.println("Message aya hai tumhara");
	                out.println(inputLine);
	                
	                System.out.println("Message <"+inputLine+"> processed by thread: "+
	                                   Thread.currentThread().getName()
	                                   +" at:" + ((System.currentTimeMillis() - startTime)/1000)
	                                   +" second");
	                String message = "Message <"+inputLine+"> processed by thread: "+
                            Thread.currentThread().getName()
                            +" at:" + ((System.currentTimeMillis() - startTime)/1000)
                            +" second";
		            Order order = new Order((int) System.currentTimeMillis(),message, message);
		            orderProducer.push(order);
	            }
	            

	 
	            in.close();
	            out.close();
	            clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

             

     }
    }
	
}

