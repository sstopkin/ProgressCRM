package org.progress.crm.scheduled;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.progress.crm.controllers.CustomersController;

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

    private static final Logger log = Logger.getLogger(CronJob.class.getName());

//    @Schedule(minute = "*/1", hour = "*")
//    public void runEveryMinute() {
//        log.log(Level.INFO,
//                "running every minute .. now it's: " + new Date().toString());
//    }
//
    @Schedule(minute = "*/5", hour = "*")
    public void runEvery10Second() {
        String filename = "/tmp/progresscrm.log";
        try {
            try (FileWriter sw = new FileWriter(filename, true)) {
                sw.write("log " + new Date().toString() + "\n");
            }
        } catch (IOException e) {
            log.log(Level.INFO, e.getMessage());
        }
    }
//    @Schedule(second = "*/5", minute = "*", hour = "*")
//    @Schedule(dayOfMonth = "*")
//    public void runEveryDay() throws CustomException {
//        Session session = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            session.beginTransaction();
//            List<Customers> list = customersController.getCustomersListByBirthday(session, new Date());
//            for (Customers customers : list) {
//                log.log(Level.INFO,
//                        ".. now it's: " + new Date().toString() + " " + customers.getCustomersFname());
//            }
//            session.getTransaction().commit();
////        } catch (HibernateException | SQLException | NoSuchAlgorithmException | IOException | InterruptedException | ExecutionException e) {
////            session.getTransaction().rollback();
//        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
//        }
//    }
}
