package com.project02.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project02.model.User;
import com.project02.service.UserService;

@RestController(value = "LoginController")
public class LoginController {

	private static UserService userService = new UserService();

	private LoginController() {
		super();
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public User login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {

		User user = userService.authenticate(username, password);
		if (user != null) {
			session.setAttribute("USER", user);
		} else {
			return null;
		}
		return user;
	}

	@GetMapping("/session")
	public User getSession(HttpSession session) {
		if (session != null) {
			// log.info((User) session.getAttribute("USER"));
			return (User) session.getAttribute("USER");
		}
		return null;
	}

	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
	}
}
