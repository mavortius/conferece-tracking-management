package com.neogrid.ctm.domain;

import java.time.LocalTime;

public class AfternoonSession extends Session {

    public AfternoonSession() {
        setBeginHour(LocalTime.of(13,0));
    }
}
