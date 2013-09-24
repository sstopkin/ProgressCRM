package org.progress.crm.dao;

import java.io.File;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public File priceGen(Session session) throws CustomException {
        List aparts = DaoFactory.getApartamentsDao().getAllApartaments(session);
        return PDF.GeneratePrice(aparts);
    }

    public File apartamentsPageGen(Session session, Integer apartamentsId) throws CustomException {
        Apartaments apart = DaoFactory.getApartamentsDao().getApartamentsById(session, apartamentsId);
        return PDF.GenerateApartamentsPage(apart);
    }
}
