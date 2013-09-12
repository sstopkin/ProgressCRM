package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;

@Singleton
public class HelpDeskController {

    public List getAllHelpDeskRequest(Session session) throws CustomException, SQLException {
        return DaoFactory.getHelpDeskDao().getAllHelpDeskRequests(session);
    }

    public boolean addHelpDeskRequest(Session session, String token, String request, String description) throws CustomException, SQLException {
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
        DaoFactory.getHelpDeskDao().addHelpDeskRequest(session, Integer.valueOf("666"), request, description, Integer.valueOf("1"));
        return true;
    }
}
