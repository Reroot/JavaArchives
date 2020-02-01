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

import com.ss.lms.models.Author;

/**
 * DAO class to access the author file
 * 
 * @author ryanb
 *
 */
public class AuthorDao {
	
	/**
	 * Reads the author file and returns a list of authors
	 * 
	 * @return List authors in the author file
	 * @throws IOException
	 */
	public List<Author> readFromFile() throws IOException {
		FileInputStream fin = new FileInputStream("./resources/author.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));
		
		List<Author> authors = new ArrayList<>();
		String authorLine;
		
		while ((authorLine = buffReader.readLine()) != null) {
			String[] authorParts = authorLine.split("/");
			authors.add(new Author(new Integer(authorParts[0]), authorParts[1]));
		}
		
		buffReader.close();
		return authors;
	}
	
	/**
	 * Writes a list of authors to the author file
	 * 
	 * @param books
	 * 			List of authors to write to file
	 * @throws IOException
	 */
	public void writeToFile (List<Author> authors) throws IOException {
		FileOutputStream out = new FileOutputStream("./resources/author.txt");
		BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(out));
		
		Collections.sort(authors);
		
		while (authors.size() > 0) {
			Author toBeWriten = authors.remove(0);
			buffWriter.append(toBeWriten.toString());
			if (authors.size() != 0) {
				buffWriter.append("\n");
			}
		}
		
		buffWriter.close();
	}
}
