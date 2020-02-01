package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.AuthorDao;
import com.team.alpha.entity.Author;

@Service
public class AuthorService {
	
	@Autowired
	AuthorDao authordao;
	
	public List<Author> getAllAuthors() {
		return authordao.getAllObjects();
	}
	
	public Author findAuthor(int id) {
		return authordao.getAuthorById(id);
	}
	
	public void addAuthor(Author author) {
		authordao.createObject(author);
	}
	
	public void removeAuthor(int id) {
		authordao.deleteObject(id);
	}
	
	public void updateAuthor(Author author) {
		authordao.updateObject(author);
	}
}
