package org.progress.crm.dao;

import java.util.List;
import org.hibernate.Session;
import org.progress.crm.logic.News;

public class NewsDao {

    public List<News> getNews(Session session) {
        List<News> list = session.createCriteria(News.class).list();
        return list;
    }

    public boolean addNews(final Session session, final int idWorker, final String header, final String text) {
        return (boolean) session.save(new News(idWorker, header, text));
    }
}
