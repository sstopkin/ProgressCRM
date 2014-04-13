package org.progress.crm.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.DbFields;

public class SearchDao {

    public List searchByQUery(Session session, int assigned, int idWorker, String startDate, String endDate, String type) {
//        Class c = Class.forName("org.progress.crm.dao.Apartaments");
        Criteria criteria = session.createCriteria(Apartaments.class);
        if (assigned != -1) {
            criteria.add(Restrictions.like(DbFields.APARTAMENTS.ASSIGNED, assigned));
        }
        if (idWorker != -1) {
            criteria.add(Restrictions.like(DbFields.APARTAMENTS.IDWORKER, idWorker));
        }
//        if ((status != -1) && (status != 0)) {
//            criteria.add(Restrictions.like(DbFields.CUSTOMERSRENT.STATUS, status));
//        }
        if ((startDate != null) && (endDate != null) && (startDate.equals(endDate))) {
            criteria.add(Restrictions.sqlRestriction(DbFields.CUSTOMERSRENT.CREATIONDATE + " >= CURDATE()"));
        } else {
            if (startDate != null) {
                criteria.add(Restrictions.sqlRestriction(DbFields.CUSTOMERSRENT.CREATIONDATE + " >= '" + startDate + "'"));
            }
            if (endDate != null) {
                criteria.add(Restrictions.sqlRestriction(DbFields.CUSTOMERSRENT.CREATIONDATE + " <= '" + endDate + "'"));
            }
        }
        criteria.add(Restrictions.eq(DbFields.CUSTOMERSRENT.DELETED, false));
        //.add(Restrictions.between("weight", minWeight, maxWeight))
        return criteria.list();
    }
}
