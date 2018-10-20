package studentlibrary;

import java.util.List;
import java.util.Random;

public class Student implements Runnable {

	private int id;
	private Book book;
	private List<Book> books;
	private boolean tired = false;
	private int currentInReading = 0;

	public Student(int id, List<Book> books) {

		this.id = id;
		this.books = books;
	}

	public Student(int id) {

		this.id = id;
	}

	public void run() {
		// TODO Auto-generated method stub

		try {

			while (!tired) {
				Thread.sleep(1000);
				if (this.getRandomBook().pickUp(this)) {

					if (currentInReading < 2) {
						currentInReading++;
						// System.out.println("Student: "+id + " reading");
						this.book.read();
						this.book.addComment(this, "Comment");
						currentInReading--;
						Thread.sleep(100);
					}
					else
					{
						System.out.println("Student: "+ this.id + "Only: "+Constants.MAX_BOOKS_PER_STUDENT + " are allowed.");
					}
				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setTired() {
		this.tired = true;
	}

	public Book getBookById(int id) {

		return books.get(id);
	}

	public Book getRandomBook() {

		Random random = new Random();

		int book_id = random.nextInt(Constants.NUMBER_OF_BOOKS - 1);
		this.book = books.get(book_id);
		// System.out.println(this.book);
		return books.get(book_id);
	}

}
