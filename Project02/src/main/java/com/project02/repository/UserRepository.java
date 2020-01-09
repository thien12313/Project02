package com.project02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project02.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	public List<User> findAll();
	public User findUserByUsername(String username);
	public User findUserByUserid(int id);
	<U extends User> U save(User user);
	public User findUserByUsernameAndPassword(String username, String password);
}