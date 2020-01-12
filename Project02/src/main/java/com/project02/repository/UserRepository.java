package com.project02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project02.model.User;

@Repository(value="userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public List<User> findAll();
	public User findUserByUsername(String username);
	public User findUserByUserid(int id);
	<U extends User> U save(U user);
	public User findUserByUsernameAndPassword(String username, String password);
}  