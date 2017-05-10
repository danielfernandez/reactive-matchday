package com.github.danielfernandez.basketflux.business.repository;


import com.github.danielfernandez.basketflux.business.Match;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MatchRepository extends ReactiveCrudRepository<Match, String> {


}
