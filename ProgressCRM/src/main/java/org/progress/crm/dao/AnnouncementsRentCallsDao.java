package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.AnnouncementsRentCalls;
import org.progress.crm.logic.DbFields;

/**
 *
 * @author best
 */
public class AnnouncementsRentCallsDao {

    public boolean addAnnouncementsRentCalls(final Session session, final int annId, final int idWorker, final String description) throws SQLException, CustomException {
        AnnouncementsRentCalls announcementsRentCalls = new AnnouncementsRentCalls(annId, description, idWorker);
        session.save(announcementsRentCalls);
        return true;
    }

    public boolean updateAnnouncementsRentCalls(final Session session, final AnnouncementsRentCalls announcementsRentCalls) throws SQLException, CustomException {
        session.update(announcementsRentCalls);
        return true;
    }

    public AnnouncementsRentCalls getAnnouncementsRentCallsById(final Session session, final Integer annId) throws SQLException, CustomException {
        return (AnnouncementsRentCalls) session.get(AnnouncementsRentCalls.class, annId);
    }

    public List<AnnouncementsRentCalls> getAllAnnouncementsRentCalls(final Session session) throws SQLException, CustomException {
        List<AnnouncementsRentCalls> list = session.createCriteria(AnnouncementsRentCalls.class).list();
        return list;
    }

    public boolean deleteAnnouncementsRentCalls(Session session, int idWorker, Integer annId) throws SQLException, CustomException {
        AnnouncementsRentCalls ann = getAnnouncementsRentCallsById(session, annId);
        ann.setDeleted(true);
        session.update(ann);
        return true;
    }

    public List getAnnouncementsRentCallsByAnnouncementsId(Session session, int announcementsId) throws CustomException, SQLException {
        return session.createCriteria(AnnouncementsRentCalls.class)
                .add(Restrictions.eq(DbFields.ANNOUNCEMENTSRENTCALLS.ANNOUNCEMENTSID, announcementsId))
                .addOrder(Order.desc(DbFields.ANNOUNCEMENTSRENTCALLS.DATE))
                .list();
    }
}
