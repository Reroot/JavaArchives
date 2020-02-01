package com.team.alpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.entity.BookLoan;
import com.team.alpha.service.BookLoanService;

@RestController
@RequestMapping(path="/administrator")
public class AdminLoanController {
	
	@Autowired
	private BookLoanService bookloanservice;
	
	@GetMapping(value="/bookloan/", produces="application/json")
	public ResponseEntity<List<BookLoan>> getAllLoans() {
		List<BookLoan> loans = bookloanservice.getAllBookLoans();
		if (loans.isEmpty()) {
            return new ResponseEntity<List<BookLoan>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<BookLoan>>(loans, HttpStatus.OK);		
	}
	
		
	@PutMapping(value="/bookloan/", consumes="application/json")
	public ResponseEntity<BookLoan> updateLoan(@RequestBody BookLoan bookloan) {
		
        if (!bookloanservice.doesBookLoanExist(bookloan)) {
            return new ResponseEntity<BookLoan>(HttpStatus.NOT_FOUND);
        }
        	bookloanservice.overrideDueDate(bookloan);
	    return new ResponseEntity<BookLoan>(bookloan, HttpStatus.OK);
	}
}
