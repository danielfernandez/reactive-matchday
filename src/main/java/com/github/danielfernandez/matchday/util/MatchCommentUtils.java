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


import java.util.Random;

import com.github.danielfernandez.matchday.data.Data;

public class MatchCommentUtils {

    private static final Random RANDOM = new Random(System.currentTimeMillis());



    public static String randomAuthor() {
        return Data.COMMENT_AUTHORS.get(RANDOM.nextInt(Data.COMMENT_AUTHORS.size()));
    }

    public static String randomText() {
        return Data.COMMENT_TEXTS.get(RANDOM.nextInt(Data.COMMENT_TEXTS.size()));
    }



    private MatchCommentUtils() {
        super();
    }

}
