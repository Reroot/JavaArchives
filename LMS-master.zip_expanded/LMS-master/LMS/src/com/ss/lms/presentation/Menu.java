package com.ss.lms.presentation;

import java.util.Scanner;
import com.ss.lms.models.Author;
import com.ss.lms.models.Book;
import com.ss.lms.models.Publisher;
import com.ss.lms.service.DaoManager;

public class Menu {

	private Scanner scan;
	/**
	 * Service for LMS
	 */
	private DaoManager man = new DaoManager();

	/**
	 * Note for reading objects in tables
	 */
	private String importantReadNote = "*NOTE* This function will return all items with any field equal to"
			+ "the ones entered\ni.e. if you enter in id=4 and name=Blue this will "
			+ "print all objects with either id=4 or name=Blue\n" + "Fields may be left blank";

	/**
	 * Note for updating objects in tables
	 */
	private String importantUpdateNote = "*NOTE* This function will return all items with any field equal to"
			+ "the ones entered\ni.e. if you enter in id=4 and name=Blue this will "
			+ "update an object with either id=4 or name=Blue\n"
			+ "Fields may be left blank and only one item can be updated at a time\n"
			+ "Fields left blank in the new record will take the corresponding field"
			+ "from the old record";

	/**
	 * Note for deleting objects in the tables
	 */
	private String importantDeleteNote = "*NOTE* This function will delete all items with any field equal to"
			+ "the ones entered\ni.e. if you enter in id=4 and name=Blue this willv"
			+ "delete all objects with either id=4 or name=Blue\n" + "Fields may be left blank";

	/**
	 * This will provide a UI to interact with LMS system. This method runs the
	 * CRUD UI and calls the appropriate method
	 */
	public void mainLoop() {
		scan = new Scanner(System.in);
		String instruction = "";
		System.out.println("Welcome to the LMS system");
		while (!instruction.toLowerCase().equals("q")) {
			System.out.println("\nEnter next command:"
					+ "\n(C)reate\n(R)ead\n(U)pdate\n(D)elete\n(Q)uit");
			instruction = scan.nextLine();
			switch (instruction.toLowerCase()) {
			case "c":
				Create();
				break;
			case "r":
				Read();
				break;
			case "u":
				Update();
				break;
			case "d":
				Delete();
				break;
			case "q":
				break;
			default:
				System.out.println("Command not recongnized\n"
						+ "Please any letter in the parentheses.");
				break;
			}
		}
		System.out.println("Quitting");
		scan.close();
	}

	/**
	 * UI for the create portion of CRUD. This method picks which object type
	 * will be created.
	 */
	private void Create() {
		boolean validCommand = false;
		String instruction = "";
		while (!validCommand) {
			System.out.println("Enter table will you be working with:\n"
					+ "(B)ook\n(A)uthor\n(P)ublisher");
			instruction = scan.nextLine();
			switch (instruction.toLowerCase()) {
			case "b":
				CreateBook();
				validCommand = true;
				break;
			case "a":
				CreateAuthor();
				validCommand = true;
				break;
			case "p":
				CreatePublisher();
				validCommand = true;
				break;
			default:
				System.out.println("Command not recongnized\n"
						+ "Please any letter in the parentheses.");
				break;
			}
		}
	}

	/**
	 * UI to create a book
	 */
	private void CreateBook() {
		Book record = new Book(null, null, null, null);
		String toBeEntered = "";
		do {
			System.out.println("Enter an unique id (must be integer):");
			toBeEntered = scan.nextLine();
		} while (!this.isInteger(toBeEntered));
		record.setId(new Integer(toBeEntered));

		do {
			System.out.println("Enter the book name (cannot contain /):");
			toBeEntered = scan.nextLine();
		} while (!this.containsSlash(toBeEntered));
		record.setName(toBeEntered);

		do {
			System.out.println("Enter an author id (must be integer):");
			toBeEntered = scan.nextLine();
		} while (!this.isInteger(toBeEntered));
		record.setAuthorId(new Integer(toBeEntered));

		do {
			System.out.println("Enter a publisher id (must be integer):");
			toBeEntered = scan.nextLine();
		} while (!this.isInteger(toBeEntered));
		record.setPublisherId(new Integer(toBeEntered));

		System.out.println(man.CreateBook(record));
	}

	/**
	 * UI to create an author
	 */
	private void CreateAuthor() {
		Author record = new Author(null, null);
		String toBeEntered = "";
		do {
			System.out.println("Enter an unique id (must be integer):");
			toBeEntered = scan.nextLine();
		} while (!this.isInteger(toBeEntered));
		record.setId(new Integer(toBeEntered));

		do {
			System.out.println("Enter the author name (cannot contain /):");
			toBeEntered = scan.nextLine();
		} while (!this.containsSlash(toBeEntered));
		record.setName(toBeEntered);

		System.out.println(man.CreateAuthor(record));
	}

