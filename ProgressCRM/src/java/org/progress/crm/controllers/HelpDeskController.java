package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class HelpDeskController {

    @EJB
    AuthenticationManager authManager;

    public List getAllHelpDeskRequest(Session session) throws CustomException, SQLException {
        return DaoFactory.getHelpDeskDao().getAllHelpDeskRequests(session);
    }

    public boolean addHelpDeskRequest(Session session, String token, String request, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getHelpDeskDao().addHelpDeskRequest(session, idWorker, request, description, Integer.valueOf("1"));
        return true;
    }
}
