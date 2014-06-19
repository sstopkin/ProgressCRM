package org.progress.crm.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Calls;
import org.progress.crm.logic.DbFields;

public class StatsDao {

    public List getCallsStats(Session session) {
        List resList = new ArrayList();

//        if (assigned != -1) {
//            criteria.add(Restrictions.like(DbFields.APARTAMENTS.ASSIGNED, assigned));
//        }
//        if (idWorker != -1) {
//            criteria.add(Restrictions.like(DbFields.APARTAMENTS.IDWORKER, idWorker));
//        }
//        if ((status != -1) && (status != 0)) {
//            criteria.add(Restrictions.like(DbFields.CUSTOMERSRENT.STATUS, status));
//        }
//        if (!contains.equals("")) {
//            StringBuilder sql = new StringBuilder();
//            sql.append("SELECT Apartaments.* FROM Comments ")
//                    .append("LEFT JOIN Apartaments on Comments.objectUUID=Apartaments.apartamentUUID ")
//                    .append("where MATCH(text) AGAINST ('")
//                    .append(contains)
//                    .append("' IN NATURAL LANGUAGE MODE)");
//            if (type.equals("apartamentsprepare")) {
//                sql.append(" and Apartaments.status=\"0\"");
//            } else {
//                sql.append(" and Apartaments.status<>\"0\"");
//            }
//            sql.append(";");
//            SQLQuery query = session.createSQLQuery(sql.toString());
//            query.addEntity(Apartaments.class);
//            List lApartaments = query.list();
//            return lApartaments;
//        } else {
        Criteria criteria = session.createCriteria(Calls.class);
        criteria.add(Restrictions.eq(DbFields.APARTAMENTS.STATUS, 0));
        criteria.add(Restrictions.not(Restrictions.eq(DbFields.APARTAMENTS.STATUS, 0)));
        criteria.add(Restrictions.eq(DbFields.APARTAMENTS.DELETED, false));
        resList = criteria.list();
//        }
//        if ((startDate != null) && (endDate != null) && (startDate.equals(endDate))) {
//            criteria.add(Restrictions.sqlRestriction(DbFields.COMMENTS.CREATIONDATE + " >= CURDATE()"));
//        } else {
//            if (startDate != null) {
//                criteria.add(Restrictions.sqlRestriction(DbFields.COMMENTS.CREATIONDATE + " >= '" + startDate + "'"));
//            }
//            if (endDate != null) {
//                criteria.add(Restrictions.sqlRestriction(DbFields.COMMENTS.CREATIONDATE + " <= '" + endDate + "'"));
//            }
//        }

        //.add(Restrictions.between("weight", minWeight, maxWeight))
        return resList;

    }

}
