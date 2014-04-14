package org.progress.crm.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.Comments;
import org.progress.crm.logic.DbFields;

public class SearchDao {

    public List searchByQUery(Session session, int assigned, int idWorker, String startDate, String endDate, String contains, String type) throws CustomException {
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
        if (!contains.equals("")) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM Comments where MATCH(text) AGAINST ('").append(contains).append("' IN NATURAL LANGUAGE MODE)");
            sql.append(";");
            SQLQuery query = session.createSQLQuery(sql.toString());
            query.addEntity(Comments.class);
            List lcomments = query.list();
            for (Iterator iterator
                    = lcomments.iterator(); iterator.hasNext();) {
                Comments comment = (Comments) iterator.next();

                Apartaments aparts = DaoFactory.getApartamentsDao().getApartamentsByUUID(session, comment.getObjectUUID());
                if (type.equals("apartamentsprepare")) {
                    if (aparts.getStatus() == 0) {
                        resList.add(aparts);
                    }
                } else {
                    if (aparts.getStatus() != 0) {
                        resList.add(aparts);
                    }
                }
            }
        } else {
            Criteria criteria = session.createCriteria(Apartaments.class);
            if (type.equals("apartamentsprepare")) {
                criteria.add(Restrictions.eq(DbFields.APARTAMENTS.STATUS, 0));
            } else {
                criteria.add(Restrictions.not(Restrictions.eq(DbFields.APARTAMENTS.STATUS, 0)));
            }
            criteria.add(Restrictions.eq(DbFields.APARTAMENTS.DELETED, false));
            resList = criteria.list();
        }
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
