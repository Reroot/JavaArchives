package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.BookLoanDao;
import com.team.alpha.entity.BookLoan;

@Service
public class BookLoanService {

	@Autowired
	BookLoanDao bookloandao;
	
	public List<BookLoan> getAllBookLoans(){
		return bookloandao.getAllObjects();
	}
	
	public void overrideDueDate(BookLoan bookloan) {
		bookloandao.updateDueDate(bookloan);
	}
	
	public Boolean doesBookLoanExist(BookLoan bookloan) {
		List<BookLoan> loans = bookloandao.getAllObjects();
		int found = 0;
		for(BookLoan loan: loans) {
			if(loan.getCardNo()==bookloan.getCardNo() & loan.getBookId()==bookloan.getBookId() 
					& loan.getBranchId()==bookloan.getBranchId()) {
				found++;
			}
		}
		if(found > 0) {
			return true;
		} else {
			return false;
		}		
	}
	
}