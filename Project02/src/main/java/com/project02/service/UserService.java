package com.project02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project02.model.User;
import com.project02.repository.UserRepository;


@Service(value="userService")
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
	public User getUserByUsername(String username) {
		return this.userRepository.findUserByUsername(username);
	}
	public User getUserByUserid(int id) {
		return this.userRepository.findUserByUserid(id);
	}
	public User createUser(User user) {
		return this.userRepository.save(user);
	}
	public User authenticate(String username, String password) {
		System.out.println("Username:" + username);
		System.out.println("Password:" + password);
		return this.userRepository.findUserByUsernameAndPassword(username, password);
	}
}
