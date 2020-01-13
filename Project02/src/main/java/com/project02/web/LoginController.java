package com.project02.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project02.model.User;
import com.project02.service.UserService;

@RestController(value = "LoginController")
@CrossOrigin()
public class LoginController {

	@Autowired
	private UserService userService;

	private LoginController() {
		super();
	}

	@PostMapping(value ="/login", produces=MediaType.APPLICATION_JSON_VALUE)
	public User login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		User user = userService.authenticate(username, password);
		session.setAttribute("USER", user);
		System.out.println(session.getAttribute("USER"));
		return user;
	}
	
	@GetMapping(value="/allusers", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@GetMapping(value="/session", produces=MediaType.APPLICATION_JSON_VALUE)
	public User getSession(HttpSession session) {
		if (session != null) {
			return (User) session.getAttribute("USER");
		}
		return null;
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
