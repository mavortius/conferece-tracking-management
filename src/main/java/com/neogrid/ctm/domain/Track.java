package com.neogrid.ctm.domain;

public class Track {

    private int number;

    private MorningSession morningSession;

    private AfternoonSession afternoonSession;


    public Track(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public MorningSession getMorningSession() {
        return morningSession;
    }

    public void setMorningSession(MorningSession morningSession) {
        this.morningSession = morningSession;
    }

    public AfternoonSession getAfternoonSession() {
        return afternoonSession;
    }

    public void setAfternoonSession(AfternoonSession afternoonSession) {
        this.afternoonSession = afternoonSession;
    }
}
