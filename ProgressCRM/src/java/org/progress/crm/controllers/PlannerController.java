package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Planner;

@Singleton
public class PlannerController {
    
    @EJB
    AuthenticationManager authenticationManager;
    
    public List<Planner> getTasksByWorker(Session session, String token) throws SQLException, IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        return DaoFactory.getPlannerDao().getTasksByWorker(session, idWorker);
    }
    
    public List<Planner> getTasks(Session session, String token) throws SQLException, IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getPlannerDao().getTasks(session);
    }
    
    public boolean addTask(Session session, String token, String taskType,
            String taskId, String taskDescription, String taskDate) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        DaoFactory.getPlannerDao().addTask(session, idWorker,
                Integer.valueOf(taskType), Integer.valueOf(taskId), taskDescription, new Date());
        return true;
    }
    
    public boolean deleteTaskById(Session session, String token, String id) throws IsNotAuthenticatedException, BadRequestException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        DaoFactory.getPlannerDao().removeTaskById(session, idWorker, Integer.valueOf(id));
        return true;
    }
    
    public boolean editNewsById(Session session, String token, String plannerId, String taskType,
            String taskId, String taskDescription, String taskDate) throws IsNotAuthenticatedException, BadRequestException {
        if (plannerId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
//        DaoFactory.getPlannerDao().editTaskById(session, Integer.valueOf(plannerId),
//                idWorker, Integer.valueOf(taskType), Integer.valueOf(taskId), taskDescription, taskDate);
        return true;
    }
}
