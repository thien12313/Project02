package com.project02.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project02.model.User;
import com.project02.model.WatchList;

@Repository(value="watchListRepository")
public interface WatchListRepository extends JpaRepository<WatchList, Integer>{

	public List<WatchList> findAllByOrderByMovienameAsc();	
	<W extends WatchList> W save(W watchlist);
	List<WatchList> findAllByUserOrderByMovienameAsc(User user);
	List<WatchList> findAllByMovienameAndMovieyear(String moviename, String movieyear);
	public WatchList findByWatchlistid(int watchlistid);
	public long count();
	public WatchList findByUserAndMoviename(User user, String moviename);
	public WatchList findAllByMoviename(String moviename);
}
