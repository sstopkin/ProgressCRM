package org.progress.crm.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Groups;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.ParamUtil;

@Singleton
public class GroupsController {

    @EJB
    AuthenticationManager authManager;

    public List<Groups> getAllGroups(Session session, String token) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        return DaoFactory.getGroupsDao().getGroups(session);
    }

    public boolean addGroup(Session session, String token, Map<String, String> map) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = authManager.getUserIdByToken(UUID.fromString(token));
        String groupName = ParamUtil.getNotEmpty(map, ParamName.GROUP_NAME);
        DaoFactory.getGroupsDao().addGroup(session, groupName);
        return true;
    }

    public boolean deleteGroup(Session session, String token, Map<String, String> map) {
        return true;
    }
}
