package com.avoris.challenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avoris.challenge.domain.Search;
import com.avoris.challenge.dto.CountDTO;
import com.avoris.challenge.dto.SearchDTO;
import com.avoris.challenge.exception.InvalidParametersException;
import com.avoris.challenge.repository.SearchRepository;

@Service
public class CountService {
	
	@Autowired
	SearchRepository searchRepository;

	public CountDTO count(Integer searchId) {		
		Optional<Search> searchEntity = searchRepository.findById(searchId);
		CountDTO countDTO = new CountDTO();
		if(searchEntity.isPresent())
		{ 
			SearchDTO searchDTO = new SearchDTO(searchEntity.get().getHotelId(), searchEntity.get().getCheckIn(),
					searchEntity.get().getCheckOut(), searchEntity.get().getAges());
			
			countDTO.setCount(searchEntity.get().getCount());			
			countDTO.setSearchId(searchId);
			countDTO.setSearchDTO(searchDTO);
		}else {
			throw new InvalidParametersException("searchId invalido o no encontrado");
		}
		return countDTO;
	}

}
