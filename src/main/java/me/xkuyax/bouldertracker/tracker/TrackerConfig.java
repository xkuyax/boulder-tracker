package me.xkuyax.bouldertracker.tracker;

import lombok.Data;

@Data
public class TrackerConfig {

    private int hourOpen = 7;
    private int hourClosed = 22;
    private int interval = 180;

}
