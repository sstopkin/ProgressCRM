package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.CustomersRent;
import org.progress.crm.logic.DbFields;

public class CustomersRentDao {

    public boolean addCustomersRent(final Session session, final int status, final int assigned,
            final String description, final int idWorker, final int idCustomer) throws SQLException, CustomException {
        CustomersRent customersRent = new CustomersRent(status, assigned, description, idWorker, idCustomer);
        session.save(customersRent);
        return true;
    }

    public boolean updateCustomersRent(final Session session, final CustomersRent customersRent) throws SQLException, CustomException {
        session.update(customersRent);
        return true;
    }

    public CustomersRent getCustomersRentById(final Session session, final Integer customersRentId) throws SQLException, CustomException {
        return (CustomersRent) session.get(CustomersRent.class, customersRentId);
    }

    public List<CustomersRent> getAllCustomersRent(final Session session) throws SQLException, CustomException {
        List<CustomersRent> list = session.createCriteria(CustomersRent.class).
                add(Restrictions.eq(DbFields.CUSTOMERSRENT.DELETED, false)).list();
        return list;
    }

    public boolean deleteCustomersRent(Session session, int customersRentId) throws SQLException, CustomException {
        CustomersRent ann = getCustomersRentById(session, customersRentId);
        ann.setDeleted(true);
        session.update(ann);
        return true;
    }

    public List<CustomersRent> getCustomersRentListByQuery(Session session,
            int assigned, int idWorker, String startDate, String endDate, int status)
            throws SQLException, SQLException, SQLException, SQLException {
        Criteria criteria = session.createCriteria(CustomersRent.class);
        if (assigned != -1) {
            criteria.add(Restrictions.like(DbFields.CUSTOMERSRENT.ASSIGNED, assigned));
        }
        if (idWorker != -1) {
            criteria.add(Restrictions.like(DbFields.ANNOUNCEMENTSRENT.IDWORKER, idWorker));
        }
        if ((status != -1) && (status != 0)) {
            criteria.add(Restrictions.like(DbFields.CUSTOMERSRENT.STATUS, status));
        }
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
        return criteria.list();
//.add(Restrictions.between("weight", minWeight, maxWeight))
    }
}
