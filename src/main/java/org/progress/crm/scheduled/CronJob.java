package org.progress.crm.scheduled;

import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.progress.crm.controllers.CustomersController;

@Singleton
public class CronJob {

    @EJB
    CustomersController customersController;

    public CronJob() {
    }

    private static final Logger log = Logger.getLogger(CronJob.class.getName());

//    @Schedule(minute = "*/1", hour = "*")
//    public void runEveryMinute() {
//        log.log(Level.INFO,
//                "running every minute .. now it's: " + new Date().toString());
//    }
//
//    @Schedule(minute = "*/5", hour = "*")
//    public void runEvery10Second() {
//        String filename = "/tmp/progresscrm.log";
//        try {
//            try (FileWriter sw = new FileWriter(filename, true)) {
//                sw.write("log " + new Date().toString() + "\n");
//            }
//        } catch (IOException e) {
//            log.log(Level.INFO, e.getMessage());
//        }
//    }
//    @Schedule(second = "*/5", minute = "*", hour = "*")
//    @Schedule(dayOfMonth = "*")
//    @Schedule(minute = "*/5", hour = "*")
//    public void runEveryDay() throws CustomException, SQLException {
//        Session session = null;
//
//        session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//        List<Customers> list = customersController.getCustomersListByBirthday(session, new Date());
//        for (Customers customers : list) {
//            log.log(Level.INFO,
//                    ".. now it's: " + new Date().toString() + " " + customers.getCustomersFname());
//        }
//        String filename = "/tmp/progresscrm.log";
//        try {
//            try (FileWriter sw = new FileWriter(filename, true)) {
//                sw.write("log " + new Date().toString() + "\n");
//            }
//        } catch (IOException e) {
//            log.log(Level.INFO, e.getMessage());
//        }
//
//    }
}
