package com.github.danielfernandez.basketflux.business.repository;


import com.github.danielfernandez.basketflux.business.Match;
import com.github.danielfernandez.basketflux.business.MatchEvent;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MatchEventRepository extends ReactiveCrudRepository<MatchEvent, String> {


}
