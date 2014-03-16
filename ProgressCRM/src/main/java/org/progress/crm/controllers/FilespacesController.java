package org.progress.crm.controllers;

import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;

@Singleton
public class FilespacesController {

    public String createFilespace(Session session, String filespaceName) {
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
        return (String) DaoFactory.getFilespacesDao().createFilespace(session, filespaceName);
    }

    public String getFilespacePathByTargetUUID(Session session, String uuid) throws BadRequestException {
        if (uuid == null) {
            throw new BadRequestException();
        }
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
        return DaoFactory.getFilespacesDao().getFilespacePathByTargetUUID(session, uuid);
    }

}
