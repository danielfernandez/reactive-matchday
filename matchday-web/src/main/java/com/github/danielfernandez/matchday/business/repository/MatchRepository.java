package com.github.danielfernandez.matchday.business.repository;


import com.github.danielfernandez.matchday.business.Match;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MatchRepository extends ReactiveCrudRepository<Match, String> {


}
