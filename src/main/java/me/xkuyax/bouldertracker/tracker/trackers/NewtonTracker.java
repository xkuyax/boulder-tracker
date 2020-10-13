package me.xkuyax.bouldertracker.tracker.trackers;

import org.springframework.stereotype.Component;

@Component
public class NewtonTracker extends BoulderadoTracker {

    public NewtonTracker() {
        super("eyJhbGciOiJIUzI1NiIsICJ0eXAiOiJKV1QifQ.eyJjdXN0b21lciI6Ik5ld3RvbjE5MjAxOSJ9.IE91bdH44QxkJCulRwSJn1zQJ_FkaDp293b5eIMAOtA");
    }

    @Override
    public String getName() {
        return "NEWTON";
    }
}
