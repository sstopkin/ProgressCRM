package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.ACLEntry;
import org.progress.crm.logic.AccessCategories;
import org.progress.crm.logic.AccessTypes;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Entities;

public class AclDao {

    public List<ACLEntry> getACLList(Session session) throws SQLException {
        Criteria cr = session.createCriteria(ACLEntry.class);
        cr.addOrder(Order.asc(DbFields.ACLENTRY.ID));
        return cr.list();
    }

    public void addACLList(final Session session, final int idEntity, final int idAccessType,
            final int idWorker, final int idAccessCategory) throws SQLException {
        session.save(new ACLEntry(idEntity, idAccessType, idWorker, idAccessCategory));
    }

    public List<ACLEntry> getACL(final Session session, final int idEntity, final int idWorker, final int idAccessType) throws SQLException {
//        DetachedCriteria minVal = DetachedCriteria.forClass(ACLEntry.class)
//                .setProjection(Projections.min(DbFields.ACLENTRY.ACCESS_CATEGORY_ID));
        Criteria cr = session.createCriteria(ACLEntry.class);
        cr.add(Restrictions.eq(DbFields.ACLENTRY.WORKER_ID, idWorker));
        cr.add(Restrictions.eq(DbFields.ACLENTRY.ENTITY_ID, idEntity));
        cr.add(Restrictions.eq(DbFields.ACLENTRY.ACCESS_TYPE_ID, idAccessType));
//        cr.add(Property.forName(DbFields.ACLENTRY.ACCESS_CATEGORY_ID).eq(minVal));
        return cr.list();
    }

    public ACLEntry getACLListById(final Session session, final int aclId) throws SQLException {
        return (ACLEntry) session.get(ACLEntry.class, aclId);
    }

    public void editACLListById(final Session session, final int aclId, final int idAccessCategory,
            final int idAccessType, final int idAccessEntity, final int idWorker) throws SQLException {
        ACLEntry news = getACLListById(session, aclId);
        news.setIdAccessCategory(idAccessCategory);
        news.setIdAccessType(idAccessType);
        news.setIdEntity(idAccessEntity);
        news.setIdWorker(idWorker);
        session.update(news);
    }

    public void removeACLListById(Session session, int aclId) throws SQLException {
        ACLEntry aclRecord = DaoFactory.getAclDao().getACLListById(session, aclId);
        session.delete(aclRecord);
    }

    public List<Entities> getEntitiesList(Session session) {
        Criteria cr = session.createCriteria(Entities.class);
        cr.addOrder(Order.asc(DbFields.ENTITIES.ID));
        return cr.list();
    }

    public List<AccessCategories> getAccessCategoriesList(Session session) {
        Criteria cr = session.createCriteria(AccessCategories.class);
        cr.addOrder(Order.asc(DbFields.ACCESS_CATEGORIES.ID));
        return cr.list();
    }

    public List<AccessTypes> getAccessTypesList(Session session) {
        Criteria cr = session.createCriteria(AccessTypes.class);
        cr.addOrder(Order.asc(DbFields.ACCESS_TYPES.ID));
        return cr.list();
    }

    public List<Entities> getACLListByEntityId(Session session, int idEntity) {
        Criteria cr = session.createCriteria(ACLEntry.class);
        cr.add(Restrictions.eq(DbFields.ACLENTRY.ENTITY_ID, idEntity));
        cr.addOrder(Order.asc(DbFields.ACLENTRY.ID));
        return cr.list();
    }
}
