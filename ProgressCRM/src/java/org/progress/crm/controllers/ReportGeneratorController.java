package org.progress.crm.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.dao.ReportGeneratorDao;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class ReportGeneratorController {

    @EJB
    AuthenticationManager authManager;

    public List getPrice(Session session, String token) throws IsNotAuthenticatedException {
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
//        UUID uuid = UUID.fromString(token);
//        int idWorker = authManager.getUserIdByToken(uuid);
        List<String> result = new ArrayList<>();
        Date curDate = new Date();
        result.add(curDate.toString());
        DaoFactory.getReportGeneratorDao().reportGen(curDate);
        return result;
    }
}