	/**
	 * UI to create a publisher
	 */
	private void CreatePublisher() {
		Publisher record = new Publisher(null, null, null);
		String toBeEntered = "";
		do {
			System.out.println("Enter an unique id (must be integer):");
			toBeEntered = scan.nextLine();
		} while (!this.isInteger(toBeEntered));
		record.setId(new Integer(toBeEntered));

		do {
			System.out.println("Enter the publisher name (cannot contain /):");
			toBeEntered = scan.nextLine();
		} while (!this.containsSlash(toBeEntered));
		record.setName(toBeEntered);

		do {
			System.out.println("Enter the publisher address (cannot contain /):");
			toBeEntered = scan.nextLine();
		} while (!this.containsSlash(toBeEntered));
		record.setName(toBeEntered);

		System.out.println(man.CreatePublisher(record));
	}

	/**
	 * UI for the read portion of CRUD. This method picks which object type
	 * will be read.
	 */
	private void Read() {
		boolean validCommand = false;
		String instruction = "";
		while (!validCommand) {
			System.out.println("Enter table will you be working with:\n"
					+ "(B)ook\n(A)uthor\n(P)ublisher");
			instruction = scan.nextLine();
			switch (instruction.toLowerCase()) {
			case "b":
				ReadBook();
				validCommand = true;
				break;
			case "a":
				ReadAuthor();
				validCommand = true;
				break;
			case "p":
				ReadPublisher();
				validCommand = true;
				break;
			default:
				System.out.println("Command not recongnized\n"
						+ "Please any letter in the parentheses.");
				break;
			}
		}

	}

