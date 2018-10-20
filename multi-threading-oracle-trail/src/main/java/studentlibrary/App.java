package studentlibrary;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {


	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ExecutorService executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_STUDENTS);
		Student[] students = null;
		Book[] books = null;
		
		try {
		students = new Student[Constants.NUMBER_OF_STUDENTS];
		books = new Book[Constants.NUMBER_OF_BOOKS];
		
		for(int i=0;i<Constants.NUMBER_OF_BOOKS;i++){
			books[i] = new Book(i);
		}
		
		for(int i=0;i < Constants.NUMBER_OF_STUDENTS; i++)
		{
		   students[i] = new Student(i, new ArrayList<Book>(Arrays.asList(books)));
		   executorService.execute(students[i]);
		}
		
		Thread.sleep(Constants.SIMULATION_RUNNING_TIME);
		
		for(Student student: students)
		{
			student.setTired();
		}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			executorService.shutdown();

			while (!executorService.isTerminated()) {
				Thread.sleep(1000);
			}
			
			for(Book book: books) {
				System.out.println(book);
			}
		}
	}

}
