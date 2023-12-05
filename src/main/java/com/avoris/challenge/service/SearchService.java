package com.avoris.challenge.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avoris.challenge.dto.SearchDTO;
import com.avoris.challenge.dto.SearchResponseDTO;
import com.avoris.challenge.exception.InvalidParametersException;
import com.avoris.challenge.kafka.KafkaProducer;
import com.avoris.challenge.kafka.SearchKafkaMessage;

@Service
public class SearchService {
	
	@Autowired
	KafkaProducer kafkaProducer;

	public SearchResponseDTO search(SearchDTO searchDTO){
		
		if(searchDTO.ages() == null || searchDTO.ages().length == 0){
			throw new InvalidParametersException("Debes rellenar el campo de las edades");			
		}
		
		if(searchDTO.hotelId() == null || searchDTO.hotelId().length() < 7){
			throw new InvalidParametersException("Id de hotel invalido");			
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date checkIn;
		Date checkOut;
		try {
			checkIn = dateFormat.parse(searchDTO.checkIn());
			checkOut = dateFormat.parse(searchDTO.checkOut());
		
		
		if(checkIn.after(checkOut)){
			throw new InvalidParametersException("El check-in debe de ser anterior al checkout");			
		}
		} catch (ParseException e) {
			throw new InvalidParametersException("Formato con las fechas invalido, deben de ser dd/MM/yyyy");
		}
		
		for (Integer age : searchDTO.ages()) {
			if(age < 0 || age > 110) {
				throw new InvalidParametersException("Las edades no son correctas");
			}			
		}		

		SearchKafkaMessage searchKafkaMessage = new SearchKafkaMessage(searchDTO.hotelId(), searchDTO.checkIn(),
				searchDTO.checkOut(), searchDTO.ages());
		
		
		kafkaProducer.sendSearch(searchKafkaMessage);
		SearchResponseDTO searchResponseDTO = new SearchResponseDTO();
		searchResponseDTO.setSearchId(searchDTO.hashCode());
		return searchResponseDTO;
	}

}
