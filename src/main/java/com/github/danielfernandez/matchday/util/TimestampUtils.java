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


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimestampUtils {

    private static final DateFormat ISO8601_DATEFORMAT_UTC;


    static {

        // In order to persist the timestamps for the match event, we will use an ISO8601 formatter
        // that will set the timestamps in UTC and format them accordingly
        final DateFormat dateFormat =
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        ISO8601_DATEFORMAT_UTC = dateFormat;

    }


    public static String computeISO8601Timestamp() {
        return ISO8601_DATEFORMAT_UTC.format(Calendar.getInstance().getTime());
    }



    private TimestampUtils() {
        super();
    }

}
