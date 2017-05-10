package com.github.danielfernandez.matchday;

import com.github.danielfernandez.matchday.agents.MatchEventAgent;
import com.github.danielfernandez.matchday.business.MatchEvent;
import com.github.danielfernandez.matchday.data.Data;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class MatchDayWebApplication {


	@Bean
	public ApplicationRunner initialize(final ReactiveMongoTemplate mongoTemplate) {
		return args -> {

			/*
			 * INSERT ALL THE NEEDED TEST DATA (will block)
			 */
			Data.initializeAllData(mongoTemplate);

			/*
			 * INITIALIZATION OF THE MATCH EVENT STREAM (insertions between 1 and 5 secs)
			 */
			final MatchEventAgent matchEventAgent = new MatchEventAgent(mongoTemplate);
			final Flux<MatchEvent> matchEventStream = matchEventAgent.createAgentStream();
			// Subscribe and just let it run (forever)
			matchEventStream.subscribe();


		};
	}




	public static void main(String[] args) {
		SpringApplication.run(MatchDayWebApplication.class, args);
	}

}
