package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class SettingsController {

    @EJB
    AuthenticationManager authenticationManager;

    public List getParameters(Session session, String token) throws IsNotAuthenticatedException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getSettingsDao().getSettingsByWorkerId(session, 1);
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
