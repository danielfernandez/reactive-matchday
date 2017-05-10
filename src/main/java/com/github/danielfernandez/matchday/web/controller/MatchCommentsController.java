package com.github.danielfernandez.matchday.web.controller;


import com.github.danielfernandez.matchday.business.Match;
import com.github.danielfernandez.matchday.business.repository.MatchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
public class MatchCommentsController {

    private final MatchRepository matchRepository;


    public MatchCommentsController(final MatchRepository matchRepository) {
        super();
        this.matchRepository = matchRepository;
    }


    @RequestMapping({"/","/matches"})
    public String matches(final Model model) {
        // Match stream will be resolved by WebFlux before Thymeleaf starts rendering...
        final Flux<Match> matchStream = this.matchRepository.findAll();
        model.addAttribute("matches", matchStream);
        return "matches";

    }

}
