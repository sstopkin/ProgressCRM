package org.progress.crm.controllers;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;

@Singleton
public class ReportGeneratorController {

    @EJB
    AuthenticationManager authManager;

    public File getPrice(Session session, String token, String status) throws IsNotAuthenticatedException, CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        if (status == null) {
            throw new BadRequestException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        return DaoFactory.getReportGeneratorDao().priceGen(session, idWorker, status);
    }

    public File getXmlCatalog(Session session) throws IsNotAuthenticatedException, CustomException, SQLException {
        try {
            return DaoFactory.getReportGeneratorDao().xmlGen(session);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ReportGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ReportGeneratorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public File getObjectCardByApartamentsId(Session session, String token, String apartamentId) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        return DaoFactory.getReportGeneratorDao().apartamentsPageGen(session, Integer.valueOf(apartamentId), idWorker);
    }
}
