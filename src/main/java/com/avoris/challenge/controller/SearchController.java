package com.avoris.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.avoris.challenge.dto.SearchDTO;
import com.avoris.challenge.dto.SearchResponseDTO;
import com.avoris.challenge.service.SearchService;

@RestController
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@PostMapping("/search")
    public ResponseEntity<SearchResponseDTO> postSearch(@RequestBody SearchDTO searchDTO) {
        return ResponseEntity.ok(searchService.search(searchDTO));
    }

}
