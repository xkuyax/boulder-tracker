package me.xkuyax.bouldertracker.tracker;

import java.net.MalformedURLException;

public interface Tracker {

    String getName();

    TrackData getCurrent() throws Exception;

}
