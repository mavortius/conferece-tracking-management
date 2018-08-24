package com.neogrid.ctm.domain;

import java.time.LocalTime;

public class MorningSession extends Session {

    public MorningSession() {
        setBeginHour(LocalTime.of(9, 0));
        setFinishHour(LocalTime.of(12,0));
    }
}
