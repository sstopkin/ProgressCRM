package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class HelpDeskController {

    @EJB
    AuthenticationManager authManager;

    public List getAllHelpDeskRequest(Session session, String token) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getHelpDeskDao().getAllHelpDeskRequests(session);
    }

    public boolean addHelpDeskRequest(Session session, String token, String request, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        DaoFactory.getHelpDeskDao().addHelpDeskRequest(session, workerId, request, description, Integer.valueOf("1"));
        return true;
    }

    public String deleteHelpDeskRequest(Session session, String token, String id) throws SQLException, CustomException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        return DaoFactory.getHelpDeskDao().deleteHelpDeskRequest(session, Integer.valueOf(id));
    }
}
