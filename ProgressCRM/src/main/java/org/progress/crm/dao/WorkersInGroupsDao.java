package org.progress.crm.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.WorkersInGroups;

public class WorkersInGroupsDao {

    public void addUserToGroup(Session session, int workerId, int groupId) {
        session.save(new WorkersInGroups(workerId, groupId));
    }

    public void deleteUserFromGroup(Session session, int workerId, int groupId) {
        session.delete(getWorkersInGroupsByWorkerIdAndGroupId(session, workerId, groupId));
    }

    public WorkersInGroups getWorkersInGroupsById(Session session, final int workersInGroupsId) {
        return (WorkersInGroups) session.get(WorkersInGroups.class, workersInGroupsId);
    }

    public List<WorkersInGroups> getWorkersInGroupsByWorkerIdAndGroupId(Session session, final int workerId, final int groupId) {
        Criteria cr = session.createCriteria(WorkersInGroups.class);
        cr.add(Restrictions.eq(DbFields.WORKERS_IN_GROUPS.WORKER_ID, workerId));
        cr.add(Restrictions.eq(DbFields.WORKERS_IN_GROUPS.GROUP_ID, groupId));
        return cr.list();
    }

    public boolean isUserInGroup(Session session, final int workerId, final int groupId) {
        if (DaoFactory.getWorkersInGroupsDao().getWorkersInGroupsByWorkerIdAndGroupId(
                session, workerId, groupId).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
