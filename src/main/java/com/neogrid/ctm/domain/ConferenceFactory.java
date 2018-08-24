package com.neogrid.ctm.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.neogrid.ctm.domain.ConferenceConfiguration.*;

public class ConferenceFactory {

    private static final String durationTerm = "min";
    private static final String lightning = "lightning";

    public static Conference create(String fileName) {
        List<Talk> talkList = new ArrayList<>();
        Conference conference = new Conference();

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            talkList = stream.map(line -> {
                Talk talk = new Talk(line);

                if (line.contains(lightning)) {
                    talk.setDuration(5L);
                } else {
                    int durationTermIndex = line.lastIndexOf(durationTerm);
                    String duration = line.substring(durationTermIndex - 2, durationTermIndex);

                    talk.setDuration(Long.valueOf(duration));
                }

                return talk;
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        talkList = talkList.stream()
                .sorted(Comparator.comparing(Talk::getDuration).reversed())
                .collect(Collectors.toList());

        long totalConferenceMin = talkList.stream().map(Talk::getDuration).reduce(0L, Long::sum);
        int totalDays = getTotalDays(totalConferenceMin);
        int trackCounter = 0;

        do {
            trackCounter++;
            Track track = new Track(trackCounter);
            conference.getTracks().add(track);
            track.setMorningSession(createMorningSession(talkList));

            /*if (talkList.stream().anyMatch(talk -> talk.getBeginTime() == null)) {
                track.setAfternoonSession(createAfternoonSession(talkList));
            }*/
        } while (trackCounter < totalDays);

        trackCounter = 0;

        do {
            trackCounter++;
            int finalTrackCounter = trackCounter;
            Track track = conference.getTracks().stream()
                    .filter(t -> t.getNumber() == finalTrackCounter)
                    .findFirst().get();
            track.setAfternoonSession(createAfternoonSession(talkList));
        } while (trackCounter < totalDays);

        return conference;
    }

    private static int getTotalDays(long totalConferenceMin) {
        long totalMinByDay = MAX_MORNING_SESSION_MINUTES + MAX_EVE_SESSION_MINUTES;
        int numOfDays = Math.toIntExact(totalConferenceMin / totalMinByDay);

        if ((totalConferenceMin % 2) == 0) {
            return numOfDays;
        } else {
            return numOfDays + 1;
        }
    }

    private static MorningSession createMorningSession(List<Talk> talks) {
        LocalTime sessionTime = MORNING_SESSION_BEGIN_TIME;
        MorningSession session = new MorningSession();
        long sessionTimeCounter = 0;

        for (Talk talk : talks) {
            if (talk.getBeginTime() == null) {
                if (sessionTimeCounter == 0) {
                    talk.setBeginTime(sessionTime);
                    sessionTime = sessionTime.plusMinutes(talk.getDuration());
                    session.getTalks().add(talk);
                    sessionTimeCounter = sessionTimeCounter + talk.getDuration();
                    continue;
                }

                if (sessionTimeCounter < MAX_MORNING_SESSION_MINUTES) {
                    if (sessionTimeCounter + talk.getDuration() <= MAX_MORNING_SESSION_MINUTES) {
                        talk.setBeginTime(sessionTime);
                        sessionTime = sessionTime.plusMinutes(talk.getDuration());
                        session.getTalks().add(talk);
                        sessionTimeCounter = sessionTimeCounter + talk.getDuration();
                    }
                }
            }
        }

        return session;
    }

    private static AfternoonSession createAfternoonSession(List<Talk> talks) {
        LocalTime sessionTime = AFTERNOON_SESSON_BEGIN_TIME;
        AfternoonSession session = new AfternoonSession();
        long sessionTimeCounter = 0;

        for (Talk talk : talks) {
            if (talk.getBeginTime() == null) {
                if (sessionTimeCounter == 0) {
                    talk.setBeginTime(sessionTime);
                    sessionTime = sessionTime.plusMinutes(talk.getDuration());
                    session.getTalks().add(talk);
                    sessionTimeCounter = sessionTimeCounter + talk.getDuration();
                    continue;
                }

                if (sessionTimeCounter < MAX_EVE_SESSION_MINUTES) {
                    if(sessionTimeCounter + talk.getDuration() < MAX_EVE_SESSION_MINUTES) {
                        talk.setBeginTime(sessionTime);
                        sessionTime = sessionTime.plusMinutes(talk.getDuration());
                        session.getTalks().add(talk);
                        sessionTimeCounter = sessionTimeCounter + talk.getDuration();
                    }
                }
            }
        }

        return session;
    }
}
