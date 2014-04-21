package org.progress.crm.controllers;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class SearchController {

    @EJB
    AuthenticationManager authManager;

    public List getListByQuery(Session session, String token, String assigned, String idWorker,
            String startDate, String endDate, String contains, String type)
            throws IsNotAuthenticatedException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        String startDate_ = null;
        String endDate_ = null;
        int idWorker_;
        int assigned_;
        if (idWorker.equals("")) {
            idWorker_ = -1;
        } else {
            idWorker_ = Integer.valueOf(idWorker);
        }
        if (!startDate.equals("")) {
            startDate_ = startDate;
        }
        if (!endDate.equals("")) {
            endDate_ = endDate;
        }
        if (assigned.equals("")) {
            assigned_ = -1;
        } else {
            assigned_ = Integer.valueOf(assigned);
        }
        if (type.equals("apartaments") || type.equals("apartamentsprepare")) {
            return DaoFactory.getApartamentsDao().searchByQUery(session,
                    assigned_, idWorker_, startDate_, endDate_, contains, type);
        } else {
            return null;
        }
    }

}
