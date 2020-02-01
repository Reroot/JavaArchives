package com.ss.lms.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ss.lms.models.Book;

/**
 * DAO class to access the book file
 * 
 * @author ryanb
 *
 */
public class BookDao {

	/**
	 * Reads the book file and returns a list of books
	 * 
	 * @return List books in the book file
	 * @throws IOException
	 */
	public List<Book> readFromFile() throws IOException {
		FileInputStream fin = new FileInputStream("./resources/book.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));
		
		List<Book> books = new ArrayList<>();
		String bookLine;
		
		while ((bookLine = buffReader.readLine()) != null) {
			String[] bookParts = bookLine.split("/");
			books.add(new Book(new Integer(bookParts[0]), bookParts[1], 
					new Integer(bookParts[2]), new Integer(bookParts[3])));
		}
		
		buffReader.close();
		return books;
	}
	
	/**
	 * Writes a list of books to the book file
	 * 
	 * @param books
	 * 			List of books to write to file
	 * @throws IOException
	 */
	public void writeToFile (List<Book> books) throws IOException {
		FileOutputStream out = new FileOutputStream("./resources/book.txt");
		BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(out));
		
		Collections.sort(books);
		
		while (books.size() > 0) {
			Book toBeWriten = books.remove(0);
			buffWriter.append(toBeWriten.toString());
			if (books.size() != 0) {
				buffWriter.append("\n");
			}
		}
		
		buffWriter.close();
	}
}