	/**
	 * UI to read a book
	 */
	private void ReadBook() {
		Book record = new Book(null, null, null, null);
		String toBeEntered = "";
		System.out.println(importantReadNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter the book name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setName(toBeEntered);
		}
		continueLooping = true;
		do {
			System.out.println("Enter an author id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setAuthorId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter a publisher id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setPublisherId(new Integer(toBeEntered));
		}

		System.out.println(man.ReadBook(record));
	}

	/**
	 * UI to read an author
	 */
	private void ReadAuthor() {
		Author record = new Author(null, null);
		String toBeEntered = "";
		System.out.println(importantReadNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter the author name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setName(toBeEntered);
		}

		System.out.println(man.ReadAuthor(record));
	}

	/**
	 * UI to read a publisher
	 */
	private void ReadPublisher() {
		Publisher record = new Publisher(null, null, null);
		String toBeEntered = "";
		System.out.println(importantReadNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter the publisher name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setName(toBeEntered);
		}
		continueLooping = true;
		do {
			System.out.println("Enter the publisher address (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setAddress(toBeEntered);
		}

		System.out.println(man.ReadPublisher(record));
	}

	/**
	 * UI for the update portion of CRUD. This method picks which object type
	 * will be updated.
	 */
	private void Update() {
		boolean validCommand = false;
		String instruction = "";
		while (!validCommand) {
			System.out.println("Enter table will you be working with:\n"
					+ "(B)ook\n(A)uthor\n(P)ublisher");
			instruction = scan.nextLine();
			switch (instruction.toLowerCase()) {
			case "b":
				UpdateBook();
				validCommand = true;
				break;
			case "a":
				UpdateAuthor();
				validCommand = true;
				break;
			case "p":
				UpdatePublisher();
				validCommand = true;
				break;
			default:
				System.out.println("Command not recongnized\n"
						+ "Please any letter in the parentheses.");
				break;
			}
		}

	}

	/**
	 * UI to update a book
	 */
	private void UpdateBook() {
		Book newRecord = new Book(null, null, null, null);
		Book oldRecord = new Book(null, null, null, null);
		String toBeEntered = "";
		System.out.println(importantUpdateNote);
		boolean continueLooping = true;
		
		do {
			System.out.println("Enter the id to be updated (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setId(new Integer(toBeEntered));
		}
		
		continueLooping = true;
		do {
			System.out.println("Enter the book name to be updated (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setName(toBeEntered);
		}
		continueLooping = true;
		do {
			System.out.println("Enter an author id to be updated (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setAuthorId(new Integer(toBeEntered));
		}

		do {
			System.out.println("Enter a publisher id to be updated (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setPublisherId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter new book name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			newRecord.setName(toBeEntered);
		} else {
			newRecord.setName(oldRecord.getName());
		}
		continueLooping = true;
		do {
			System.out.println("Enter new author id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			newRecord.setAuthorId(new Integer(toBeEntered));
		} else {
			newRecord.setAuthorId(oldRecord.getAuthorId());
		}
		continueLooping = true;
		do {
			System.out.println("Enter new publisher id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			newRecord.setPublisherId(new Integer(toBeEntered));
		} else {
			newRecord.setPublisherId(oldRecord.getPublisherId());
		}

		System.out.println(man.UpdateBook(oldRecord, newRecord));
	}

	/**
	 * UI to update an author
	 */
	private void UpdateAuthor() {
		Author oldRecord = new Author(null, null);
		Author newRecord = new Author(null, null);
		String toBeEntered = "";
		System.out.println(importantUpdateNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id to be updated (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter the author name to be updated (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setName(toBeEntered);
		}
		continueLooping = true;
		do {
			System.out.println("Enter new author name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			newRecord.setName(toBeEntered);
		} else {
			newRecord.setName(oldRecord.getName());
		}

		man.UpdateAuthor(oldRecord, newRecord);
	}

	/**
	 * UI to update a publisher
	 */
	private void UpdatePublisher() {
		Publisher oldRecord = new Publisher(null, null, null);
		Publisher newRecord = new Publisher(null, null, null);
		String toBeEntered = "";
		System.out.println(importantUpdateNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id to be updated (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setId(new Integer(toBeEntered));
		}

		do {
			System.out.println("Enter the publisher name to be updated (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setName(toBeEntered);
		}

		do {
			System.out.println("Enter the publisher address to be updated (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			oldRecord.setAddress(toBeEntered);
		}

		do {
			System.out.println("Enter new publisher name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			newRecord.setName(toBeEntered);
		} else {
			newRecord.setName(oldRecord.getName());
		}

		do {
			System.out.println("Enter new publisher address (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			newRecord.setAddress(toBeEntered);
		} else {
			newRecord.setAddress(oldRecord.getName());
		}

		man.UpdatePublisher(oldRecord, newRecord);
	}

	/**
	 * UI for the delete portion of CRUD. This method picks which object type
	 * will be delete.
	 */
	private void Delete() {
		boolean validCommand = false;
		String instruction = "";
		while (!validCommand) {
			System.out.println("Enter table will you be working with:\n"
					+ "(B)ook\n(A)uthor\n(P)ublisher");
			instruction = scan.nextLine();
			switch (instruction.toLowerCase()) {
			case "b":
				DeleteBook();
				validCommand = true;
				break;
			case "a":
				DeleteAuthor();
				validCommand = true;
				break;
			case "p":
				DeletePublisher();
				validCommand = true;
				break;
			default:
				System.out.println("Command not recongnized\n"
						+ "Please any letter in the parentheses.");
				break;
			}
		}

	}

	/**
	 * UI to delete a book
	 */
	private void DeleteBook() {
		Book record = new Book(null, null, null, null);
		String toBeEntered = "";
		System.out.println(importantDeleteNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setId(new Integer(toBeEntered));
		}

		do {
			System.out.println("Enter the book name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setName(toBeEntered);
		}

		do {
			System.out.println("Enter an author id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setAuthorId(new Integer(toBeEntered));
		}

		do {
			System.out.println("Enter a publisher id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setPublisherId(new Integer(toBeEntered));
		}

		System.out.println(man.DeleteBook(record));
	}

	/**
	 * UI to delete an author
	 */
	private void DeleteAuthor() {
		Author record = new Author(null, null);
		String toBeEntered = "";
		System.out.println(importantDeleteNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter the author name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setName(toBeEntered);
		}

		System.out.println(man.DeleteAuthor(record));
	}

	/**
	 * UI to update a publisher
	 */
	private void DeletePublisher() {
		Publisher record = new Publisher(null, null, null);
		String toBeEntered = "";
		System.out.println(importantDeleteNote);
		boolean continueLooping = true;
		do {
			System.out.println("Enter the id (must be integer):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndIsInteger(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setId(new Integer(toBeEntered));
		}
		continueLooping = true;
		do {
			System.out.println("Enter the publisher name (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setName(toBeEntered);
		}
		continueLooping = true;
		do {
			System.out.println("Enter the publisher address (cannot contain /):");
			toBeEntered = scan.nextLine();
			continueLooping = !this.isEmptyAndContainsSlash(toBeEntered);
		} while (continueLooping);
		if (!toBeEntered.equals("")) {
			record.setAddress(toBeEntered);
		}

		System.out.println(man.DeletePublisher(record));
	}

	/**
	 * Helper method to return if a string either empty or an integer
	 * 
	 * @param str
	 * 			To be checked
	 * @return if string is empty or integer
	 */
	private boolean isEmptyAndIsInteger(String str) {
		if (str.isEmpty()) {
			return true;
		} else if (this.isInteger(str)) {
			return true;
		}
		return false;
	}

	/**
	 * Helper method to return if a string either empty or contains a slash
	 * 
	 * @param str
	 * 			To be checked
	 * @return if string is empty or slash
	 */
	private boolean isEmptyAndContainsSlash(String str) {
		if (str.isEmpty()) {
			return true;
		} else if (this.isInteger(str)) {
			return true;
		}
		return false;
	}

	/**
	 * Loops through a string and returns if it has a slash inside
	 * 
	 * @param str
	 * 			to be checked
	 * @return if string has slash
	 */
	private boolean containsSlash(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		for (int i = 0; i < length; i++) {
			char c = str.charAt(i);
			if (c == '/') {
				return false;
			}
		}
		return true;
	}

	/**
	 * Loops through string to see if all characters are numerals
	 * 
	 * @param str
	 * 			to be checked
	 * @return if string is a number
	 */
	private boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}
}
