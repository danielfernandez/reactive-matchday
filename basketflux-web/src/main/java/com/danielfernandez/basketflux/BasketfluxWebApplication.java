package com.danielfernandez.basketflux;

import com.danielfernandez.basketflux.business.MatchComment;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class BasketfluxWebApplication {



	@Bean
	public ApplicationRunner initialize(final ReactiveMongoTemplate reactiveMongoTemplate) {
		return args -> {

			final Mono<Void> initializeMongo =
					reactiveMongoTemplate.dropCollection(MatchComment.class)
							.then(reactiveMongoTemplate.createCollection(MatchComment.class))
							.then(reactiveMongoTemplate.insert(new MatchComment("one", "matchOne", "Whatever goal!")))
							.then(reactiveMongoTemplate.insert(new MatchComment("two", "matchTwo", "Whatever other goal!")))
							.then(reactiveMongoTemplate.insert(new MatchComment("three", "matchThree", "Whatever some goal!")))
							.then(reactiveMongoTemplate.insert(new MatchComment("four", "matchFour", "Whatever any goal!")))
							.then();

			initializeMongo.block();

		};
	}


	public static void main(String[] args) {
		SpringApplication.run(BasketfluxWebApplication.class, args);
	}

}
