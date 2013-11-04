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
import org.progress.crm.logic.AnnouncementsCalls;

/**
 *
 * @author best
 */
@Singleton
public class AnnouncementsCallsController {

    @EJB
    AuthenticationManager authManager;

    public List getAllAnnouncementsCalls(Session session) throws CustomException, SQLException {
        return DaoFactory.getAnnouncementsCallsDao().getAllAnnouncementsCalls(session);
    }

    public boolean addAnnouncementsCalls(Session session, String token, String annId, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsCallsDao().addAnnouncementsCalls(session, Integer.valueOf(annId), idWorker, description);
        return true;
    }

    public boolean deleteAnnouncementsCalls(Session session, String token, String id) throws IsNotAuthenticatedException, SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsCallsDao().deleteAnnouncementsCalls(session, idWorker, idWorker);
        return true;
    }

    public List getAnnouncementsCallsByAnnouncementsId(Session session, String id) throws IsNotAuthenticatedException, BadRequestException, SQLException, CustomException {
        if (id == null) {
            throw new BadRequestException();
        }
        return DaoFactory.getAnnouncementsCallsDao().getAnnouncementsCallsByAnnouncementsId(session, Integer.valueOf(id));
    }
}
