package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.BookDao;
import com.team.alpha.entity.Book;

@Service
public class BookService {
	
	@Autowired
	BookDao bookdao;
	
	public List<Book> getAllBooks(){
		return bookdao.getAllObjects();
	}
	
	public Book getBookById(Integer id){
		Book tempBook = bookdao.getBookById(id);
		return tempBook;
	}
	
	public List<Book> getAllBooksWithCopies(){
		return bookdao.getAllAvailbleBooks();
	}
	
	public void addBook(Book book) {
		bookdao.createObject(book);
	}
	
	public void deleteBook(int id) {
		bookdao.deleteObject(id);
	}
	
	public void updateBook(Book book){
		bookdao.updateObject(book);
	}
}
