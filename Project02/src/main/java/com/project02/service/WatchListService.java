package com.project02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project02.model.User;
import com.project02.model.WatchList;
import com.project02.repository.WatchListRepository;

@Service(value="watchListService")
@CrossOrigin(origins = "http://localhost:4200")
public class WatchListService {

	@Autowired
	WatchListRepository watchListRepository;
	
	public List<WatchList> getAllWatchlists() {
		return this.watchListRepository.findAllByOrderByMovienameAsc();
	}
	public List<WatchList> getAllWatchlistByUser(User user) {
		return this.watchListRepository.findAllByUserOrderByMovienameAsc(user);
	}
	public List<WatchList> getAllWatchlistByMovienameAndMovieyear(String moviename, String movieyear) {
		return this.watchListRepository.findAllByMovienameAndMovieyear(moviename, movieyear);
	}
	public void saveMovie(WatchList watchlist) {
		this.watchListRepository.save(watchlist);
	}
	public long count() {
		return this.watchListRepository.count();
	}
	public WatchList getOne(int watchlistid) {
		return this.watchListRepository.findByWatchlistid(watchlistid);
	}
	public WatchList validate(User user, String moviename) {
		return this.watchListRepository.findByUserAndMoviename(user, moviename);
	}
}