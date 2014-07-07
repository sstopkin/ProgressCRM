package org.progress.crm.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Calls;
import org.progress.crm.logic.DbFields;

public class CallsDao {

    public boolean addCustomerCall(final Session session, final String objectUUID,
            String incomingPhoneNumber, final String description, final int idWorker) throws SQLException {
        Calls cCall = new Calls(objectUUID, new Date(), incomingPhoneNumber, description, idWorker);
        session.save(cCall);
        return true;
    }

    public List getCustomerCallsByObjectUUID(final Session session, final String objectUUID) throws SQLException {
        return session.createCriteria(Calls.class)
                .add(Restrictions.eq(DbFields.CALLS.OBJECTUUID, objectUUID))
                .addOrder(Order.desc(DbFields.CALLS.DATE))
                .list();
    }

    public List<Calls> getAllCustomerCalls(final Session session) throws SQLException {
        List<Calls> list = session.createCriteria(Calls.class).list();
        return list;
    }

    public void getCallsAddStatsList(Session session) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        for (int i = 30; i > 0; i--) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String formatted = format1.format(calendar.getTime());
            System.out.println(formatted);
        }

        StringBuilder sql = new StringBuilder();
        sql.append("select count(*) FROM progresscrm.Calls where DATE_FORMAT(date, '%Y-%m-%d') = CURDATE()");
        sql.append(";");
        SQLQuery query = session.createSQLQuery(sql.toString());
        query.addEntity(Calls.class);
        List lApartaments = query.list();
    }
}
