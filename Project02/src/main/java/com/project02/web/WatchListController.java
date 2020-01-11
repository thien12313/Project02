package com.project02.web;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project02.model.Movie;
import com.project02.model.User;
import com.project02.model.WatchList;
import com.project02.service.UserService;
import com.project02.service.WatchListService;

@RestController(value = "watchlistController")
@RequestMapping(value = "/watchlist")
@CrossOrigin(origins = "http://localhost:4200")
public class WatchListController {

	@Autowired
	private WatchListService watchListService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RestTemplate restTemplate;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<WatchList> getAllWatchlists() {
		return watchListService.getAllWatchlists();
	}

	@GetMapping(value = "/userall/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<WatchList> getAllWatchlistsByUserId(@PathVariable int id, HttpSession session) {
		String ids = session.getId();
		System.out.println(ids);
		User user = userService.getUserByUserid(id);
		return watchListService.getAllWatchlistByUser(user);
	}
	
	@GetMapping(value = "/all/{moviename}/{movieyear}", produces = MediaType.APPLICATION_JSON_VALUE)
	//@ResponseBody
	public List<WatchList> getAllWatchlistsByMovienameAndMovieyear(@PathVariable String moviename, @PathVariable String movieyear) {
		return watchListService.getAllWatchlistByMovienameAndMovieyear(moviename, movieyear);
	}
	
	@PostMapping(value = "/new")
	public void saveWatchList(@RequestParam String moviename, @RequestParam String movieyear, @RequestParam String review, 
			@RequestParam int rating, @RequestParam String imageurl,
			@RequestParam int userid, @RequestParam String username, @RequestParam String password, @RequestParam String fullname, @RequestParam String aboutme) {
		User user = new User(userid, username, password, fullname, aboutme);
		WatchList watchlist = new WatchList(100000, moviename, movieyear, review, rating, true, imageurl, user);
		watchListService.saveMovie(watchlist);
	}
	
	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	public WatchList getRandomWatchlist(HttpSession session) {
		Random rand = new Random();
		int num = (int) watchListService.count();
		int random = rand.nextInt(num + 1);
		if(random == 0) {
			random++;
		}
		return watchListService.getOne(random);
	}
	
	@RequestMapping(value="/movies/{moviename}", method = RequestMethod.GET)
	public Movie getMovie(@PathVariable String moviename) {
		Movie movie = restTemplate.getForObject("http://www.omdbapi.com/?apikey=45a28543&t=" + moviename, Movie.class);
		return movie;
	}
}
