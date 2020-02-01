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
import com.team.alpha.entity.LibraryBranch;

@Component
public class LibraryBranchDao implements ObjectDao<LibraryBranch>{
	
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
	public List<LibraryBranch> getAllObjects() {
		List<LibraryBranch> branches = new ArrayList<LibraryBranch>();
		try{
			String allBranchesQuery = "select * from tbl_library_branch;";
			PreparedStatement myStatement = getConnection().prepareStatement(allBranchesQuery);
			ResultSet myResultSet = myStatement.executeQuery(); 
			LibraryBranch temp = null;
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("branchId");
					String resultTwo = (String) myResultSet.getObject("branchName");
					String resultThree = (String) myResultSet.getObject("branchAddress");
					temp = new LibraryBranch(resultOne, resultTwo, resultThree);
					branches.add(temp);
					
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}

		return branches;
	}
	
	public LibraryBranch findBranchById(int branchId) {
		LibraryBranch branch = null;
		try{
			String findBranchQuery = "select * from tbl_library_branch where branchId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(findBranchQuery);
			myStatement.setInt(1, branchId);
			ResultSet myResultSet = myStatement.executeQuery(); 
				while(myResultSet.next()) {
					int resultOne = myResultSet.getInt("branchId");
					String resultTwo = (String) myResultSet.getObject("branchName");
					String resultThree = (String) myResultSet.getObject("branchAddress");
					branch = new LibraryBranch(resultOne, resultTwo, resultThree);
						
				}
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}

		return branch;
	}

	@Override
	public void createObject(LibraryBranch branch) {
		try{
			String addNewBranch = "insert into tbl_library_branch(branchId, branchName, branchAddress) values(?,?,?)";
			PreparedStatement myStatement = getConnection().prepareStatement(addNewBranch);
			myStatement.setInt(1, branch.getBranchId());
			myStatement.setString(2, branch.getBranchName());
			myStatement.setString(3, branch.getBranchAddress());
			myStatement.executeUpdate();
			System.out.println("Branch added successfully");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}	
	}

	@Override
	public void deleteObject(int id) {
		try{
			String deleteBranch = "delete from tbl_library_branch where branchId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(deleteBranch);
			myStatement.setInt(1, id);
			myStatement.executeUpdate();
			System.out.println("Branch successfully deleted");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}	
	}

	@Override
	public void updateObject(LibraryBranch branch) {
		try{
			String updateBranch = "update tbl_library_branch set branchName=?, branchAddress=? where branchId=?";
			PreparedStatement myStatement = getConnection().prepareStatement(updateBranch);
			myStatement.setString(1, branch.getBranchName());
			myStatement.setString(2, branch.getBranchAddress());
			myStatement.setInt(3, branch.getBranchId());
			myStatement.executeUpdate();
			System.out.println("Branch successfully updated.");	
			} catch (SQLException e) {
				System.out.println("Error connecting to database.");
			}
	}

}
