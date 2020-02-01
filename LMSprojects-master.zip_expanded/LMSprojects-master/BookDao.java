package com.team.alpha.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.team.alpha.dao.ObjectDao;
import com.team.alpha.entity.Book;

@Component
public class BookDao implements ObjectDao<Book>{
	
	@Value("${DB_URL}")
	private String dbUrl;
	@Value("${DB_UN}")
	private String user;
	@Value("${DB_PASS}")
	private String pass;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch(ClassNotFoundException e) {
			System.out.println("Error with database connection.");
			e.printStackTrace();
		}
		Connection connection=null;
		try {
			connection = (Connection) DriverManager.getConnection(dbUrl, user, pass);
		} catch (SQLException e) {
			System.out.println("Error with database connection.");
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public List<Book> getAllObjects() {
		List<Book> books = new ArrayList<Book>();
		try{
			String allBooksQuery = "select * from tbl_book";
			PreparedStatement myStatement = getConnection().prepareStatement(allBooksQuery);
			ResultSet myResultSet = myStatement.executeQuery(); 
			Book temp = new Book();
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("bookId");
					String resultTwo = (String) myResultSet.getObject("title");
					int resultThree = myResultSet.getInt("authorId");
					int resultFour = myResultSet.getInt("pubId");
					temp = new Book(resultOne, resultTwo, resultThree, resultFour);
					books.add(temp);
					
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
		return books;
	}
	
	public Book getBookById(int id) {	
		Book temp = null;
		try{
			String allBooksQuery = "select * from tbl_book where bookId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(allBooksQuery);
			myStatement.setInt(1, id);
			ResultSet myResultSet = myStatement.executeQuery(); 
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("bookId");
					String resultTwo = (String) myResultSet.getObject("title");
					int resultThree = myResultSet.getInt("authorId");
					int resultFour = myResultSet.getInt("pubId");
					temp = new Book(resultOne, resultTwo, resultThree, resultFour);

				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
				e.printStackTrace();
			}
		
		return temp;
	}
		
	public List<Book> getAllAvailbleBooks() {
		List<Book> books = new ArrayList<Book>();
		try{
			String availableBooks = "SELECT B.bookId, B.title, B.authorId, B.pubId FROM tbl_book B, tbl_book_copies BC WHERE BC.branchId=B.branchId AND BC.noOfCopies > 0 AND BC.bookId=B.bookId;";
			PreparedStatement myStatement = getConnection().prepareStatement(availableBooks);
			ResultSet myResultSet = myStatement.executeQuery(); 
			Book temp = null;
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("bookId");
					String resultTwo = (String) myResultSet.getObject("title");
					int resultThree = myResultSet.getInt("authorId");
					int resultFour = myResultSet.getInt("pubId");
					temp = new Book(resultOne, resultTwo, resultThree, resultFour);
					books.add(temp);	
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
		return books;
	
	}

	@Override
	public void createObject(Book book) {
		
		try{
			String addNewBook = "insert into tbl_book(bookId, title, authorId, pubId) values(?,?,?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewBook);
			myStatement.setInt(1, book.getBookId());
			myStatement.setString(2, book.getTitle());
			myStatement.setInt(3, book.getAuthorId());
			myStatement.setInt(4, book.getPublisherId());
			myStatement.executeUpdate();
			System.out.println("Book added successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
	}

	@Override
	public void deleteObject(int id) {
		try{
			String deleteBook = "delete from tbl_book where bookId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteBook);;
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Book deleted successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
	}

	@Override
	public void updateObject(Book book) {
		
		try{
			String updateBook = "update tbl_book set title=?, authorId=?, pubId=? where bookId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateBook);
			myStatement.setString(1, book.getTitle());
			myStatement.setInt(2, book.getAuthorId());
			myStatement.setInt(3, book.getPublisherId());
			myStatement.setInt(4, book.getBookId());
			myStatement.executeUpdate();
			System.out.println("Book updated successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
	}
	
}
