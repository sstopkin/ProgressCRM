/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.News;

@Singleton
public class NewsController {

    @EJB
    AuthenticationManager authenticationManager;

    public List<News> getNews(Session session) throws SQLException {
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
}
