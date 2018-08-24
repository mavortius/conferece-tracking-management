package com.neogrid.ctm.domain;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.neogrid.ctm.domain.ConferenceConfiguration.LUNCH_TIME;
import static com.neogrid.ctm.domain.ConferenceConfiguration.TIME_FORMATTER;

public class Conference {

    private List<Track> tracks = new ArrayList();

    public Conference() {
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void print() {
        tracks = tracks.stream()
                .sorted(Comparator.comparing(Track::getNumber))
                .collect(Collectors.toList());

        System.out.println();
        tracks.forEach(track -> {
            System.out.println("Track " + track.getNumber());
            track.getMorningSession().getTalks().forEach(this::printTalk);
            System.out.println(LUNCH_TIME.format(TIME_FORMATTER) + " Lunch");
            track.getAfternoonSession().getTalks().forEach(this::printTalk);
            Talk lastTalk = track.getAfternoonSession().getLastTalk();
            long lastTrackDuration = lastTalk.getDuration();
            LocalTime networkingEvent = lastTalk.getBeginTime().plusMinutes(lastTrackDuration);
            System.out.println(networkingEvent.format(TIME_FORMATTER) + " Networking Event");
        });
        System.out.println();
    }

    private void printTalk(Talk talk) {
        System.out.println(talk);
    }
}
