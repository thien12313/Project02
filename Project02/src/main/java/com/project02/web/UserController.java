package com.project02.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project02.model.User;
import com.project02.service.UserService;

@RestController(value="userController")
@RequestMapping(value="/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	private static UserService userService;
	private static Set<String> userNameSet = new HashSet<>();
	
	private static void loadUsers() {
		for (User u : userService.getAllUsers()) {
			userNameSet.add(u.getUsername());
		}
	}
	@Autowired
	public void setUserService(UserService userService) {
		UserController.userService = userService;
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	@GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAllUsers(){
		return UserController.userService.getAllUsers();
	}
	
	@GetMapping(value="/username/{username}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserByName(@PathVariable String username) {
		return UserController.userService.getUserByUsername(username);
	}
	
	@GetMapping(value="/id/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getUserByUserid(@PathVariable int id) {
		return UserController.userService.getUserByUserid(id);
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public static User createUser(@RequestParam("username") String username, 
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam("aboutme") String aboutme) {
		loadUsers();
		int setSize = userNameSet.size();
		userNameSet.add(username);
		if (setSize == userNameSet.size()) {
			//return "User Name is already taken; please choose a different User Name.";
			return null;
		}
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setFullname(fullname);
		user.setAboutme(aboutme);
		
		return userService.createUser(user);
	}
}
