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
package com.github.danielfernandez.matchday.business.repository;


import com.github.danielfernandez.matchday.business.entities.MatchComment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;

public interface MatchCommentRepository extends ReactiveMongoRepository<MatchComment,String> {

    /*
     * Note that we are extending from ReactiveMongoRepository, which will
     * already add a large amount of methods to our repository for free!
     */


    // This is a normal, non-tailable stream that will give us the initial values to be rendered for
    // the comments section of a match
    public Flux<MatchComment> findByMatchIdAndTimestampLessThanEqualOrderByTimestampDesc(final String matchId, final String timestamp);


    // OrderBy cannot be used in @Tailable, but that's fine because they will be returned in insertion order
    // and for our SSE stream that's exactly what we want
    @Tailable
    public Flux<MatchComment> findByMatchIdAndTimestampGreaterThan(final String matchId, final String timestamp);

}
