package org.progress.crm.controllers;

import java.util.Date;
import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Customers;

@Singleton
public class CustomersController {

    public Customers getCustomerById(Session session, String token, String customerId) throws CustomException {
        if (customerId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCustomersDao().getCustomerById(session, Integer.valueOf(customerId));
    }

    public List getCustomerObjectsById(Session session, String token, String customerId) throws CustomException {
        if (customerId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCustomersDao().getCustomerObjectsById(session, Integer.valueOf(customerId));
    }

    public boolean addCustomer(Session session,
            String token,
            String fName,
            String lName,
            String mName,
            String customersMonthOfBirthday,
            String customersDayOfBirthday,
            String customersYearOfBirthday,
            String customersSex,
            String customersPhone,
            String customersEmail,
            String customersAddress,
            String customersExtra) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        DaoFactory.getCustomersDao().addCustomer(session,
                fName, lName,
                mName,
                Integer.valueOf(customersMonthOfBirthday),
                Integer.valueOf(customersDayOfBirthday),
                Integer.valueOf(customersYearOfBirthday),
                Integer.valueOf(customersSex),
                customersPhone,
                customersEmail,
                customersAddress,
                customersExtra
        );
        return true;
    }

    public boolean removeCustomer(Session session, String token, String id) throws CustomException {
        if (id == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        DaoFactory.getCustomersDao().removeCustomerById(session, Integer.valueOf(id));
        return true;
    }

    public List getCustomerByString(Session session, String token, String str) throws CustomException {
        if (str == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCustomersDao().findCustomerByStr(session, str);
    }

    public boolean editCustomer(Session session,
            String token,
            String id,
            String fName,
            String lName,
            String mName,
            String customersMonthOfBirthday,
            String customersDayOfBirthday,
            String customersYearOfBirthday,
            String customersSex,
            String customersPhone,
            String customersEmail,
            String customersAddress,
            String customersExtra) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        Customers customers = DaoFactory.getCustomersDao().getCustomerById(session, Integer.valueOf(id));
        customers.setDeleted(false);
        customers.setCustomersFname(fName);
        customers.setCustomersMname(mName);
        customers.setCustomersLname(lName);
        customers.setCustomersYearOfBirthday(Integer.valueOf(customersYearOfBirthday));
        customers.setCustomersDayOfBirthday(Integer.valueOf(customersDayOfBirthday));
        customers.setCustomersMonthOfBirthday(Integer.valueOf(customersMonthOfBirthday));
        customers.setCustomersPhone(customersPhone);
        customers.setCustomersEmail(customersEmail);
        customers.setCustomersAddress(customersAddress);
        customers.setCustomersExtra(customersExtra);

        DaoFactory.getCustomersDao().modifyCustomer(session, customers);
        return true;
    }

    public List getCustomersListByQuery(Session session, String token, String str) throws CustomException {
        if (str == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCustomersDao().findCustomerByStr(session, str);
    }

    public List<Customers> getAllCustomers(Session session, String token) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getCustomersDao().getAllCustomers(session);
    }

    public List<Customers> getCustomersListByBirthday(Session session, Date currentDay) throws IsNotAuthenticatedException {
        return DaoFactory.getCustomersDao().getCustomersListByBirthday(session, currentDay);
    }
}
