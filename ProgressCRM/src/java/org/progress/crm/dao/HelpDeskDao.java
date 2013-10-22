package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.HelpDeskRequest;

public class HelpDeskDao {

    public boolean addHelpDeskRequest(final Session session, final int idWorker, final String request, final String text, final int status) throws SQLException, CustomException {
        HelpDeskRequest hd = new HelpDeskRequest(idWorker, request, text, status);
        session.save(hd);
        return true;
    }

    public boolean updateHelpDeskRequest(final Session session, final HelpDeskRequest hd) throws SQLException, CustomException {
        session.update(hd);
        return true;
    }

    public HelpDeskRequest getHelpDeskRequestId(final Session session, final Integer hdId) throws SQLException, CustomException {
        HelpDeskRequest hd = (HelpDeskRequest) session.get(HelpDeskRequest.class, hdId);
        return hd;
    }

    public List<HelpDeskRequest> getAllHelpDeskRequests(final Session session) throws SQLException, CustomException {
        List<HelpDeskRequest> list = session.createCriteria(HelpDeskRequest.class).list();
        return list;
    }

    public boolean deleteHelpDeskRequest(Session session, int idWorker, Integer hdId) throws SQLException, CustomException {
        HelpDeskRequest hd = getHelpDeskRequestId(session, hdId);
        session.delete(hd);
        return true;
    }
}
