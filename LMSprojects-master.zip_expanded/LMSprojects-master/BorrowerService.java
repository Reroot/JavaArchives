package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.BorrowerDao;
import com.team.alpha.entity.Borrower;

@Service
public class BorrowerService {
	
	@Autowired
	BorrowerDao borrowerdao;
	
	public List<Borrower> getAllBorrowers() {
		return borrowerdao.getAllObjects();
	}
	
	public Borrower getBorrowerById(int cardNo){
		Borrower tempBorrower = borrowerdao.getBorrowerById(cardNo);
		return tempBorrower;
	}
	
	public void addBorrower(Borrower borrower) {
		borrowerdao.createObject(borrower);
	}
	
	public void deleteBorrower(int id) {
		borrowerdao.deleteObject(id);
	}
	
	public void updateBorrower(Borrower borrower) {
		borrowerdao.updateObject(borrower);
	}
}
