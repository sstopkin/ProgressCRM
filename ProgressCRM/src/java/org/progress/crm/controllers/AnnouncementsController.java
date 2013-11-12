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

    public Object getAnnouncementsListByQuery(Session session, String token, String street,
            String rooms, String floor, String floors, String idWorker, String sdated, String sdatem,
            String sdatey,
            String edated,
            String edatem,
            String edatey) throws IsNotAuthenticatedException {
        //FIXME!!!!!    
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int rooms_;
        int floor_;
        int floors_;
        int idWorker_;
        if (rooms.equals("")) {
            rooms_ = -1;
        } else {
            rooms_ = Integer.valueOf(rooms);
        }
        if (floor.equals("")) {
            floor_ = -1;
        } else {
            floor_ = Integer.valueOf(floor);
        }
        if (floors.equals("")) {
            floors_ = -1;
        } else {
            floors_ = Integer.valueOf(floors);
        }
        if (idWorker.equals("")) {
            idWorker_ = -1;
        } else {
            idWorker_ = Integer.valueOf(idWorker);
        }
        return DaoFactory.getAnnouncementsDao().getAnnouncementsListByQuery(session, street, rooms_, floor_, floors_, idWorker_);
    }
}
