package com.ss.lms.service;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeFalse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.ss.lms.models.Author;
import com.ss.lms.models.Book;
import com.ss.lms.models.Publisher;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DaoManagerTest {

	public Book[] books;
	public Author[] authors;
	public Publisher[] publishers;
	public DaoManager man;

	@Before
	public void startUp() {
		man = new DaoManager();
		books = new Book[10];
		books[0] = new Book(0, "Book1", 0, 0);
		books[1] = new Book(1, "Book2", 0, 0);
		books[2] = new Book(2, "Book3", 4, 4);
		books[3] = new Book(3, "Book4", 3, 6);
		books[4] = new Book(4, "Book5", 6, 8);
		books[5] = new Book(5, "Book6", 8, 3);
		books[6] = new Book(6, "Book7", 3, 2);
		books[7] = new Book(7, "Book8", 4, 8);
		books[8] = new Book(8, "Book9", 1, 6);
		books[9] = new Book(9, "Book10", 6, 6);
		authors = new Author[10];
		authors[0] = new Author(0, "Author1");
		authors[1] = new Author(1, "Author2");
		authors[2] = new Author(2, "Author3");
		authors[3] = new Author(3, "Author4");
		authors[4] = new Author(4, "Author5");
		authors[5] = new Author(5, "Author6");
		authors[6] = new Author(6, "Author7");
		authors[7] = new Author(7, "Author8");
		authors[8] = new Author(8, "Author9");
		authors[9] = new Author(9, "Author10");
		publishers = new Publisher[10];
		publishers[0] = new Publisher(0, "Publisher1", "1 Street");
		publishers[1] = new Publisher(1, "Publisher2", "2 Street");
		publishers[2] = new Publisher(2, "Publisher3", "3 Street");
		publishers[3] = new Publisher(3, "Publisher4", "4 Street");
		publishers[4] = new Publisher(4, "Publisher5", "5 Street");
		publishers[5] = new Publisher(5, "Publisher6", "6 Street");
		publishers[6] = new Publisher(6, "Publisher7", "7 Street");
		publishers[7] = new Publisher(7, "Publisher8", "8 Street");
		publishers[8] = new Publisher(8, "Publisher9", "9 Street");
		publishers[9] = new Publisher(9, "Publisher10", "10 Street");
	}

	@Test
	public void testCreateAuthorTable() {
		for (Author author : authors) {
			man.CreateAuthor(author);
		}
	}

	@Test
	public void testCreatePublisherTable() {
		for (Publisher publisher : publishers) {
			man.CreatePublisher(publisher);
		}
	}

	@Test
	public void testCreatezBookTable() {
		for (Book book : books) {
			man.CreateBook(book);
		}
	}

	@Test
	public void testReadBook() {
		assertEquals(man.ReadBook(new Book(1, null, null, null)), "Found books:\n1/Book2/0/0");
		assertEquals(man.ReadBook(new Book(null, "Book4", null, null)),
				"Found books:\n3/Book4/3/6");
		assertEquals(man.ReadBook(new Book(null, null, 3, null)),
				"Found books:\n3/Book4/3/6\n6/Book7/3/2");
		assertEquals(man.ReadBook(new Book(null, null, null, 6)),
				"Found books:\n3/Book4/3/6\n8/Book9/1/6\n9/Book10/6/6");
	}

	@Test
	public void testReadAuthor() {
		assumeFalse(false);
//		fail("Not yet implemented");
	}

	@Test
	public void testReadPublisher() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateBook() {
		System.out.println(man.UpdateBook(new Book(5, null, null, null), new Book(null, "New Book",
				1, 1)));
	}

	@Test
	public void testUpdateAuthor() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePublisher() {
		fail("Not yet implemented");
	}

	@Test
	public void testzDeleteBook() {
		man.DeleteBook(new Book(null, "New Book", null, null));
	}

	@Test
	public void testzDeleteAuthor() {
		man.DeleteAuthor(new Author(3, null));
	}

	@Test
	public void testzDeletePublisher() {
		man.DeletePublisher(new Publisher(6, null, null));
	}

}
