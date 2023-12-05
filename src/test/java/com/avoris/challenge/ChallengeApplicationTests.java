package com.avoris.challenge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.avoris.challenge.dto.CountDTO;
import com.avoris.challenge.dto.SearchDTO;
import com.avoris.challenge.dto.SearchResponseDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@AutoConfigureWebTestClient
@TestMethodOrder(OrderAnnotation.class)
public class ChallengeApplicationTests {
	
	@Container
    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");
	
	@Container
	public static final KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.3.3"));
	
	@DynamicPropertySource
	  static void overrideProperties(DynamicPropertyRegistry registry) {
	    registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
	  }

	@Autowired
    private WebTestClient webTestClient;
	
	@Test
	@Order(1)
    public void testPostSearch() {
		SearchDTO searchDTO = new SearchDTO("1234aBc", "29/12/2023", "31/12/2023", new Integer[]{30 ,29, 1, 3});
        
        webTestClient.post().uri("/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(searchDTO)
        .exchange()
        .expectStatus().isOk()
        .expectBody(SearchResponseDTO.class)
        .value(response -> {
        	assertTrue(response.getSearchId().equals(978805580));        	
        });
    }
	
	@Test
	@Order(2)
    public void testPostSearchWithErrorDate() {
		SearchDTO searchDTO = new SearchDTO("1234aBc", "29/12/2024", "31/12/2023", new Integer[]{30 ,29, 1, 3});
        
        webTestClient.post().uri("/search")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(searchDTO)
        .exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class)
        .value(response -> {
        	assertTrue(response.equals("El check-in debe de ser anterior al checkout"));
        });
    }

    @Test
    @Order(3)
    public void testGetCountWithWrongId() {
        
        webTestClient.get().uri("/count?searchId=978805581")
        .exchange()
        .expectStatus().is4xxClientError()
        .expectBody(String.class)
        .value(response -> {
        	assertTrue(response.equals("searchId invalido o no encontrado"));
        });
    }
    
    @Test
    @Order(4)
    public void testGetCount() {
    	
        webTestClient.get().uri("/count?searchId=978805580")
        .exchange()
        .expectStatus().isOk()
        .expectBody(CountDTO.class)
        .value(response -> {
        	assertTrue(response.getCount()>0);
        	assertTrue(response.getSearchId().equals(978805580));
        });
    }
    
    @AfterEach
    public void waitAfterEachTest() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }

}
