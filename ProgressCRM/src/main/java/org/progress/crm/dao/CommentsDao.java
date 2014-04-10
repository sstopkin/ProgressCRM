package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Calls;
import org.progress.crm.logic.DbFields;

public class CommentsDao {

    public boolean addComment(final Session session, final int objectId, final int objectType, final String text, final int idWorker) throws SQLException, CustomException {
//        C cCall = new Calls(apartamentId, new Date(), incomingPhoneNumber, description, idWorker);
//        session.save(cCall);
        return true;
    }

    public List getCommentsByObjectId(final Session session, final Integer objectId, final Integer objectType) throws SQLException, CustomException {
        return session.createCriteria(Calls.class)
                .add(Restrictions.eq(DbFields.CALLS.APARTAMENTSID, objectId))
                .addOrder(Order.desc(DbFields.CALLS.DATE))
                .list();
    }

    public List<Calls> getAllComments(final Session session) throws SQLException, CustomException {
        return session.createCriteria(Calls.class).list();
    }
}
