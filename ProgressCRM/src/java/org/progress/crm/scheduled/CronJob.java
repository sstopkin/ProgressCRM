package org.progress.crm.scheduled;

import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.CustomersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

/**
 *
 * @author best
 */
@Singleton
public class CronJob {

    @EJB
    CustomersController customersController;

    public CronJob() {
    }

    private final Logger log = Logger.getLogger(CronJob.class.getName());

//    @Schedule(minute = "*/1", hour = "*")
//    public void runEveryMinute() {
//        log.log(Level.INFO,
//                "running every minute .. now it's: " + new Date().toString());
//    }
//
//    @Schedule(second = "*/5", minute = "*", hour = "*")
//    public void runEvery10Second() {
//        log.log(Level.INFO,
//                "running every second .. now it's: " + new Date().toString());
//    }
    @Schedule(dayOfMonth = "*")
    public String runEveryDay() throws CustomException {
        return TransactionService.runInScope(new Command<String>() {
            @Override
            public String execute(Session session) throws CustomException, SQLException {
                log.log(Level.INFO, "########################: " + new Date().toString());
                customersController.getCustomerByString(session, null)
                return "";
            }
        });
    }
}
