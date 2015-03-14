package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Workers;

public class WorkersDao {

    public int addWorker(Session session, final String email, final String fName, final String mName, final String lName, final String pass)
            throws SQLException {
        Workers worker = new Workers(email, fName, mName, lName, pass);
        worker.setPermissions(1);
        return (int) session.save(worker);
    }

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

    public List<Workers> getAllWorkersOrderedById(Session session, boolean active) throws SQLException {
        Criteria cr = session.createCriteria(Workers.class);
        if (active) {
            cr.add(Restrictions.eq(DbFields.WORKERS.IS_ACTIVE, true));
        }
        cr.addOrder(Order.asc(DbFields.WORKERS.ID));
        return cr.list();
    }

    public List getWorkerObjectsById(final Session session, final int workerId) throws SQLException {
        return session.createCriteria(Apartaments.class)
                .add(Restrictions.eq(DbFields.APARTAMENTS.ASSIGNED, workerId))
                .addOrder(Order.desc(DbFields.APARTAMENTS.CREATIONDATE))
                .list();
    }
}
