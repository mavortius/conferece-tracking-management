package com.neogrid.ctm.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class ConferenceConfiguration {

    public static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("hh:mma").toFormatter(Locale.US);

    public static final LocalTime MORNING_SESSION_BEGIN_TIME = LocalTime.of(9,0);

    public static final LocalTime LUNCH_TIME = LocalTime.of(12,0);

    public static final LocalTime AFTERNOON_SESSON_BEGIN_TIME = LocalTime.of(13,0);

    public static final LocalTime NETWORKING_EVENT_BEGIN_TIME = LocalTime.of(16,0);

    public static final LocalTime NETWORKING_EVENT_END_TIME = LocalTime.of(17,0);

    public static final long MAX_MORNING_SESSION_MINUTES = 180L;

    public static final long MAX_EVE_SESSION_MINUTES = 240L;
}
