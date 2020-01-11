package com.project02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project02.model.User;
import com.project02.model.WatchList;

@CrossOrigin(origins = "http://localhost:4200")
public interface WatchListRepository extends JpaRepository<WatchList, Integer>{

	public List<WatchList> findAll();	
	<W extends WatchList> W save(WatchList watchlist);
	List<WatchList> findAllByUser(User user);
	List<WatchList> findAllByMovienameAndMovieyear(String moviename, String movieyear);
	public WatchList findByWatchlistid(int watchlistid);
	public long count();
}
