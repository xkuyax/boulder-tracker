package me.xkuyax.bouldertracker.tracker.trackers;

import org.springframework.stereotype.Component;

@Component
public class BoulderClubTracker extends BoulderadoTracker {

    public BoulderClubTracker( ) {
        super("eyJhbGciOiJIUzI1NiIsICJ0eXAiOiJKV1QifQ.eyJjdXN0b21lciI6IkdyYXpCb3VsZGVyQ2x1YiJ9.XLsaRDLe3akJy8Ed7kXpTMJPLPsVtnVhe_LgwMcIxHU");
    }

    @Override
    public String getName() {
        return "BOULDERCLUB";
    }
}