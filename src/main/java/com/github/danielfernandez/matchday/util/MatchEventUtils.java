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
package com.github.danielfernandez.matchday.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.github.danielfernandez.matchday.business.entities.MatchEvent;

public class MatchEventUtils {

    private static final List<MatchEvent.Type> WEIGHED_TYPES;
    private static final Random RANDOM;


    static {

        // We will create a weighed list containing 100 Type instances, repeated as many times
        // as the probability we want to give each Type, so that randomly selected event types
        // make the best possible sense.
        final List<MatchEvent.Type> weighedTypes = new ArrayList<>();
        for (int i = 0; i < 60; i++) { // 70% Opportunity
            weighedTypes.add(MatchEvent.Type.OPPORTUNITY);
        }
        for (int i = 0; i < 20; i++) { // 10% Goal
            weighedTypes.add(MatchEvent.Type.GOAL);
        }
        for (int i = 0; i < 18; i++) { // 18% Yellow Card
            weighedTypes.add(MatchEvent.Type.YELLOW_CARD);
        }
        for (int i = 0; i < 2; i++) { // 2% Red Card
            weighedTypes.add(MatchEvent.Type.RED_CARD);
        }
        WEIGHED_TYPES = Collections.unmodifiableList(weighedTypes);
        RANDOM = new Random(System.currentTimeMillis());

    }


    public static MatchEvent.Type randomEventType() {
        return WEIGHED_TYPES.get(RANDOM.nextInt(WEIGHED_TYPES.size()));
    }



    private MatchEventUtils() {
        super();
    }

}
