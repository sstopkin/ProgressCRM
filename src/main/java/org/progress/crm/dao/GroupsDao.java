package org.progress.crm.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Groups;

public class GroupsDao {

    public List<Groups> getGroups(Session session) {
        Criteria cr = session.createCriteria(Groups.class);
        cr.add(Restrictions.eq(DbFields.GROUPS.DELETED, false));
        cr.addOrder(Order.desc(DbFields.GROUPS.ID));
        return cr.list();
    }

    public void addGroup(Session session, String groupName) {
        session.save(new Groups(groupName));
    }

    public void deleteGroup(Session session, int groupId) {
        Groups g = DaoFactory.getGroupsDao().getGroupById(session, groupId);
        g.setDeleted(true);
        session.save(g);
    }

    public Groups getGroupById(Session session, int groupId) {
        return (Groups) session.get(Groups.class, groupId);
    }

}
