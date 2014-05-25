package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.logic.LogService;

public class LogServiceDao {

    public boolean addNewAction(final Session session, int idWorker, String description, int action) throws SQLException {
        session.save(new LogService(action, idWorker, description));
        return true;
    }

    public List<LogService> getFullLog(Session session) throws SQLException {
        List<LogService> list = session.createCriteria(LogService.class).list();
        return list;
    }
}
