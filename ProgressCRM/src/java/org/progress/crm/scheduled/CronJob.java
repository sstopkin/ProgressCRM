package org.progress.crm.scheduled;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author best
 */
@Singleton
public class CronJob {

    public CronJob() {
    }

    private final Logger log = Logger.getLogger(CronJob.class.getName());

    @Schedule(minute = "*/1", hour = "*")
    public void runEveryMinute() {
        log.log(Level.INFO,
                "running every minute .. now it's: " + new Date().toString());
    }
    
    @Schedule(second = "*/5",minute = "*", hour = "*")
    public void runEvery10Second() {
        log.log(Level.INFO,
                "running every second .. now it's: " + new Date().toString());
    }
}
