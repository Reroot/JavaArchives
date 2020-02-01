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

import com.ss.lms.models.Publisher;

/**
 * DAO class to access the publisher file
 * 
 * @author ryanb
 *
 */
public class PublisherDao {
	
	/**
	 * Reads the publisher file and returns list of publisher
	 * 
	 * @return	List of publishers inside the file
	 * @throws IOException
	 */
	public List<Publisher> readFromFile() throws IOException {
		FileInputStream fin = new FileInputStream("./resources/publisher.txt");
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(fin));
		
		List<Publisher> publishers = new ArrayList<>();
		String publisherLine;
		
		while ((publisherLine = buffReader.readLine()) != null) {
			String[] publisherParts = publisherLine.split("/");
			publishers.add(new Publisher(new Integer(publisherParts[0]), 
					publisherParts[1], publisherParts[2]));
		}
		
		buffReader.close();
		return publishers;
	}
	
	/**
	 * Writes list of publishers to publisher file
	 * 
	 * @param publishers
	 * 			List of publishers to write to file
	 * @throws IOException
	 */
	public void writeToFile (List<Publisher> publishers) throws IOException {
		FileOutputStream out = new FileOutputStream("./resources/publisher.txt");
		BufferedWriter buffWriter = new BufferedWriter(new OutputStreamWriter(out));
		
		Collections.sort(publishers);
		
		while (publishers.size() > 0) {
			Publisher toBeWriten = publishers.remove(0);
			buffWriter.append(toBeWriten.toString());
			if (publishers.size() != 0) {
				buffWriter.append("\n");
			}
		}
		
		buffWriter.close();
	}
}
