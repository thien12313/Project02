package com.project02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project02.model.User;
import com.project02.model.WatchList;
import com.project02.repository.WatchListRepository;

@Service(value="watchListService")
public class WatchListService {

	@Autowired
	WatchListRepository watchListRepository;
	
	public List<WatchList> getAllWatchlists() {
		return this.watchListRepository.findAll();
	}
	public List<WatchList> getAllWatchlistByUser(User user) {
		return this.watchListRepository.findAllByUser(user);
	}
	public List<WatchList> getAllWatchlistByMovienameAndMovieyear(String moviename, String movieyear) {
		return this.watchListRepository.findAllByMovienameAndMovieyear(moviename, movieyear);
	}
	public void saveMovie(WatchList watchlist) {
		this.watchListRepository.save(watchlist);
	}
}