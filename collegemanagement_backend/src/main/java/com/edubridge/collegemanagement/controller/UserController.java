package com.edubridge.collegemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edubridge.collegemanagement.entities.User;
import com.edubridge.collegemanagement.repository.UserRepository;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	@PostMapping("/user")
	public ResponseEntity<User>registerUser(@RequestBody User user) {
		return ResponseEntity.ok(repo.save(user));
	}
	
	@PostMapping("/login")
	public ResponseEntity<Boolean> login(@RequestBody User user) {
		List<User> findByEmail = repo.findByEmail(user.getEmail());
		if (!findByEmail.isEmpty() && findByEmail.get(0) != null) {
			User user2 = findByEmail.get(0);
			if (user2!= null && user2.getEmail().equals(user.getEmail()) && user2.getPassword().equals(user.getPassword())) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(Boolean.FALSE);
	}

}
