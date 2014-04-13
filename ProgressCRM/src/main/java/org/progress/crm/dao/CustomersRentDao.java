package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
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

    public void editCustomersRentById(Session session, int id, int status, int assigned,
            String description, int idWorker, int idCustomer) throws SQLException, CustomException {
        CustomersRent cr = getCustomersRentById(session, id);
        cr.setAssigned(assigned);
        cr.setDescription(description);
        cr.setIdCustomer(idCustomer);
        cr.setIdWorker(idWorker);
        cr.setLastModify(new Date());
        cr.setStatus(status);
        session.update(cr);
    }
}
