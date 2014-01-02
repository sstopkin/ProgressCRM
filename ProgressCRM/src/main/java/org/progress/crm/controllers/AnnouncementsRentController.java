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
import org.progress.crm.logic.AnnouncementsRent;

/**
 *
 * @author best
 */
@Singleton
public class AnnouncementsRentController {

    @EJB
    AuthenticationManager authManager;

    public List getAllAnnouncementsRent(Session session, String token) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getAnnouncementsRentDao().getAllAnnouncementsRent(session);
    }

    public boolean addAnnouncementsRent(Session session, String token, String street, String houseNumber,
            String rooms, String floor, String floors, String price, String phone, String description) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsRentDao().addAnnouncementsRent(session, idWorker, street, houseNumber,
                Integer.valueOf(rooms), Integer.valueOf(floor), Integer.valueOf(floors), Integer.valueOf(price), phone, description);
        return true;
    }

    public boolean deleteAnnouncementsRent(Session session, String token, String id) throws IsNotAuthenticatedException, SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        DaoFactory.getAnnouncementsRentDao().deleteAnnouncementsRent(session, idWorker, idWorker);
        return true;
    }

    public List<AnnouncementsRent> getAnnouncementsRentListByQuery(Session session, String token, String street, String houseNumber,
            String rooms, String floor, String floors, String idWorker, String startDate, String endDate) throws IsNotAuthenticatedException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        String startDate_ = null;
        String endDate_ = null;
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
        if (!startDate.equals("")) {
            startDate_ = startDate;
        }
        if (!endDate.equals("")) {
            endDate_ = endDate;
        }
        return DaoFactory.getAnnouncementsRentDao().getAnnouncementsRentListByQuery(session, street, houseNumber, rooms_, floor_, floors_, idWorker_, startDate_, endDate_);
    }
}
