package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Calls;

public class CallsDao {
     public boolean addCustomerCall(final Session session, final int apartamentId, final String description) throws SQLException, CustomException {
        Calls cCall = new Calls(apartamentId, new Date(), description);
        session.save(cCall);
        return true;
    }

    public boolean updateCustomerCall(final Session session, final Calls cCall) throws SQLException, CustomException {
        session.update(cCall);
        return true;
    }

    public Calls getCustomerCallById(final Session session, final Integer cCalsId) throws SQLException, CustomException {
        Calls cCall = (Calls) session.get(Calls.class, cCalsId);
        return cCall;
    }

    public List<Calls> getAllCustomerCalls(final Session session) throws SQLException, CustomException {
        List<Calls> list = session.createCriteria(Calls.class).list();
        return list;
    }
}
