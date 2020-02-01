package com.team.alpha.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.model.User;
import com.team.alpha.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userDao;
	
	public User searchByUsernamePassword(String username, String password) {
		return userDao.findByUsernamePassword(username, password);
	}
	
	public Optional<User> searchByUsername(String username) {
		return userDao.findByUsername(username);
	}
}
