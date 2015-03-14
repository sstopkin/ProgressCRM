package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Settings;

public class SettingsDao {

    public List<Settings> getValueByParameterName(Session session, String parameter, int idWorker) throws SQLException {
        Criteria criteria = session.createCriteria(Settings.class);
        criteria.add(Restrictions.eq(DbFields.SETTINGS.PARAMETERS, parameter));
        if (idWorker != -1) {
            criteria.add(Restrictions.eq(DbFields.PLANNER.IDWORKER, idWorker));
        }
        return criteria.list();
    }

    public List<Settings> getSettingsByWorkerId(Session session, int idWorker) throws SQLException {
        Criteria criteria = session.createCriteria(Settings.class);
        criteria.add(Restrictions.eq(DbFields.PLANNER.IDWORKER, idWorker));
        return criteria.list();
    }

    public void addTask(final Session session, final int idWorker, final String parameter,
            final String value) throws SQLException {
        session.save(new Settings(idWorker, value, parameter));
    }

    public void editParameter(final Session session, final Settings task) throws SQLException {
        session.update(task);
    }

}
