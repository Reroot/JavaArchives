package com.team.alpha.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team.alpha.model.User;
import com.team.alpha.service.UserService;

@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value="/search")
	public ResponseEntity<?> findUser(@RequestParam(value="username") String username) {
		Optional<User> user = userService.searchByUsername(username);
		if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);	
	}
}
