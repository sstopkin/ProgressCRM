package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.News;

@Singleton
public class NewsController {

    @EJB
    AuthenticationManager authenticationManager;

    public List<News> getNews(Session session, String token) throws SQLException, IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getNewsDao().getNews(session);
    }

    public boolean addNews(Session session, String token, String text, String header) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        DaoFactory.getNewsDao().addNews(session, idWorker, text, header);
        return true;
    }

    public boolean deleteNewsById(Session session, String token, String id) throws IsNotAuthenticatedException, BadRequestException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        DaoFactory.getNewsDao().removeNewsById(session, idWorker, Integer.valueOf(id));
        return true;
    }

    public boolean editNewsById(Session session, String newsId, String token, String text, String header) throws IsNotAuthenticatedException, BadRequestException {
        if (newsId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authenticationManager.getUserIdByToken(uuid);
        DaoFactory.getNewsDao().editNewsById(session, Integer.valueOf(newsId), idWorker, header, text);
        return true;
    }
}
