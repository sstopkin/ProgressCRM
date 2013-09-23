package org.progress.crm.dao;

import java.io.File;
import org.hibernate.Session;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public File priceGen(Session session) {


        return PDF.Gen();
    }

    public File apartamentsPageGen(Session session, Integer apartamentsId) throws CustomException {
        Apartaments apart = DaoFactory.getApartamentsDao().getApartamentsById(session, apartamentsId);
        return PDF.Gen();
    }
}
