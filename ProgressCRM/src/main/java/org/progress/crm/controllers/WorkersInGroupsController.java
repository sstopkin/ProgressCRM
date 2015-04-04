package org.progress.crm.controllers;

import java.util.Map;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.ParamUtil;

@Singleton
public class WorkersInGroupsController {

    public boolean addUserToGroup(Session session, String token, Map<String, String> map) throws IsNotAuthenticatedException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = ParamUtil.getInt(map, ParamName.WORKER_ID);
        int groupId = ParamUtil.getInt(map, ParamName.GROUP_NAME);
        DaoFactory.getWorkersInGroupsDao().addUserToGroup(session, workerId, groupId);
        return true;
    }

    public boolean deleteUserFromGroup(Session session, String token, Map<String, String> map) throws IsNotAuthenticatedException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = ParamUtil.getInt(map, ParamName.WORKER_ID);
        int groupId = ParamUtil.getInt(map, ParamName.GROUP_NAME);
        DaoFactory.getWorkersInGroupsDao().deleteUserFromGroup(session, workerId, groupId);
        return true;
    }
}
