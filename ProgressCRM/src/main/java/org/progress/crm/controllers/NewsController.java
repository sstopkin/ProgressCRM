package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.mail.MessagingException;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Constants;
import org.progress.crm.logic.News;
import org.progress.crm.util.JavaMail;
import org.progress.crm.util.Pair;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.ParamUtil;

@Singleton
public class NewsController {

    @EJB
    AuthenticationManager authManager;

    public List<News> getNews(Session session, String token) throws SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int idWorker = authManager.getUserIdByToken(UUID.fromString(token));
        Pair<Integer, Integer> permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.NEWS, idWorker, Constants.ACL.ACCESS_VIEW);
        return DaoFactory.getNewsDao().getNews(session, permission.getSecond(), idWorker);
    }

    public boolean addNews(Session session, String token, String text, String header) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int idWorker = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.NEWS, idWorker, Constants.ACL.ACCESS_ADD_EIDT);
        DaoFactory.getNewsDao().addNews(session, idWorker, text, header);
        try {
            JavaMail.sendMail(SettingsController.parameters.get("admin.email"), text, header);
        } catch (MessagingException ex) {
            Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean deleteNewsById(Session session, String token, String id) throws CustomException, SQLException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int idWorker = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.NEWS, idWorker, Constants.ACL.ACCESS_DELETE);
        DaoFactory.getNewsDao().removeNewsById(session, idWorker, Integer.valueOf(id));
        return true;
    }

    public boolean editNewsById(Session session, String token, Map<String, String> map) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int newsId = ParamUtil.getInt(map, ParamName.NEWS_ID);
        String text = map.get(ParamName.NEWS_TEXT);
        String header = map.get(ParamName.NEWS_HEADER);
        int idWorker = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.NEWS, idWorker, Constants.ACL.ACCESS_ADD_EIDT);
        DaoFactory.getNewsDao().editNewsById(session, newsId, idWorker, header, text);
        return true;
    }

    public News getNewsById(Session session, String token, Map<String, String> param) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int idWorker = authManager.getUserIdByToken(UUID.fromString(token));
        Pair permission = AclController.getAclCheckAccess(session, Constants.ENTITIES.NEWS, idWorker, Constants.ACL.ACCESS_VIEW);
        return DaoFactory.getNewsDao().getNewsById(session, ParamUtil.getNotEmptyInt(param, ParamName.NEWS_ID));
    }
}
