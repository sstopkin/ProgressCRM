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
public class CallsController {

    @EJB
    AuthenticationManager authManager;

    public List getCallsByApartsId(Session session, String token, String apartamentsId) throws CustomException, SQLException {
        if (apartamentsId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCallsDao().getCustomerCallsByApartamentsId(session, Integer.valueOf(apartamentsId));
    }

    public boolean addCallsByApartsId(Session session, String token, String apartamentsId, String description) throws CustomException, SQLException {
        if (apartamentsId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        DaoFactory.getCallsDao().addCustomerCall(session, Integer.valueOf(apartamentsId), description, idWorker);
        return true;
    }
//
//    public boolean removeCustomer(Session session, String token, String id) throws CustomException {
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
//        DaoFactory.getCustomersDao().removeCustomerById(session, Integer.valueOf(id));
//        return true;
//    }
//
//    public List getCustomerByString(Session session, String str) throws CustomException {
//        return DaoFactory.getCustomersDao().findCustomerByStr(session, str);
//    }
//
//    public boolean editCustomer(Session session, String token, String id, String fName, String lName, String mName) throws CustomException {
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
//        Customers customers = getCustomerById(session, id);
//        customers.setDeleted(false);
//        customers.setfName(fName);
//        customers.setlName(lName);
//        customers.setmName(mName);
//        DaoFactory.getCustomersDao().modifyCustomer(session, customers);
//        return true;
//    }
}
