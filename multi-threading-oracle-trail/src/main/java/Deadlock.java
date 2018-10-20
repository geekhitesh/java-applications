
class Resource {
	
	private Object lock1;
	private Object lock2;
	
	public Resource()
	{
		lock1 = new Object();
		lock2 = new Object();
	}
	
	public void resource1() throws InterruptedException {
		
		System.out.println("Lock 1 => "+ Thread.currentThread().getName());
		
		Thread.sleep(1000);
		synchronized (lock1) {
			
			
			
			resource2();
			
		}
	}
	
	public void resource2() throws InterruptedException {
		
		System.out.println("Lock 2 => "+ Thread.currentThread().getName());
		
		Thread.sleep(1000);
		synchronized(lock2) {
			
			//System.out.println("Lock 2" + Thread.currentThread().getName());
			resource1();
		}
		
	}
	
}

public class Deadlock {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final Resource resource = new Resource();
		
		Thread t1 = new Thread(new Runnable() {
			
			public void run() {
				
				try {
					resource.resource1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		t1.setName("Thread 1");
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
				
				try {
					resource.resource2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		t2.setName("Thread 2");
		
		System.out.println("Work Started");
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();

		System.out.println("Work Finished");
	}

}
