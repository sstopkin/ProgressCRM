package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.AnnouncementsRent;
import org.progress.crm.logic.DbFields;

public class AnnouncementsRentDao {

    public boolean addAnnouncementsRent(final Session session, final int idWorker, final String street, final String houseNumber,
            final int rooms, final int floor, final int floors, final int price, final String phone, final String description) throws SQLException, CustomException {
        AnnouncementsRent hd = new AnnouncementsRent(street, houseNumber, rooms, floor, floors, price, phone, description, idWorker);
        session.save(hd);
        return true;
    }

    public boolean updateAnnouncementsRent(final Session session, final AnnouncementsRent announcementsRent) throws SQLException, CustomException {
        session.update(announcementsRent);
        return true;
    }

    public AnnouncementsRent getAnnouncementsRentById(final Session session, final Integer annId) throws SQLException, CustomException {
        return (AnnouncementsRent) session.get(AnnouncementsRent.class, annId);
    }

    public List<AnnouncementsRent> getAllAnnouncementsRent(final Session session) throws SQLException, CustomException {
        List<AnnouncementsRent> list = session.createCriteria(AnnouncementsRent.class).
                add(Restrictions.eq(DbFields.ANNOUNCEMENTSRENT.DELETED, false)).list();
        return list;
    }

    public boolean deleteAnnouncementsRent(Session session, int idWorker, Integer annId) throws SQLException, CustomException {
        AnnouncementsRent ann = getAnnouncementsRentById(session, annId);
        ann.setDeleted(true);
        session.update(ann);
        return true;
    }

    public List<AnnouncementsRent> getAnnouncementsRentListByQuery(Session session, String street, String houseNumber, int rooms, int floor, int floors, int idWorker, String startDate, String endDate) throws SQLException, SQLException, SQLException, SQLException {
        Criteria criteria = session.createCriteria(AnnouncementsRent.class);
        if (rooms != -1) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.ROOMS, rooms));
        }
        if (floor != -1) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.FLOOR, floor));
        }
        if (floors != -1) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.FLOORS, floors));
        }
        if (idWorker != -1) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.IDWORKER, idWorker));
        }
        if ((startDate != null) && (endDate != null) && (startDate.equals(endDate))) {
            criteria.add(Restrictions.sqlRestriction(DbFields.ANNOUNCEMENTSRENT.CREATIONDATE + " >= CURDATE()"));
        } else {
            if (startDate != null) {
                criteria.add(Restrictions.sqlRestriction(DbFields.ANNOUNCEMENTSRENT.CREATIONDATE + " >= '" + startDate + "'"));
            }
            if (endDate != null) {
                criteria.add(Restrictions.sqlRestriction(DbFields.ANNOUNCEMENTSRENT.CREATIONDATE + " <= '" + endDate + "'"));
            }
        }
        if (!street.equals("")) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.STREETS, street));
        }
        if (!houseNumber.equals("")) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.HOUSENUMBER, houseNumber));
        }
        criteria.add(Restrictions.eq(DbFields.ANNOUNCEMENTSRENT.DELETED, false));
        return criteria.list();
//.add(Restrictions.between("weight", minWeight, maxWeight))
    }
}
