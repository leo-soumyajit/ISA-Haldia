package com.soumyajit.ISA.HIT.HALDIA.Config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    // This runs every 5 minutes after server starts (initial delay = 0)
    @Scheduled(initialDelay = 0, fixedRate = 1 * 60 * 1000)
    public void performTask() {
        System.out.println("Running scheduled task at: " + java.time.LocalDateTime.now());

        // Your task logic here
    }
}
