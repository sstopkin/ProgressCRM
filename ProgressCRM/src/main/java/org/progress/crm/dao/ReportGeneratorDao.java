package org.progress.crm.dao;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.Workers;
import org.progress.crm.util.PDF;

public class ReportGeneratorDao {

    public File priceGen(Session session, int idWorker, String status) throws SQLException {
        Workers worker = DaoFactory.getWorkersDao().getWorkerById(session, idWorker);
        String reportAuthorWorkerName = worker.getlName() + " " + worker.getfName() + " " + worker.getmName();

        List<Object> reportContent = session.createSQLQuery(
                "SELECT Apartaments.id, price, Rooms, CityDistrict, CityName,StreetName,HouseNumber,BuildingNumber, "
                + "Floor, Floors, SizeApartament,SizeLiving,SizeKitchen, "
                + "Description, customersFName,customersLName,customersMName, customersPhone, YearOfConstruction, "
                + "FName, MName, Lname\n" + "FROM progresscrm.Apartaments "
                + "LEFT JOIN progresscrm.Workers ON Apartaments.idWorkerTarget=Workers.id "
                + "LEFT JOIN progresscrm.Customers ON Apartaments.idCustomer=Customers.id "
                + "WHERE Apartaments.Deleted='0' AND Apartaments.Status='" + status + "' ORDER BY Apartaments.Rooms;").
                list();
        return PDF.GeneratePrice(reportContent, reportAuthorWorkerName);
    }

    public File apartamentsPageGen(Session session, Integer apartamentsId, int idWorker) throws SQLException {
        Apartaments apartament = DaoFactory.getApartamentsDao().getApartamentsById(session, apartamentsId);
        Workers worker = DaoFactory.getWorkersDao().getWorkerById(session, idWorker);
        String workerName = worker.getlName() + " " + worker.getfName() + " " + worker.getmName();
        return PDF.GenerateApartamentsPage(apartament, workerName);
    }
}
