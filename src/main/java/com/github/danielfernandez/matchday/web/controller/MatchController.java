package com.github.danielfernandez.matchday.web.controller;


import com.github.danielfernandez.matchday.business.Match;
import com.github.danielfernandez.matchday.business.MatchSummary;
import com.github.danielfernandez.matchday.business.repository.MatchRepository;
import com.github.danielfernandez.matchday.business.repository.MatchSummaryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

@Controller
public class MatchController {

    private final MatchRepository matchRepository;
    private final MatchSummaryRepository matchSummaryRepository;



    public MatchController(
            final MatchRepository matchRepository,
            final MatchSummaryRepository matchSummaryRepository) {
        super();
        this.matchRepository = matchRepository;
        this.matchSummaryRepository = matchSummaryRepository;
    }



    @RequestMapping({"/","/matches"})
    public String matches(final Model model) {
        // Match stream will be resolved by WebFlux before Thymeleaf starts rendering...
        final Flux<Match> matchStream = this.matchRepository.findAll();
        model.addAttribute("matches", matchStream);
        return "matches";

    }


    @RequestMapping("/matchsummary/{matchId}")
    @ResponseBody
    public Flux<MatchSummary> matchSummary(@PathVariable String matchId) {
        return this.matchSummaryRepository.tailMatchSummary(matchId);
    }

}
