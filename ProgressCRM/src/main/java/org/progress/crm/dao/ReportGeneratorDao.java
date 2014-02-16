package org.progress.crm.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.Workers;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public File priceGen(Session session, int idWorker) throws CustomException, SQLException {
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session);
        Workers worker = DaoFactory.getWorkersDao().getWorkerById(session, idWorker);
        String workerName = worker.getlName() + " " + worker.getmName() + " " + worker.getfName();
        return PDF.GeneratePrice(apartaments, workerName);
    }

    public File apartamentsPageGen(Session session, Integer apartamentsId, int idWorker) throws CustomException, SQLException {
        Apartaments apartament = DaoFactory.getApartamentsDao().getApartamentsById(session, apartamentsId);
        Workers worker = DaoFactory.getWorkersDao().getWorkerById(session, idWorker);
        String workerName = worker.getfName() + " " + worker.getmName() + " " + worker.getlName();
        return PDF.GenerateApartamentsPage(apartament, workerName);
    }
}
