package com.neogrid.ctm.domain;

import java.time.LocalTime;

import static com.neogrid.ctm.domain.ConferenceConfiguration.TIME_FORMATTER;

public class Talk {

    private String title;

    private LocalTime beginTime;

    private long duration;

    public Talk(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalTime beginTime) {
        this.beginTime = beginTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        LocalTime beginTime = getBeginTime();
        return beginTime.format(TIME_FORMATTER) + " " + getTitle();
    }
}
