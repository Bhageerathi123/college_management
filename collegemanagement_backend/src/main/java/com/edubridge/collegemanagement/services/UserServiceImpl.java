package com.edubridge.collegemanagement.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.edubridge.collegemanagement.entities.User;

import com.edubridge.collegemanagement.repository.UserRepository;

@Service

public class UserServiceImpl  implements UserService {

		Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

		@Autowired
		private UserRepository userRepository;

		@Override
		public User craeteUser(User User) {
			return userRepository.save(User);
		}

}
