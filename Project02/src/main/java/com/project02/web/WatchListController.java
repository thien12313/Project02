package com.project02.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project02.model.User;
import com.project02.model.WatchList;
import com.project02.service.UserService;
import com.project02.service.WatchListService;

@RestController(value = "watchlistController")
@RequestMapping(value = "/watchlist")
public class WatchListController {

	private static WatchListService watchListService;
	private static UserService userService;

	@Autowired
	public void setWatchListService(WatchListService watchListService) {
		WatchListController.watchListService = watchListService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		WatchListController.userService = userService;
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<WatchList> getAllWatchlists() {
		return WatchListController.watchListService.getAllWatchlists();
	}

	@GetMapping(value = "/userall/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WatchList> getAllWatchlistsByUserId(@PathVariable int id) {
		User user = WatchListController.userService.getUserByUserid(id);
		return WatchListController.watchListService.getAllWatchlistByUser(user);
	}
	
	@GetMapping(value = "/all/{moviename}/{movieyear}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<WatchList> getAllWatchlistsByMovienameAndMovieyear(@PathVariable String moviename, @PathVariable String movieyear) {
		return WatchListController.watchListService.getAllWatchlistByMovienameAndMovieyear(moviename, movieyear);
	}
	
	@PostMapping(value = "/new")
	public void saveWatchList(@RequestParam String moviename, @RequestParam String movieyear, @RequestParam String review, @RequestParam int rating, @RequestParam boolean isrecommended, @RequestParam String imageurl, HttpSession session) {
		User user = (User) session.getAttribute("USER");
		WatchList watchlist = new WatchList(1, moviename, movieyear, review, rating, isrecommended, imageurl, user);
		WatchListController.watchListService.saveMovie(watchlist);
	}
}