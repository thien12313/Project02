package com.project02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "watchlist")
public class WatchList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "watchlistid")
	private int watchlistid;
	@Column(name = "moviename")
	private String moviename;
	@Column(name = "movieyear")
	private String movieyear;
	@Column(name = "review")
	private String review;
	@Column(name = "rating")
	private int rating;
	@Column(name = "isrecommended")
	private boolean isrecommended;
	@Column(name = "imageurl")
	private String imageurl;

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	public WatchList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WatchList(int watchlistid, String moviename, String movieyear, String review, int rating,
			boolean isrecommended, String imageurl, User user) {
		super();
		this.watchlistid = watchlistid;
		this.moviename = moviename;
		this.movieyear = movieyear;
		this.review = review;
		this.rating = rating;
		this.isrecommended = isrecommended;
		this.imageurl = imageurl;
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((imageurl == null) ? 0 : imageurl.hashCode());
		result = prime * result + (isrecommended ? 1231 : 1237);
		result = prime * result + ((moviename == null) ? 0 : moviename.hashCode());
		result = prime * result + ((movieyear == null) ? 0 : movieyear.hashCode());
		result = prime * result + rating;
		result = prime * result + ((review == null) ? 0 : review.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + watchlistid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WatchList other = (WatchList) obj;
		if (imageurl == null) {
			if (other.imageurl != null)
				return false;
		} else if (!imageurl.equals(other.imageurl))
			return false;
		if (isrecommended != other.isrecommended)
			return false;
		if (moviename == null) {
			if (other.moviename != null)
				return false;
		} else if (!moviename.equals(other.moviename))
			return false;
		if (movieyear == null) {
			if (other.movieyear != null)
				return false;
		} else if (!movieyear.equals(other.movieyear))
			return false;
		if (rating != other.rating)
			return false;
		if (review == null) {
			if (other.review != null)
				return false;
		} else if (!review.equals(other.review))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (watchlistid != other.watchlistid)
			return false;
		return true;
	}

	public int getWatchlistid() {
		return watchlistid;
	}

	public void setWatchlistid(int watchlistid) {
		this.watchlistid = watchlistid;
	}

	public String getMoviename() {
		return moviename;
	}

	public void setMoviename(String moviename) {
		this.moviename = moviename;
	}

	public String getMovieyear() {
		return movieyear;
	}

	public void setMovieyear(String movieyear) {
		this.movieyear = movieyear;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isIsrecommended() {
		return isrecommended;
	}

	public void setIsrecommended(boolean isrecommended) {
		this.isrecommended = isrecommended;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "WatchList [watchlistid=" + watchlistid + ", moviename=" + moviename + ", movieyear=" + movieyear
				+ ", review=" + review + ", rating=" + rating + ", isrecommended=" + isrecommended + ", imageurl="
				+ imageurl + ", user=" + user + "]";
	}

}