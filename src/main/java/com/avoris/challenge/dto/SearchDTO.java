package com.avoris.challenge.dto;

import java.util.Arrays;
import java.util.Objects;


public record SearchDTO(String hotelId, String checkIn, String checkOut, Integer[] ages)   {		

  public SearchDTO(String hotelId, String checkIn, String checkOut, Integer[] ages) {
    this.hotelId = hotelId;
    this.checkIn = checkIn;
    this.checkOut = checkOut;
    this.ages = ages;
  }

  @Override
  public int hashCode() {
    return Objects.hash(hotelId, checkIn, checkOut, Arrays.hashCode(ages));
  }
}