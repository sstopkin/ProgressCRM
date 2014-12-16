package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.scheduled.CronJob;
import org.progress.crm.util.HibernateUtil;
import java.util.logging.Logger;
import org.progress.crm.logic.Settings;

@Singleton
public class SettingsController {

    @EJB
    AuthenticationManager authenticationManager;

    private static final Logger log = Logger.getLogger(SettingsController.class.getName());
    public static Map<String, String> parameters = null;

    static {
        Session session = null;
        parameters = new HashMap<>();
        try {
            session = HibernateUtil.getSettingsSessionFactory().openSession();
            try {
                List<Settings> list = DaoFactory.getSettingsDao().getSettingsByWorkerId(session, 1);
                for (Settings item : list) {
                    parameters.put(item.getParameter(), item.getValue());
                }
            } catch (SQLException ex) {
                Logger.getLogger(CronJob.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List getParametersByWorkerId(Session session, String token) throws IsNotAuthenticatedException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        return DaoFactory.getSettingsDao().getSettingsByWorkerId(session, idWorker);
    }

}
