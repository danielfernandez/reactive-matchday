/*
 * =============================================================================
 *
 *   Copyright (c) 2017, Daniel Fernandez (http://github.com/danielfernandez)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package com.github.danielfernandez.matchday.web.controller;


import com.github.danielfernandez.matchday.business.dataviews.MatchStatus;
import com.github.danielfernandez.matchday.business.repository.MatchStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;

@Controller
public class MatchesController {


    private final MatchStatusRepository matchStatusRepository;



    public MatchesController(final MatchStatusRepository matchStatusRepository) {
        super();
        this.matchStatusRepository = matchStatusRepository;
    }



    @RequestMapping({"/","/matches"})
    public String matches(final Model model) {
        // Match stream will be resolved by WebFlux before Thymeleaf starts rendering...
        final Flux<MatchStatus> matchStatusStream = this.matchStatusRepository.findAllMatchStatus();
        model.addAttribute("matchStatuses", matchStatusStream);
        return "matches";

    }

}
