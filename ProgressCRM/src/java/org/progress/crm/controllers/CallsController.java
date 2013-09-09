package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class CallsController {

    public List getCallsByApartsId(Session session, String apartamentsId) throws CustomException, SQLException {
        if (apartamentsId == null) {
            throw new BadRequestException();
        }
        return DaoFactory.getCallsDao().getCustomerCallsByApartamentsId(session, Integer.valueOf(apartamentsId));
    }

    public boolean addCallsByApartsId(Session session, String token, String apartamentId, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        DaoFactory.getCallsDao().addCustomerCall(session, Integer.valueOf(apartamentId), description);
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
