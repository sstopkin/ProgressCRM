package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class CommentsController {

    @EJB
    AuthenticationManager authManager;

    public List getCommentsByObjectUUID(Session session, String token, String objectUUID) throws CustomException, SQLException {
        if (objectUUID == null || objectUUID.equals("")) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCommentsDao().getCommentsByObjectUUID(session, objectUUID);
    }

    public boolean addCommentByObjectId(Session session, String token, String objectUUID, String text) throws CustomException, SQLException {
        if (objectUUID == null || objectUUID.equals("")) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        DaoFactory.getCommentsDao().addComment(session, objectUUID, text, idWorker);
        return true;
    }
}
