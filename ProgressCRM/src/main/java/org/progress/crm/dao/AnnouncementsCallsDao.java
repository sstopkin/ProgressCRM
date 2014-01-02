package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.AnnouncementsCalls;
import org.progress.crm.logic.DbFields;

/**
 *
 * @author best
 */
public class AnnouncementsCallsDao {

    public boolean addAnnouncementsCalls(final Session session, final int annId, final int idWorker, final String description) throws SQLException, CustomException {
        AnnouncementsCalls announcementsCalls = new AnnouncementsCalls(annId, description, idWorker);
        session.save(announcementsCalls);
        return true;
    }

    public boolean updateAnnouncementsCalls(final Session session, final AnnouncementsCalls announcementsCalls) throws SQLException, CustomException {
        session.update(announcementsCalls);
        return true;
    }

    public AnnouncementsCalls getAnnouncementsCallsById(final Session session, final Integer annId) throws SQLException, CustomException {
        return (AnnouncementsCalls) session.get(AnnouncementsCalls.class, annId);
    }

    public List<AnnouncementsCalls> getAllAnnouncementsCalls(final Session session) throws SQLException, CustomException {
        List<AnnouncementsCalls> list = session.createCriteria(AnnouncementsCalls.class).list();
        return list;
    }

    public boolean deleteAnnouncementsCalls(Session session, int idWorker, Integer annId) throws SQLException, CustomException {
        AnnouncementsCalls ann = getAnnouncementsCallsById(session, annId);
        ann.setDeleted(true);
        session.update(ann);
        return true;
    }

    public List getAnnouncementsCallsByAnnouncementsId(Session session, int announcementsId) throws CustomException, SQLException {
        return session.createCriteria(AnnouncementsCalls.class)
                .add(Restrictions.eq(DbFields.ANNOUNCEMENTSCALLS.ANNOUNCEMENTSID, announcementsId))
                .addOrder(Order.desc(DbFields.ANNOUNCEMENTSCALLS.DATE))
                .list();
    }
}
