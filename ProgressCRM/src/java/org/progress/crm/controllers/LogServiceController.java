package org.progress.crm.controllers;

import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;

/**
 *
 * @author best
 */
@Singleton
public class LogServiceController {

    public boolean addEvent(Session session, int idWorker, String description, int action) {
        DaoFactory.getLogServiceDao().addNewAction(session, idWorker, description, action);
        return true;
    }
}
