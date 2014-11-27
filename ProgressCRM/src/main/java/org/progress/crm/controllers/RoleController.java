package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Workers;

@Singleton
public class RoleController {

    @EJB
    AuthenticationManager authManager;

    public int getPermissions(Session session, String token) throws SQLException, CustomException {
        if (token == null) {
            return 0;
        }
        if (!authManager.isAuthentificated(UUID.fromString(token))) {
            return 0;
        }
        int userId = getUserIdByToken(token);
        Workers pr = DaoFactory.getWorkersDao().getWorkerById(session, userId);
        if (pr == null) {
            return 0;
        }
        return pr.getPermissions();
    }

    public int getUserIdByToken(String token) throws SQLException, CustomException {
        if (token == null) {
            return 0;
        }
        UUID uuid = UUID.fromString(token);
        if (authManager.isAuthentificated(uuid)) {
            int userId = authManager.getUserIdByToken(uuid);
            return userId;
        }
        return 0;
    }
}
