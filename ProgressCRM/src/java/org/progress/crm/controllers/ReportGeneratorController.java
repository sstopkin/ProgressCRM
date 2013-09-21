package org.progress.crm.controllers;

import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.ReportGeneratorDao;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class ReportGeneratorController {

    @EJB
    AuthenticationManager authManager;

    public Object getPrice(Session session, String token) throws IsNotAuthenticatedException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        ReportGeneratorDao gen = new ReportGeneratorDao();
        gen.reportGen();
        return null;
    }
}
