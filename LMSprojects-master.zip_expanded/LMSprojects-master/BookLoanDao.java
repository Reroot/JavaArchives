package com.team.alpha.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.team.alpha.dao.ObjectDao;
import com.team.alpha.entity.BookLoan;

@Component
public class BookLoanDao implements ObjectDao<BookLoan> {
	
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
	public List<BookLoan> getAllObjects() {
		List<BookLoan> bookLoans = new ArrayList<BookLoan>();
		try{
			String allBookLoansQuery = "select * from tbl_book_loans";
			PreparedStatement myStatement = getConnection().prepareStatement(allBookLoansQuery);
			ResultSet myResultSet = myStatement.executeQuery(); 
			BookLoan temp = new BookLoan();
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("bookId");
					int resultTwo = myResultSet.getInt("branchId");
					int resultThree = myResultSet.getInt("cardNo");
					Date resultFour = myResultSet.getDate("dateOut");
					Date resultFive = myResultSet.getDate("dueDate");
					temp = new BookLoan(resultOne, resultTwo, resultThree, resultFour, resultFive);
					bookLoans.add(temp);	
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
	
		return bookLoans;
		
	}

	@Override
	public void createObject(BookLoan bookLoan) {
		try{
			String addNewLoan = "insert into tbl_book_loans(bookId, branchId, cardNo, dateOut, dueDate) values(?,?,?,?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewLoan);
			myStatement.setInt(1, bookLoan.getBookId());
			myStatement.setInt(2, bookLoan.getBranchId());
			myStatement.setInt(3, bookLoan.getCardNo());
			myStatement.setDate(4, bookLoan.getDateOut());
			myStatement.setDate(5, bookLoan.getDueDate());
			myStatement.executeUpdate();
			System.out.println("Book loan was added successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}	
		
	}

	@Override
	public void deleteObject(int id) {
		try{
			String deleteLoan = "delete from tbl_book_loans where cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteLoan);
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Book loan successfully deleted");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}		
	}
	
	public void deleteObjectWith(int bookid, int branchid, int cardnumber) {
		try{
			String deleteLoan = "delete from tbl_book_loans where bookId=? and branchId=? and cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteLoan);
			myStatement.setInt(1, bookid);
			myStatement.setInt(2, branchid);
			myStatement.setInt(3, cardnumber);
			myStatement.executeUpdate();
			System.out.println("Book loan successfully deleted");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
	}

	@Override
	public void updateObject(BookLoan bookLoan) {
		try{
			String updateLoan = "update tbl_book_loans set bookId=?, branchId=?, dateOut=?, dueDate=? where cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateLoan);
			myStatement.setInt(1, bookLoan.getBookId());
			myStatement.setInt(2, bookLoan.getBranchId());
			myStatement.setDate(3, (Date) bookLoan.getDateOut());
			myStatement.setDate(4, (Date) bookLoan.getDueDate());
			myStatement.setInt(5, bookLoan.getCardNo());
			myStatement.executeUpdate();
			System.out.println("Book loan successfully updated.");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
	}
	
	public void updateDueDate(BookLoan bookloan) {
		try{
			String updateDate = "update tbl_book_loans set dueDate=? where bookId=? and branchId=? and cardNo=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateDate);
			myStatement.setDate(1, bookloan.getDueDate());
			myStatement.setInt(2, bookloan.getBookId());
			myStatement.setInt(3, bookloan.getBranchId());
			myStatement.setInt(4, bookloan.getCardNo());
			myStatement.executeUpdate();
			System.out.println("Due date successfully updated.");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
		
	}

}
