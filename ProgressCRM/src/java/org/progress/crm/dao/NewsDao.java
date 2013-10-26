package org.progress.crm.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.News;

public class NewsDao {
    
    public List<News> getNews(Session session) {
        List<News> list = session.createCriteria(News.class).list();
        return list;
    }
    
    public boolean addNews(final Session session, final int idWorker, final String header, final String text) {
        return (boolean) session.save(new News(idWorker, header, text));
    }
    
    public News getNewsById(final Session session, final int newsId) {
        return (News) session.get(News.class, newsId);
    }
    
    public void editNewsById(final Session session, final int newsId, final int idWorker, final String header, final String text) {
        News news = getNewsById(session, newsId);
        news.setHeader(header);
        news.setIdWorker(idWorker);
        news.setLastModify(new Date());
        news.setText(text);
        session.update(news);
    }
    
    public void removeNewsById(Session session, int idWorker, int newsId) {
        News news = DaoFactory.getNewsDao().getNewsById(session, newsId);
        news.setLastModify(new Date());
        news.setDeleted(true);
        session.update(news);
    }
}
