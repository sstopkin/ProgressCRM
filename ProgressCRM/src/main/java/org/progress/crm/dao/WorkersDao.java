package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Workers;

public class WorkersDao {
//FFU
//    public void addWorker(Session session, final String email, final String fName, final String mName, final String lName, final String pass)
//            throws SQLException {
//        Workers pr = new Workers(email, fName, mName, lName, pass);
//        pr.setPermissions(1);
//        session.save(pr);
//    }

    public void updateWorker(Session session, Workers worker) throws SQLException {
        session.update(worker);
    }

    public Workers getWorkerById(Session session, Integer workerId) throws SQLException {
        return (Workers) session.get(Workers.class, workerId);
    }

    public Workers getWorkerByEmail(Session session, String workerEmail) throws SQLException {
        return (Workers) session.byNaturalId(Workers.class)
                .using(DbFields.WORKERS.EMAIL, workerEmail)
                .load();
    }

    public List<Workers> getAllWorkers(Session session) throws SQLException {
        return session.createCriteria(Workers.class)
                .addOrder(Order.asc(DbFields.WORKERS.EMAIL))
                .list();
    }
}
