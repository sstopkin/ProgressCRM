package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Comments;
import org.progress.crm.logic.DbFields;

public class CommentsDao {

    public boolean addComment(final Session session, final String objectUUID, final String text, final int idWorker) throws SQLException {
        Comments coment = new Comments(idWorker, objectUUID, text);
        session.save(coment);
        return true;
    }

    public List getCommentsByObjectUUID(final Session session, final String objectUUID) throws SQLException {
        return session.createCriteria(Comments.class)
                .add(Restrictions.eq(DbFields.COMMENTS.OBJECTUUID, objectUUID))
//                .addOrder(Order.desc(DbFields.COMMENTS.CREATIONDATE))
                .list();
    }

    public List<Comments> getAllComments(final Session session) throws SQLException {
        return session.createCriteria(Comments.class).list();
    }
}
