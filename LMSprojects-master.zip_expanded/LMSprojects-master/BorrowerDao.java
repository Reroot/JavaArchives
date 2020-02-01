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

import com.team.alpha.entity.Borrower;
 
@Component
public class BorrowerDao implements ObjectDao<Borrower>{
	
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
	public List<Borrower> getAllObjects() {
		List<Borrower> borrowers = new ArrayList<Borrower>();
		try{			
			String allBorrowersQuery = "select * from tbl_borrower;";
			PreparedStatement myStatement = getConnection().prepareStatement(allBorrowersQuery);
			ResultSet myResultSet = myStatement.executeQuery(); 
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("cardNo");
					String resultTwo = (String) myResultSet.getObject("name");
					String resultThree = (String) myResultSet.getObject("address");
					String resultFour = (String) myResultSet.getObject("phone");
					Borrower temp = new Borrower(resultOne, resultTwo, resultThree, resultFour);
					borrowers.add(temp);
					
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
		return borrowers;
	}
	
	public Borrower getBorrowerById(int cardNo) {	
		Borrower temp = null;
		try{
			String findBorrowerQuery = "select * from tbl_borrower where cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(findBorrowerQuery);
			myStatement.setInt(1, cardNo);
			ResultSet myResultSet = myStatement.executeQuery(); 
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("cardNo");
					String resultTwo = (String) myResultSet.getObject("name");
					String resultThree = (String) myResultSet.getObject("address");
					String resultFour = (String) myResultSet.getObject("phone");
					temp = new Borrower(resultOne, resultTwo, resultThree, resultFour);

				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
				e.printStackTrace();
			}
		
		return temp;
	}

	@Override
	public void createObject(Borrower borrower) {
		try{
			String addNewBorrower = "insert into tbl_borrower(cardNo, name, address, phone) values(?,?,?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewBorrower);
			myStatement.setInt(1, borrower.getCardNo());
			myStatement.setString(2, borrower.getBorrowerName());
			myStatement.setString(3, borrower.getBorrowerAddress());
			myStatement.setString(4, borrower.getBorrowerPhone());
			myStatement.executeUpdate();
			System.out.println("Borrower added successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
	}

	@Override
	public void deleteObject(int id) {
		try{
			String deleteBorrower = "delete from tbl_borrower where cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteBorrower);
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Borrower successfully deleted");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
	}

	@Override
	public void updateObject(Borrower borrower) {
		try{
			String updateBorrower = "update tbl_borrower set name=?, address=?, phone=? where cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateBorrower);
			myStatement.setString(1, borrower.getBorrowerName());
			myStatement.setString(2, borrower.getBorrowerAddress());
			myStatement.setString(3, borrower.getBorrowerPhone());
			myStatement.setInt(4, borrower.getCardNo());
			myStatement.executeUpdate();
			System.out.println("Borrower successfully updated");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}	
	}

}
