package com.neogrid.ctm.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Session {

    private LocalTime beginHour;

    private LocalTime finishHour;

    private List<Talk> talks = new ArrayList();

    public LocalTime getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(LocalTime beginHour) {
        this.beginHour = beginHour;
    }

    public LocalTime getFinishHour() {
        return finishHour;
    }

    public void setFinishHour(LocalTime finishHour) {
        this.finishHour = finishHour;
    }

    public List<Talk> getTalks() {
        return talks;
    }

    public void setTalks(List<Talk> talks) {
        this.talks = talks;
    }

    public Talk getLastTalk() {
        return talks.stream()
                .max(Comparator.comparing(Talk::getBeginTime))
                .orElse(null);
    }
}
