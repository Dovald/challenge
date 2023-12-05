package com.avoris.challenge.domain;


import org.springframework.data.annotation.Id;

public final class Search   {
	
	@Id
	private Integer searchId;	
	private Integer count;
	private String hotelId;
	private String checkIn;
	private String checkOut;
	private Integer[] ages;

	public Search() {
	}

	public Integer getSearchId() {
		return searchId;
	}

	public void setSearchId(Integer searchId) {
		this.searchId = searchId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public Integer[] getAges() {
		return ages;
	}

	public void setAges(Integer[] ages) {
		this.ages = ages;
	}
  
}
