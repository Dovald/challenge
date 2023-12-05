package com.avoris.challenge.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {
	
  public KafkaProducer(KafkaTemplate<String, SearchKafkaMessage> kafkaSearchTemplate) {
	this.kafkaSearchTemplate = kafkaSearchTemplate;
  }

  @Autowired
  private final KafkaTemplate<String, SearchKafkaMessage> kafkaSearchTemplate;

  @Value(value = "${spring.kafka.topic.hotel}")
  private String topicSearch;

  public void sendSearch(SearchKafkaMessage searchKafkaMessage) {
	  
    System.out.println(String.format("Search Message sent -> %s", searchKafkaMessage.toString()));

    Message<SearchKafkaMessage> message = MessageBuilder
            .withPayload(searchKafkaMessage)
            .setHeader(KafkaHeaders.TOPIC, topicSearch)
            .build();

    kafkaSearchTemplate.send(message);
  }
}