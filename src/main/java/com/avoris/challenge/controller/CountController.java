package com.avoris.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avoris.challenge.dto.CountDTO;
import com.avoris.challenge.service.CountService;

@RestController
public class CountController {
	
	@Autowired
	private CountService countService;
	
	@GetMapping("/count")
    public ResponseEntity<CountDTO> getCount(@RequestParam(name = "searchId") Integer searchId) {
        return ResponseEntity.ok(countService.count(searchId));
    }

}
