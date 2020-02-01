package com.ss.lms.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.dao.AuthorDao;
import com.ss.lms.dao.BookDao;
import com.ss.lms.dao.PublisherDao;
import com.ss.lms.models.Author;
import com.ss.lms.models.Book;
import com.ss.lms.models.Publisher;

/**
 * Service class that reads from DAOs, performs desired actions, and writes new
 * information to file
 * 
 * @author ryanb
 *
 */
public class DaoManager {

	private BookDao bookDao;
	private AuthorDao authorDao;
	private PublisherDao publisherDao;

	/**
	 * Default constructor for DAOs
	 */
	public DaoManager() {
		bookDao = new BookDao();
		authorDao = new AuthorDao();
		publisherDao = new PublisherDao();
	}

	/**
	 * This method takes in the book object, checks for a unique id, checks the
	 * that the author and publisher id exist in their respective tables and
	 * stores the record into the book table
	 * 
	 * @param record
	 *            Book object to be added
	 * @return String exit message
	 */
	public String CreateBook(Book record) {
		List<Book> bookTable = new ArrayList<>();
		List<Author> authorTable = new ArrayList<>();
		List<Publisher> publisherTable = new ArrayList<>();

		try {
			bookTable = bookDao.readFromFile();
			authorTable = authorDao.readFromFile();
			publisherTable = publisherDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Check for unique ids
		// null values don't need to be passed in,
		if (this.searchInTableBook(bookTable, new Book(record.getId(), null, null, null))
				.size() != 0) {
			return "Id is not unique";
		} else if (this.searchInTableAuthor(authorTable, new Author(record.getAuthorId(), null))
				.size() == 0) {
			return "Author id is not in author table";
		} else if (this.searchInTablePublisher(publisherTable, new Publisher(record
				.getPublisherId(), null, null)).size() == 0) {
			return "Publisher id is not in publisher table";
		}

		if (!bookTable.contains(record)) {
			bookTable.add(record);
			try {
				bookDao.writeToFile(bookTable);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Record creation successful";
		}

		return "Record already exists in the table";
	}

	/**
	 * This method takes in the author object, checks for a unique id, and
	 * stores the record into the author table
	 * 
	 * @param record
	 *            Author object to be added
	 * @return String exit message
	 */
	public String CreateAuthor(Author record) {
		List<Author> table = new ArrayList<>();

		try {
			table = authorDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this.searchInTableAuthor(table, new Author(record.getId(), null)).size() != 0) {
			return "Id is not unique";
		}

		if (!table.contains(record)) {
			table.add(record);
			try {
				authorDao.writeToFile(table);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Record creation successful";
		}

		return "Record already exists in the table";
	}

	/**
	 * This method takes in the publisher object, checks for a unique id, and
	 * stores the record into the publisher table
	 * 
	 * @param record
	 *            Publisher object to be added
	 * @return String exit message
	 */
	public String CreatePublisher(Publisher record) {
		List<Publisher> table = new ArrayList<>();

		try {
			table = publisherDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (this.searchInTablePublisher(table, new Publisher(record.getId(), null, null))
				.size() != 0) {
			return "Id is not unique";
		}

		if (!table.contains(record)) {
			table.add(record);
			try {
				publisherDao.writeToFile(table);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Record creation successful";
		}

		return "Record already exists in the table";
	}

	/**
	 * Reads book table for all fields in the inputed book. Fields are inclusive
	 * so if the record has an id of 4 and name of Blue, this method will return
	 * all books with either the id of 4 or name of Blue.
	 * 
	 * @param record
	 *            Book to be read (ignored fields to be left null)
	 * @return String exit message with either found books or no book found
	 */
	public String ReadBook(Book record) {
		List<Book> table = new ArrayList<>();

		try {
			table = bookDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
			return "File Error";
		}

		List<Book> matchingRecords = this.searchInTableBook(table, record);

		if (matchingRecords.size() > 0) {
			return "Found books:\n" + BookArrayToString(matchingRecords.toArray(new Book[0]));
		}

		return "Book not found";
	}

	/**
	 * Reads author table for all fields in the inputed author. Fields are
	 * inclusive so if the record has an id of 4 and name of Blue, this method
	 * will return all books with either the id of 4 or name of Blue.
	 * 
	 * @param record
	 *            Author to be read (ignored fields to be left null)
	 * @return String exit message with either found authors or no author found
	 */
	public String ReadAuthor(Author record) {
		List<Author> table = new ArrayList<>();

		try {
			table = authorDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Author> matchingRecords = this.searchInTableAuthor(table, record);

		if (matchingRecords.size() > 0) {
			return "Found authors:\n" + AuthorArrayToString(matchingRecords.toArray(new Author[0]));
		}

		return "Publisher not found";
	}

	/**
	 * Reads publisher table for all fields in the inputed publisher. Fields are
	 * inclusive so if the record has an id of 4 and name of Blue, this method
	 * will return all books with either the id of 4 or name of Blue.
	 * 
	 * @param record
	 *            Publisher to be read (ignored fields to be left null)
	 * @return String exit message with either found publishers or no publisher
	 *         found
	 */
	public String ReadPublisher(Publisher record) {
		List<Publisher> table = new ArrayList<>();

		try {
			table = publisherDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		List<Publisher> matchingRecords = this.searchInTablePublisher(table, record);

		if (matchingRecords.size() > 0) {
			return "Found publishers:\n" + PublisherArrayToString(matchingRecords.toArray(
					new Publisher[0]));
		}

		return "Publisher not found";
	}

	/**
	 * Reads for oldRecord in table and will update it with newRecord's
	 * information excluding the oldRecord's id
	 * 
	 * @param oldRecord
	 *            Book to be searched for
	 * @param newRecord
	 *            Book to replace the oldRecord
	 * @return String exit message
	 */
	public String UpdateBook(Book oldRecord, Book newRecord) {
		List<Book> table = new ArrayList<>();

		try {
			table = bookDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (table.contains(newRecord)) {
			return "Book already found in table";
		}

		List<Book> foundBooks = this.searchInTableBook(table, oldRecord);

		if (foundBooks.size() == 1) {
			oldRecord = foundBooks.get(0);
			Integer index = table.indexOf(oldRecord);
			Book toBeUpdated = new Book(oldRecord.getId(), newRecord.getName(), newRecord
					.getAuthorId(), newRecord.getPublisherId());
			table.set(index, toBeUpdated);

			try {
				bookDao.writeToFile(table);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return toBeUpdated.toString();

		} else if (foundBooks.size() == 0) {
			return "Book not found in table";
		}

		return "Multiple books found in table";
	}

	/**
	 * Reads for oldRecord in table and will update it with newRecord's
	 * information excluding the oldRecord's id
	 * 
	 * @param oldRecord
	 *            Author to be searched for
	 * @param newRecord
	 *            Author to replace the oldRecord
	 * @return String exit message
	 */
	public String UpdateAuthor(Author oldRecord, Author newRecord) {
		List<Author> table = new ArrayList<>();

		try {
			table = authorDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (table.contains(newRecord)) {
			return "Author already found in table";
		}

		List<Author> foundAuthors = this.searchInTableAuthor(table, oldRecord);

		if (foundAuthors.size() == 1) {
			oldRecord = foundAuthors.get(0);
			Integer index = table.indexOf(oldRecord);
			Author toBeUpdated = new Author(oldRecord.getId(), newRecord.getName());
			table.set(index, toBeUpdated);

			try {
				authorDao.writeToFile(table);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return toBeUpdated.toString();

		} else if (foundAuthors.size() == 0) {
			return "Author not found in table";
		}

		return "Multiple authors found in table";
	}

	/**
	 * Reads for oldRecord in table and will update it with newRecord's
	 * information excluding the oldRecord's id
	 * 
	 * @param oldRecord
	 *            Publisher to be searched for
	 * @param newRecord
	 *            Publisher to replace the oldRecord
	 * @return String exit message
	 */
	public String UpdatePublisher(Publisher oldRecord, Publisher newRecord) {
		List<Publisher> table = new ArrayList<>();

		try {
			table = publisherDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (table.contains(newRecord)) {
			return "Publisher already found in table";
		}

		List<Publisher> foundPublishers = this.searchInTablePublisher(table, oldRecord);

		if (foundPublishers.size() == 1) {
			oldRecord = foundPublishers.get(0);
			Integer index = table.indexOf(oldRecord);
			Publisher toBeUpdated = new Publisher(oldRecord.getId(), newRecord.getName(), newRecord
					.getAddress());
			table.set(index, toBeUpdated);

			try {
				publisherDao.writeToFile(table);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return toBeUpdated.toString();

		} else if (foundPublishers.size() == 0) {
			return "Publisher not found in table";
		}

		return "Multiple publishers found in table";
	}

	/**
	 * Reads the table for the records matching any fields inputed and then if
	 * found, the book record(s) will be deleted
	 * 
	 * @param record
	 *            To be Deleted
	 * @return String exit message
	 */
	public String DeleteBook(Book record) {
		List<Book> table = new ArrayList<>();

		try {
			table = bookDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Book> foundBooks = this.searchInTableBook(table, record);

		if (foundBooks.size() > 0) {
			for (Book b : foundBooks) {
				table.remove(b);
			}

			try {
				bookDao.writeToFile(table);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "Delete successful";
		}
		return "Book not found in table";
	}

	/**
	 * Reads the table for the records matching any fields inputed and then if
	 * found, the author records will be deleted along with any books written by
	 * the author records
	 * 
	 * @param record
	 *            To be Deleted
	 * @return String exit message
	 */
	public String DeleteAuthor(Author record) {
		List<Author> authorTable = new ArrayList<>();

		try {
			authorTable = authorDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Author> foundAuthors = this.searchInTableAuthor(authorTable, record);

		if (foundAuthors.size() > 0) {
			List<Book> bookTable = new ArrayList<>();

			try {
				bookTable = bookDao.readFromFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (Author remove : foundAuthors) {
				authorTable.remove(remove);
				// Delete all books with this author
				List<Book> toBeDeleted = this.searchInTableBook(bookTable, new Book(null, null,
						remove.getId(), null));
				for (Book b : toBeDeleted) {
					bookTable.remove(b);
				}
			}

			try {
				authorDao.writeToFile(authorTable);
				bookDao.writeToFile(bookTable);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "Delete successful";
		}

		return "Author not found in table";
	}

	/**
	 * Reads the table for the records matching any fields inputed and then if
	 * found, the publisher records will be deleted along with any books written
	 * by the publisher records
	 * 
	 * @param record
	 *            To be Deleted
	 * @return String exit message
	 */
	public String DeletePublisher(Publisher record) {
		List<Publisher> publisherTable = new ArrayList<>();

		try {
			publisherTable = publisherDao.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Publisher> foundPublishers = this.searchInTablePublisher(publisherTable, record);

		if (foundPublishers.size() > 0) {
			List<Book> bookTable = new ArrayList<>();

			try {
				bookTable = bookDao.readFromFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			for (Publisher remove : foundPublishers) {
				publisherTable.remove(remove);
				// Delete all books with this publisher
				List<Book> toBeDeleted = this.searchInTableBook(bookTable, new Book(null, null,
						null, remove.getId()));
				for (Book b : toBeDeleted) {
					bookTable.remove(b);
				}
			}

			try {
				publisherDao.writeToFile(publisherTable);
				bookDao.writeToFile(bookTable);
			} catch (IOException e) {
				e.printStackTrace();
			}

			return "Delete successful";
		}

		return "Author not found in table";
	}

	/**
	 * Searches through the list for any fields that match the record
	 * 
	 * @param books
	 *            List of books in the table
	 * @param record
	 *            Book to be searched for
	 * @return List all books found that match any field
	 */
	private List<Book> searchInTableBook(List<Book> books, Book record) {
		List<Book> matchingRecords = new ArrayList<>();

		for (Book b : books) {
			if (b.getId().equals(record.getId())) {
				matchingRecords.add(b);
			} else if (b.getName().equals(record.getName())) {
				matchingRecords.add(b);
			} else if (b.getAuthorId().equals(record.getAuthorId())) {
				matchingRecords.add(b);
			} else if (b.getPublisherId().equals(record.getPublisherId())) {
				matchingRecords.add(b);
			}
		}

		return matchingRecords;
	}

	/**
	 * Searches through the list for any fields that match the record
	 * 
	 * @param authors
	 *            List of authors in the table
	 * @param record
	 *            Author to be searched for
	 * @return List all authors found that match any field
	 */
	private List<Author> searchInTableAuthor(List<Author> authors, Author record) {
		List<Author> matchingRecords = new ArrayList<>();

		for (Author a : authors) {
			if (a.getId().equals(record.getId())) {
				matchingRecords.add(a);
			} else if (a.getName().equals(record.getName())) {
				matchingRecords.add(a);
			}
		}

		return matchingRecords;
	}

	/**
	 * Searches through the list for any fields that match the record
	 * 
	 * @param publishers
	 *            List of publishers in the table
	 * @param record
	 *            Publisher to be searched for
	 * @return List all publishers found that match any field
	 */
	private List<Publisher> searchInTablePublisher(List<Publisher> publishers, Publisher record) {
		List<Publisher> matchingRecords = new ArrayList<>();

		for (Publisher p : publishers) {
			if (p.getId().equals(record.getId())) {
				matchingRecords.add(p);
			} else if (p.getName().equals(record.getName())) {
				matchingRecords.add(p);
			} else if (p.getAddress().equals(record.getAddress())) {
				matchingRecords.add(p);
			}
		}

		return matchingRecords;
	}

	/**
	 * Takes an array of books and turns it into a string representation using
	 * the toString() method
	 * 
	 * @param books
	 *            Array to be turned into a string
	 * @return String representation of the array
	 */
	private String BookArrayToString(Book[] books) {
		StringBuilder sb = new StringBuilder();

		for (Book b : books) {
			if (sb.length() != 0) {
				sb.append("\n");
			}
			sb.append(b.toString());
		}

		return sb.toString();
	}

	/**
	 * Takes an array of authors and turns it into a string representation using
	 * the toString() method
	 * 
	 * @param authors
	 *            Array to be turned into a string
	 * @return String representation of the array
	 */
	private String AuthorArrayToString(Author[] authors) {
		StringBuilder sb = new StringBuilder();

		for (Author a : authors) {
			if (sb.length() != 0) {
				sb.append("\n");
			}
			sb.append(a.toString());
		}

		return sb.toString();
	}

	/**
	 * Takes an array of publishers and turns it into a string representation
	 * using the toString() method
	 * 
	 * @param publishers
	 *            Array to be turned into a string
	 * @return String representation of the array
	 */
	private String PublisherArrayToString(Publisher[] publishers) {
		StringBuilder sb = new StringBuilder();

		for (Publisher p : publishers) {
			if (sb.length() != 0) {
				sb.append("\n");
			}
			sb.append(p.toString());
		}

		return sb.toString();
	}
}
