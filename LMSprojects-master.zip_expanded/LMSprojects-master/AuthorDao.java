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
import com.team.alpha.entity.Author;

@Component
public class AuthorDao implements ObjectDao<Author> {
	
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
	public List<Author> getAllObjects() {	
		List<Author> authors = new ArrayList<Author>();	
		try{
			String allAuthorsQuery = "select * from tbl_author";
			PreparedStatement myStatement = getConnection().prepareStatement(allAuthorsQuery);
			ResultSet myResultSet = myStatement.executeQuery(); 
			Author temp = new Author();
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("authorId");
					String resultTwo = (String) myResultSet.getObject("authorName");
					temp = new Author(resultOne, resultTwo);
					authors.add(temp);
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
				e.printStackTrace();
			}
		
		return authors;
	}
	
	public Author getAuthorById(int id) {	
		Author temp = null;
		try{
			String allAuthorsQuery = "select * from tbl_author where authorId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(allAuthorsQuery);
			myStatement.setInt(1, id);
			ResultSet myResultSet = myStatement.executeQuery(); 
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("authorId");
					String resultTwo = (String) myResultSet.getObject("authorName");
					temp = new Author(resultOne, resultTwo);
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
				e.printStackTrace();
			}
		
		return temp;
	}

	@Override
	public void createObject(Author author) {
		
		try{
			String addNewAuthor = "insert into tbl_author(authorId, authorName) values(?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewAuthor);
			myStatement.setInt(1, author.getAuthorId());
			myStatement.setString(2, author.getName());
			myStatement.executeUpdate();
			System.out.println("Author added successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
			
	}

	@Override
	public void deleteObject(int id) {
		
		try{
			String deleteAuthor = "delete from tbl_author where authorId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteAuthor);
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Author deleted successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
	}

	@Override
	public void updateObject(Author author) {
		
		try{
			String updateAuthor = "update tbl_author set authorName=? where authorId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateAuthor);
			myStatement.setString(1, author.getName());
			myStatement.setInt(2, author.getAuthorId());
			myStatement.executeUpdate();
			System.out.println("Author updated successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}	
	}
	
}
