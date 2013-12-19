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
import org.progress.crm.logic.CustomersRent;

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

    public List<CustomersRent> getCustomersRentListByQuery(Session session, String token, String assigned,
            String idWorker, String startDate, String endDate, String status)
            throws IsNotAuthenticatedException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        String startDate_ = null;
        String endDate_ = null;
        int idWorker_;
        int assigned_;
        int status_;
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        if (status.equals("")) {
            status_ = -1;
        } else {
            status_ = Integer.valueOf(status);
        }
        if (idWorker.equals("")) {
            idWorker_ = -1;
        } else {
            idWorker_ = Integer.valueOf(idWorker);
        }
        if (!startDate.equals("")) {
            startDate_ = startDate;
        }
        if (assigned.equals("")) {
            assigned_ = -1;
        } else {
            assigned_ = Integer.valueOf(assigned);
        }
        if (!endDate.equals("")) {
            endDate_ = endDate;
        }
        return DaoFactory.getCustomersRentDao().getCustomersRentListByQuery(session,
                assigned_, idWorker_, startDate_, endDate_, status_);
    }
}
