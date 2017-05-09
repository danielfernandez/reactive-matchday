package com.danielfernandez.basketflux.web.controller;


import com.danielfernandez.basketflux.business.repository.MatchCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MatchCommentsController {

    private final MatchCommentRepository matchCommentRepository;


    public MatchCommentsController(final MatchCommentRepository matchCommentRepository) {
        super();
        this.matchCommentRepository = matchCommentRepository;
    }


    @RequestMapping("/comments")
    public String comments() {

        return "comments";

    }

}
