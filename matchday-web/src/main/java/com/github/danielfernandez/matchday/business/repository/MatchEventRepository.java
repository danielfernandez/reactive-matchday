package com.github.danielfernandez.matchday.business.repository;


import com.github.danielfernandez.matchday.business.MatchEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MatchEventRepository extends ReactiveCrudRepository<MatchEvent, String> {


}
