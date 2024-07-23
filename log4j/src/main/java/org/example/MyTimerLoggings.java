package org.example;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerLoggings {
    private static final Logger logger = LogManager.getLogger(MyTimerLoggings.class);

    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.debug("Logging DEBUG every second.");
            }
        }, 0, 1000);

        long periodInfo = 60000;
        long delayInfo = 60000 - (System.currentTimeMillis() % 60000);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.info("Logging INFO at the start of each minute.");
            }
        }, delayInfo, periodInfo);

        long periodError = 3600000;
        LocalTime now = LocalTime.now(ZoneId.systemDefault());
        long delayError = (60 - now.getMinute()) * 60000 - now.getSecond() * 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.error("Logging ERROR at the start of each hour.");
            }
        }, delayError, periodError);
    }
}
