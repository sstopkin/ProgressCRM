package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Comments;
import org.progress.crm.logic.DbFields;

public class CommentsDao {

    public boolean addComment(final Session session, final String objectUUID, final String text, final int idWorker) throws SQLException, CustomException {
        Comments coment=new Comments(idWorker, objectUUID, text);
        session.save(coment);
        return true;
    }

    public List getCommentsByObjectUUID(final Session session, final String objectUUID) throws SQLException, CustomException {
        return session.createCriteria(Comments.class)
                .add(Restrictions.eq(DbFields.CALLS.APARTAMENTSID, objectUUID))
                .addOrder(Order.desc(DbFields.CALLS.DATE))
                .list();
    }

    public List<Comments> getAllComments(final Session session) throws SQLException, CustomException {
        return session.createCriteria(Comments.class).list();
    }
}
