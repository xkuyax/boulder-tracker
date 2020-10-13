package me.xkuyax.bouldertracker.tracker.trackers;

import lombok.Data;
import me.xkuyax.bouldertracker.tracker.TrackData;
import me.xkuyax.bouldertracker.tracker.Tracker;
import me.xkuyax.utils.StringUtils;
import org.jsoup.Jsoup;

import java.net.URL;

@Data
public abstract class BoulderadoTracker implements Tracker {

    private final String token;

    @Override
    public TrackData getCurrent() throws Exception {
        var document = Jsoup.parse(new URL("https://www.boulderado.de/boulderadoweb/gym-clientcounter/index.php?mode=get&token=" + token), 30000);
        var visitorsText = document.select(".actcounter-content").text();
        var freeText = document.select(".freecounter-content").text();
        var visitors = StringUtils.parseInt(visitorsText, -1);
        var free = StringUtils.parseInt(freeText, -1);
        if (visitors != -1 && free != -1) {
            return new TrackData(visitors, visitors + free, visitorsText + "," + freeText);
        } else {
            return new TrackData(visitors, free, visitorsText + "," + freeText);
        }
    }
}
