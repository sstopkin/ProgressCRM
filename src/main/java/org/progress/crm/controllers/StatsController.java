package org.progress.crm.controllers;

import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
//@Lock(LockType.READ)
public class StatsController {

//    private Map counts;
//
//    public StatsController() {
//        counts = (Map) CacheBuilder.newBuilder()
//                .maximumSize(1000)
//                .expireAfterAccess(7, TimeUnit.DAYS)
//                .build().asMap();
//    }

    public Object getCounts(Session session, String token) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List getCallsStats(Session sesstin, String token) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getStatsDao().getCallsStats(sesstin);
    }
}
