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
import com.team.alpha.entity.Publisher;

@Component
public class PublisherDao implements ObjectDao<Publisher>{

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
	public List<Publisher> getAllObjects() {
		List<Publisher> publishers = new ArrayList<Publisher>();
		try{
			String allPublishersQuery = "select * from tbl_publisher;";
			PreparedStatement myStatement = getConnection().prepareStatement(allPublishersQuery);;
			ResultSet myResultSet = myStatement.executeQuery(); 
			Publisher temp = null;
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("publisherId");
					String resultTwo = myResultSet.getString("publisherName");
					String resultThree = myResultSet.getString("publisherAddress");
					String resultFour = myResultSet.getString("publisherPhone");
					temp = new Publisher(resultOne, resultTwo, resultThree, resultFour);
					publishers.add(temp);
					
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}

		return publishers;
	}
	
	public Publisher getPublisherById(int id) {
		Publisher publisher = null;
		try{
			String allPublishersQuery = "select * from tbl_publisher where publisherId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(allPublishersQuery);
			myStatement.setInt(1, id);
			ResultSet myResultSet = myStatement.executeQuery(); 
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("publisherId");
					String resultTwo = myResultSet.getString("publisherName");
					String resultThree = myResultSet.getString("publisherAddress");
					String resultFour = myResultSet.getString("publisherPhone");
					publisher = new Publisher(resultOne, resultTwo, resultThree, resultFour);					
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}

		return publisher;
	}

	@Override
	public void createObject(Publisher publisher) {
		try{
			String addNewPublisher = "insert into tbl_publisher(publisherId, publisherName, publisherAddress, publisherPhone) values(?,?,?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewPublisher);
			myStatement.setInt(1, publisher.getPublisherId());
			myStatement.setString(2, publisher.getPublisherName());
			myStatement.setString(3, publisher.getPublisherAddress());
			myStatement.setString(4, publisher.getPublisherPhone());
			myStatement.executeUpdate();
			System.out.println("Publisher successfully added");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}	
		
	}

	@Override
	public void deleteObject(int id) {
		try{
			String deletePublisher = "delete from tbl_publisher where publisherId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deletePublisher);
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Publisher successfully deleted");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}			
	}

	@Override
	public void updateObject(Publisher publisher) {
		try{
			String updatePublisher = "update tbl_publisher set publisherName=?, publisherAddress=?, publisherPhone=? where publisherId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updatePublisher);
			myStatement.setString(1, publisher.getPublisherName());
			myStatement.setString(2, publisher.getPublisherAddress());
			myStatement.setString(3, publisher.getPublisherPhone());
			myStatement.setInt(4, publisher.getPublisherId());
			myStatement.executeUpdate();
			System.out.println("Publisher successfully updated");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}		
		
	}

	
}
