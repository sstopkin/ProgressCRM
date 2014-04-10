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
    
    public List getCommentsByObjectId(Session session, String token, String objectId, String objectType) throws CustomException, SQLException {
        if (objectId == null || objectType == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCommentsDao().getCommentsByObjectId(session, Integer.valueOf(objectId), Integer.valueOf(objectType));
    }
    
    public boolean addCommentByObjectId(Session session, String token, String objectId, String objectType, String text) throws CustomException, SQLException {
        if (objectId == null || objectId.equals("")) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        
        DaoFactory.getCommentsDao().addComment(session, Integer.valueOf(objectId), Integer.valueOf(objectType), text, idWorker);
        return true;
    }
}
