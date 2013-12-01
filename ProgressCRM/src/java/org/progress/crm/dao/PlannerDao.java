package org.progress.crm.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Planner;

public class PlannerDao {

    public List<Planner> getTasks(Session session) {
        List<Planner> list = session.createCriteria(Planner.class).
                add(Restrictions.eq(DbFields.PLANNER.DELETED, false)).
                addOrder(Order.desc(DbFields.PLANNER.CREATIONDATE)).list();
        return list;
    }

    public void addTask(final Session session, final int idWorker, final int taskType,
            final int taskId, final String taskDescription, final Date taskDate) {
        session.save(new Planner(idWorker, taskType, taskId, taskDescription, taskDate));
    }

    public Planner getTaskById(final Session session, final int taskId) {
        return (Planner) session.get(Planner.class, taskId);
    }

    public void editTaskById(final Session session, final int plannerId, final int idWorker, final int taskType,
            final int taskId, final String taskDescription, final Date taskDate) {
        Planner task = getTaskById(session, plannerId);
        task.setIdWorker(idWorker);
        task.setTaskType(taskType);
        task.setTaskId(taskId);
        task.setTaskDescription(taskDescription);
        task.setTaskDate(taskDate);
        session.update(task);
    }

    public void removeTaskById(Session session, int idWorker, int plannerId) {
        Planner task = getTaskById(session, plannerId);
        task.setDeleted(true);
        session.update(task);
    }
}
