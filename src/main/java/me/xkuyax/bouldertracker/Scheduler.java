package me.xkuyax.bouldertracker;

import lombok.RequiredArgsConstructor;
import me.xkuyax.bouldertracker.tracker.Tracker;
import me.xkuyax.bouldertracker.tracker.TrackerConfig;
import me.xkuyax.utils.config.Config;
import me.xkuyax.utils.mysql.MysqlConnection;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class Scheduler implements ApplicationListener<ContextRefreshedEvent> {

    private final MysqlConnection mysqlConnection;
    private final Config config;
    private Map<String, Tracker> trackerMap;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        trackerMap = contextRefreshedEvent.getApplicationContext().getBeansOfType(Tracker.class);
        new Thread(this::init).start();
    }

    public void init() {
        trackerMap.values().forEach((tracker) -> {
            var trackerConfig = config.getGenericType("Tracker." + tracker.getName(), TrackerConfig.class);
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        var date = LocalDateTime.now();
                        if (date.getHour() >= trackerConfig.getHourOpen() && date.getHour() <= trackerConfig.getHourClosed()) {
                            var current = tracker.getCurrent();
                            System.out.println(tracker.getName() + " current = " + current);
                            mysqlConnection.prepare("insert into tracker_history (Tracker,Current,Max,Raw) values (?,?,?,?)", preparedStatement -> {
                                preparedStatement.setString(1, tracker.getName());
                                preparedStatement.setInt(2, current.getCurrent());
                                preparedStatement.setInt(3, current.getMax());
                                preparedStatement.setString(4, current.getRaw());
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Error tracking " + tracker.getName());
                    } finally {
                        sleep(trackerConfig.getInterval() + (int) (Math.random() * 10 + 1));
                    }
                }
            });
            thread.start();
            System.out.println("Started Tracker for: "+tracker.getName());
            sleep((int) (Math.random() * 10 + 1));
        });
    }

    private void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}