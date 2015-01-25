package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.Date;
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
import org.progress.crm.logic.Constants;
import org.progress.crm.logic.Planner;
import org.progress.crm.util.Pair;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.ParamUtil;

@Singleton
public class PlannerController {

    @EJB
    AuthenticationManager authManager;

    public List getTasks(Session session, String token, String targetUUID, String timezone) throws SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.PLANNER, workerId, Constants.ACL.ACCESS_VIEW);
        List<Planner> tasks = DaoFactory.getPlannerDao().getTasks(session, targetUUID, workerId);
        return tasks;
    }

    public boolean addTask(Session session, String token, Map<String, String> map) throws CustomException, SQLException {
        if (token == null) {
            throw new CustomException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.PLANNER, workerId, Constants.ACL.ACCESS_ADD_EIDT);

        String targetObjectUUID = ParamUtil.getNotNull(map, ParamName.PLANNER_TARGET_OBJECT_UUID);
        String taskTitle = ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_TITLE);
        String taskDescription = ParamUtil.getNotEmpty(map, ParamName.PLANNER_TASK_DESCRIPTION);
        String taskColor = ParamUtil.getRGBColor(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_COLOR));
        Date taskStartDate = new Date(Long.parseLong(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_START_DATE)));
        Date taskEndDate = new Date(Long.parseLong(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_END_DATE)));
        DaoFactory.getPlannerDao().addTask(session, workerId, taskColor, targetObjectUUID, taskTitle, taskDescription, taskStartDate,
                taskEndDate);
        return true;
    }

    public boolean deleteTaskById(Session session, String token, String id) throws CustomException, BadRequestException, SQLException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new CustomException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.PLANNER, workerId, Constants.ACL.ACCESS_DELETE);
        DaoFactory.getPlannerDao().removeTaskById(session, workerId, Integer.valueOf(id));
        return true;
    }

    public Object getPlannerTaskById(Session session, String token, Map<String, String> map) throws IsNotAuthenticatedException, CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        int taskId = ParamUtil.getNotEmptyInt(map, ParamName.PLANNER_ID);
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.PLANNER, workerId, Constants.ACL.ACCESS_VIEW);
        return DaoFactory.getPlannerDao().getTaskById(session, taskId);
    }

    public boolean editTaskById(Session session, String token, Map<String, String> map) throws BadRequestException, CustomException, SQLException {
        if (map.get(ParamName.PLANNER_ID) == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new CustomException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.PLANNER, workerId, Constants.ACL.ACCESS_ADD_EIDT);
        int plannerId = ParamUtil.getNotEmptyInt(map, ParamName.PLANNER_ID);
        Planner task = DaoFactory.getPlannerDao().getTaskById(session, Integer.valueOf(plannerId));

        Date taskStartDate = new Date(Long.parseLong(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_START_DATE)));
        Date taskEndDate = new Date(Long.parseLong(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_END_DATE)));

        task.setTargetOjectUUID(ParamUtil.getNotNull(map, ParamName.PLANNER_TARGET_OBJECT_UUID));
        task.setTaskTitle(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_TITLE));
        task.setTaskDescription(ParamUtil.getNotEmpty(map, ParamName.PLANNER_TASK_DESCRIPTION));
        task.setColor(ParamUtil.getRGBColor(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_COLOR)));
        task.setTaskStartDate(taskStartDate);
        task.setTaskEndDate(taskEndDate);

        DaoFactory.getPlannerDao().editTaskById(session, task);
        return true;
    }
}
