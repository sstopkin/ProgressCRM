package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Constants;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.News;

public class NewsDao {

    public List<News> getNews(Session session, int permission, int idWorker) throws SQLException {
        Criteria cr = session.createCriteria(News.class);
        cr.add(Restrictions.eq(DbFields.NEWS.DELETED, false));
        cr.addOrder(Order.desc(DbFields.NEWS.LASTMODIFY));
        if (permission != Constants.ACL.CATEGORIES_ALL) {
            cr.add(Restrictions.eq(DbFields.APARTAMENTS.IDWORKER, idWorker));
        }
        return cr.list();
    }

    public void addNews(final Session session, final int idWorker, final String header, final String text) throws SQLException {
        session.save(new News(idWorker, header, text));
    }

    public News getNewsById(final Session session, final int newsId) throws SQLException {
        return (News) session.get(News.class, newsId);
    }

    public void editNewsById(final Session session, final int newsId, final int idWorker, final String header, final String text) throws SQLException {
        News news = getNewsById(session, newsId);
        news.setHeader(header);
        news.setIdWorker(idWorker);
        news.setLastModify(new Date());
        news.setText(text);
        session.update(news);
    }

    public void removeNewsById(Session session, int idWorker, int newsId) throws SQLException {
        News news = DaoFactory.getNewsDao().getNewsById(session, newsId);
        news.setLastModify(new Date());
        news.setDeleted(true);
        session.update(news);
    }
}
