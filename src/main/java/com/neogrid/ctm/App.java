package com.neogrid.ctm;

import com.neogrid.ctm.domain.Conference;
import com.neogrid.ctm.domain.ConferenceFactory;

/**
 * Application entry point
 */
public class App {
    public static void main(String[] args) {
        String fileName = "input.txt";

        Conference conference = ConferenceFactory.create(fileName);

        conference.print();
    }
}
