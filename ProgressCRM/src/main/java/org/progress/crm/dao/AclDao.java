package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.logic.ACLList;

public class AclDao {

    public List<ACLList> getACLList(Session session) throws SQLException {
        List<ACLList> list = session.createCriteria(ACLList.class).list();
        return list;
    }

    public void addACLList(final Session session, final int idEntity, final int idAccessType,
            final int idWorker, final int idAccessCategory) throws SQLException {
        session.save(new ACLList(idEntity, idAccessType, idWorker, idAccessCategory));
    }

    public ACLList getACLListById(final Session session, final int aclId) throws SQLException {
        return (ACLList) session.get(ACLList.class, aclId);
    }

    public void editACLListById(final Session session, final int aclId, final int idAccessCategory,
            final int idAccessType, final int idAccessEntity, final int idWorker) throws SQLException {
        ACLList news = getACLListById(session, aclId);
        news.setIdAccessCategory(idAccessCategory);
        news.setIdAccessType(idAccessType);
        news.setIdEntity(idAccessEntity);
        news.setIdWorker(idWorker);
        session.update(news);
    }

    public void removeACLListById(Session session, int aclId) throws SQLException {
        ACLList aclRecord = DaoFactory.getAclDao().getACLListById(session, aclId);
        session.delete(aclRecord);
    }
}
