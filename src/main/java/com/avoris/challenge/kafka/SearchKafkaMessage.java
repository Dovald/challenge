package com.avoris.challenge.kafka;

import java.util.Arrays;
import java.util.Objects;

public class SearchKafkaMessage {
	
	private String hotelId;
	private String checkIn;
	private String checkOut;
	private Integer[] ages;
	
	public SearchKafkaMessage(String hotelId, String checkIn, String checkOut, Integer[] ages) {
		super();
		this.hotelId = hotelId;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.ages = ages;
	}
	
	public SearchKafkaMessage() {}
	
	@Override
	public int hashCode() {
	  return Objects.hash(hotelId, checkIn, checkOut, Arrays.hashCode(ages) );
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
	
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SearchKafkaMessage {\n");
    
    sb.append("    hotelId: ").append(toIndentedString(hotelId)).append("\n");
    sb.append("    checkIn: ").append(toIndentedString(checkIn)).append("\n");
    sb.append("    checkOut: ").append(toIndentedString(checkOut)).append("\n");
    sb.append("    ages: ").append(toIndentedString(Arrays.toString(ages))).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
