package com.avoris.challenge.kafka;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.avoris.challenge.domain.Search;
import com.avoris.challenge.repository.SearchRepository;

@Component
public class KafkaConsumer {
	
	@Autowired
	SearchRepository searchRepository;


	@KafkaListener(topics = "${spring.kafka.topic.hotel}", 
	containerFactory = "kafkaListenerContainerFactory")
	public void listeningSearch(SearchKafkaMessage searchKafkaMessage) {
		System.out.println(String.format("Search Message received -> %s", searchKafkaMessage.toString()));
		Integer hashCode = searchKafkaMessage.hashCode();
		Optional<Search> search = searchRepository.findById(hashCode);
		
		if(search.isPresent()){
			search.get().setCount(search.get().getCount()+1);
			searchRepository.save(search.get());
		}
		else {
			Search newsearch = new Search();
			newsearch.setSearchId(hashCode);
			newsearch.setCount(1);
			newsearch.setAges(searchKafkaMessage.getAges());
			newsearch.setCheckIn(searchKafkaMessage.getCheckIn());
			newsearch.setCheckOut(searchKafkaMessage.getCheckOut());
			newsearch.setHotelId(searchKafkaMessage.getHotelId());
			searchRepository.save(newsearch);			
		}		
	}
}
