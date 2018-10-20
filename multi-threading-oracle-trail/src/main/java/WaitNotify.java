import java.util.ArrayList;
import java.util.Random;

class Processor {
	
	private ArrayList<String> messages;
	
	private Object lock;
	
	public Processor() {
		messages = new ArrayList<String>();
		lock = new Object();
	}
	
	
	public void producer() {
		
		synchronized (lock) {
			
			if(messages.size() >= 100) {
				try {
					
					System.out.println("Message Queue is full. Producer going to wait");
					lock.wait();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("Interrupted in Producer");
				}
			}
			
			Random r1 = new Random();
			Integer message = r1.nextInt(1000);
			messages.add(message.toString());	
			System.out.println("New Message "+ message +" to Produced. Notify All Consumers");	
			lock.notifyAll();
		}
		
	}
	
	public void consumer() {
		
		synchronized (lock) {
			
			if(messages.isEmpty()) {
				try {
					System.out.println("Message Queue is empty. Consumer going to wait");
					lock.wait();
				} catch (InterruptedException e) {
					
					System.out.println("Interrupted in Consumer");
				}
			}
			
			System.out.println("Message "+ messages.get(0) +" cosumed. Notify All producers");	
			messages.remove(0);
			lock.notifyAll();
		}
	}
	
}


public class WaitNotify {

	public static void main(String args[])
	{
		final Processor processor = new Processor();
		
		Thread p1 = new Thread(new Runnable() {
			
			public void run() {
				
				processor.producer();
				
			}
		});
		
		p1.setName("First Producer");
		
		
		Thread p2 = new Thread(new Runnable() {
			
			public void run() {
				
				for(int i=0; i < 1000;i++)
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					processor.producer();
				}
			}
		});
		
		p2.setName("Second Producer");

		Thread c1 = new Thread(new Runnable() {
			
			public void run() {
				
				for(int i=0; i < 1000;i++)
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					processor.consumer();
				}
			}
		});
		
		c1.setName("First Producer");		
		
		
		System.out.println("Starting Producers and consumers");
		
		p1.start();
		p2.start();
		c1.start();
		
		System.out.println("End");
	}
}
