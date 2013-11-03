package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

/**
 *
 * @author best
 */
@Singleton
public class AnnouncementsController {
    
    @EJB
    AuthenticationManager authManager;
    
    public List getAllAnnouncements(Session session) throws CustomException, SQLException {
        return DaoFactory.getAnnouncementsDao().getAllAnnouncements(session);
    }
    
    public boolean addAnnouncements(Session session, String token, String street,
            String rooms, String floor, String floors, String phone, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsDao().addAnnouncements(session, idWorker, street,
                Integer.valueOf(rooms), Integer.valueOf(floor), Integer.valueOf(floors), phone, description);
        return true;
    }
    
    public boolean deleteAnnouncements(Session session, String token, String id) throws IsNotAuthenticatedException, SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsDao().deleteAnnouncements(session, idWorker, idWorker);
        return true;
    }
}
