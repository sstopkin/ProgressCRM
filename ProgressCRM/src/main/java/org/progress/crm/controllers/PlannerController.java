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

    public boolean addTask(Session session, String token, Map<String, String> map) throws CustomException, SQLException {
        if (token == null) {
            throw new CustomException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);

        String targetObjectUUID = ParamUtil.getNotNull(map, ParamName.PLANNER_TARGET_OBJECT_UUID);
        String taskTitle = ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_TITLE);
        String taskDescription = ParamUtil.getNotEmpty(map, ParamName.PLANNER_TASK_DESCRIPTION);
        String taskColor = ParamUtil.getRGBColor(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_COLOR));
        Date taskStartDate = new Date(Long.parseLong(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_START_DATE)));
        Date taskEndDate = new Date(Long.parseLong(ParamUtil.getNotNull(map, ParamName.PLANNER_TASK_END_DATE)));
        DaoFactory.getPlannerDao().addTask(session, idWorker, taskColor, targetObjectUUID, taskTitle, taskDescription, taskStartDate,
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

    public boolean editTaskById(Session session, String token, Map<String, String> map) throws BadRequestException, CustomException, SQLException {
        if (map.get(ParamName.PLANNER_ID) == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new CustomException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);

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
