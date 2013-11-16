package org.progress.crm.scheduled;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.controllers.CustomersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Customers;
import org.progress.crm.util.HibernateUtil;

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
//    @Schedule(second = "*/5", minute = "*", hour = "*")
    @Schedule(dayOfMonth = "*")
    public void runEveryDay() throws CustomException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            List<Customers> list = customersController.getCustomersListByBirthday(session, new Date());
            for (Customers customers : list) {
                log.log(Level.INFO,
                        ".. now it's: " + new Date().toString() + " " + customers.getCustomersFname());
            }
            session.getTransaction().commit();
//        } catch (HibernateException | SQLException | NoSuchAlgorithmException | IOException | InterruptedException | ExecutionException e) {
//            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
