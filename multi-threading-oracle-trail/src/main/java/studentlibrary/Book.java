package studentlibrary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
	
	private int id;
	private ArrayList<String> comments;
	private Lock lock;
	
	public Book (int id) {
		this.id  = id;
		this.lock = new ReentrantLock();
		comments = new ArrayList<String>();
	}
	
	public boolean pickUp(Student student) throws InterruptedException {
		
		if(lock.tryLock(10,TimeUnit.MILLISECONDS))
		{
			System.out.println("Student: "+ student.getId() + " reading book "+id);
			
			return true;
		}	
		
		return false;
		
	}
	
	public void read() throws InterruptedException {
		
		Thread.sleep(1000);
	}
	
	public void addComment(Student student, String comment) throws InterruptedException {
		
		if(lock.tryLock(10,TimeUnit.MILLISECONDS))
		{
			comments.add(student.getId()+"");
		}	
		
	}
	
	public List<String> getComments() 
	{
		return comments;
	}
	
	public void finished(Student student) {
		
		this.lock.unlock();
		
		System.out.println("Student: "+ student.getId() + " finished reading book "+id);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", comments=" + comments + "]";
	}
	
	

}
