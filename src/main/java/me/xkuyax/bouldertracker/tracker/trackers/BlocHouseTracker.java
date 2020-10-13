package me.xkuyax.bouldertracker.tracker.trackers;

import lombok.Data;
import me.xkuyax.bouldertracker.tracker.TrackData;
import me.xkuyax.bouldertracker.tracker.Tracker;
import me.xkuyax.utils.config.Config;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

import java.net.URL;

@Data
@Component
public class BlocHouseTracker implements Tracker {

    private final Config config;

    @Override
    public String getName() {
        return "BLOC_HOUSE";
    }

    @Override
    public TrackData getCurrent() throws Exception {
        var document = Jsoup.parse(new URL("https://www.bloc-house.at/freieplaetze.php"), 30000);
        var input = document.text().replaceAll("\\D", "");
        try {
            var free = Integer.parseInt(input);
            var max = config.getInt("Trackers.blocHouse.maximum", 130);
            return new TrackData(max - free, max, input);
        } catch (Exception e) {
            return new TrackData(-1, -1, input);
        }
    }
}
