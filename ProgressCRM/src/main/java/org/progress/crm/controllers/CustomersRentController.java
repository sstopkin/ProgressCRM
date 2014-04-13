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
public class CustomersRentController {

    @EJB
    AuthenticationManager authManager;

    public List getAllCustomersRent(Session session, String token) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCustomersRentDao().getAllCustomersRent(session);
    }

    public boolean addCustomersRent(Session session, String token, String status, String assigned,
            String description, String idCustomer) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getCustomersRentDao().addCustomersRent(session, Integer.valueOf(status),
                Integer.valueOf(assigned), description, idWorker, Integer.valueOf(idCustomer));
        return true;
    }

    public boolean editCustomersRentById(Session session, String token, String id, String status, String assigned, String description, String idCustomer) throws IsNotAuthenticatedException, SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getCustomersRentDao().editCustomersRentById(session, Integer.valueOf(id), Integer.valueOf(status),
                Integer.valueOf(assigned), description, idWorker, Integer.valueOf(idCustomer));
        return true;
    }

    public boolean deleteCustomersRent(Session session, String token, String id) throws IsNotAuthenticatedException, SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        DaoFactory.getCustomersRentDao().deleteCustomersRent(session, Integer.valueOf(id));
        return true;
    }
}
