/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progress.crm.dao;

import java.util.List;
import org.hibernate.Session;
import org.progress.crm.logic.LogService;

/**
 *
 * @author best
 */
public class LogServiceDao {

    public boolean addNewAction(final Session session, int idWorker, String description, int action) {
        session.save(new LogService(action, idWorker, description));
        session.getTransaction().commit();
        return true;
    }

    public List<LogService> getFullLog(Session session) {
        List<LogService> list = session.createCriteria(LogService.class).list();
        return list;
    }
}
