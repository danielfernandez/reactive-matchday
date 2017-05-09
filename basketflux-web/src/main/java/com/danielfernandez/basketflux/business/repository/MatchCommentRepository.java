package com.danielfernandez.basketflux.business.repository;


import com.danielfernandez.basketflux.business.MatchComment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MatchCommentRepository extends ReactiveCrudRepository<MatchComment, String> {


}
