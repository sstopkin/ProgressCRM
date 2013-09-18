package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Calls;
import org.progress.crm.logic.DbFields;

public class CallsDao {

    public boolean addCustomerCall(final Session session, final int apartamentId, final String description, final int idWorker) throws SQLException, CustomException {
        Calls cCall = new Calls(apartamentId, new Date(), description, idWorker);
        session.save(cCall);
        return true;
    }

    public List getCustomerCallsByApartamentsId(final Session session, final Integer apartamentsId) throws SQLException, CustomException {
        return session.createCriteria(Calls.class)
                .add(Restrictions.eq(DbFields.CALLS.ID, apartamentsId))
                .addOrder(Order.desc(DbFields.CALLS.DATE))
                .list();
    }

    public List<Calls> getAllCustomerCalls(final Session session) throws SQLException, CustomException {
        List<Calls> list = session.createCriteria(Calls.class).list();
        return list;
    }
}
