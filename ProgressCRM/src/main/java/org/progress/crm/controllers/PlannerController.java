package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Planner;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.ParamUtil;

@Singleton
public class PlannerController {

    @EJB
    AuthenticationManager authenticationManager;

    public List getTasks(Session session, String token, String targetUUID, String timezone) throws SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        //FIXME
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        List<Planner> tasks = DaoFactory.getPlannerDao().getTasks(session, targetUUID, idWorker);
        return tasks;
    }

    public boolean addTask(Session session, String token, String taskClass,
            String targetObjectUUID, String taskTitle, String taskDescription, String taskStartDate, String taskEndDate) throws CustomException, SQLException {
        if (token == null) {
            throw new CustomException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        Long startDate = Long.parseLong(taskStartDate);
        Long endDate = Long.parseLong(taskEndDate);
        DaoFactory.getPlannerDao().addTask(session, idWorker, taskClass, targetObjectUUID, taskTitle, taskDescription, new java.util.Date(startDate),
                new java.util.Date(endDate));
        return true;
    }

    public boolean deleteTaskById(Session session, String token, String id) throws CustomException, BadRequestException, SQLException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new CustomException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        DaoFactory.getPlannerDao().removeTaskById(session, idWorker, Integer.valueOf(id));
        return true;
    }

    public Object getPlannerTaskById(Session session, String token, Map<String, String> map) throws IsNotAuthenticatedException, CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int taskId = ParamUtil.getNotEmptyInt(map, ParamName.PLANNER_ID);
        return DaoFactory.getPlannerDao().getTaskById(session, taskId);
    }

    public boolean editTaskById(Session session, String token, String id, String taskClass, String targetobjectuuid, String taskTitle, String taskDescription, String taskStartDate, String taskEndDate) throws BadRequestException, CustomException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new CustomException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        //FIXME
//        DaoFactory.getPlannerDao().editTaskById(session, Integer.valueOf(plannerId),
//                idWorker, Integer.valueOf(taskType), Integer.valueOf(taskId), taskDescription, taskDate);
        return true;
    }
}
