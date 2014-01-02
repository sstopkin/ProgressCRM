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

/**
 *
 * @author best
 */
@Singleton
public class AnnouncementsRentCallsController {

    @EJB
    AuthenticationManager authManager;

    public List getAllAnnouncementsRentCalls(Session session, String token) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getAnnouncementsRentCallsDao().getAllAnnouncementsRentCalls(session);
    }

    public boolean addAnnouncementsRentCalls(Session session, String token, String annId, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsRentCallsDao().addAnnouncementsRentCalls(session, Integer.valueOf(annId), idWorker, description);
        return true;
    }

    public boolean deleteAnnouncementsRentCalls(Session session, String token, String id) throws IsNotAuthenticatedException, SQLException, CustomException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsRentCallsDao().deleteAnnouncementsRentCalls(session, idWorker, idWorker);
        return true;
    }

    public List getAnnouncementsRentCallsByAnnouncementsId(Session session, String id) throws IsNotAuthenticatedException, BadRequestException, SQLException, CustomException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (id == null) {
            throw new BadRequestException();
        }
        return DaoFactory.getAnnouncementsRentCallsDao().getAnnouncementsRentCallsByAnnouncementsId(session, Integer.valueOf(id));
    }
}
