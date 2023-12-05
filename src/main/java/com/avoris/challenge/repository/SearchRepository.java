package com.avoris.challenge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.avoris.challenge.domain.Search;

public interface SearchRepository extends MongoRepository<Search, Integer> {

}