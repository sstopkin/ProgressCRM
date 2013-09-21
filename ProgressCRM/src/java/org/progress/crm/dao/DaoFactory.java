package org.progress.crm.dao;

public class DaoFactory {

    public static final String UNDEFINED = "undefined";

    public static WorkersDao getWorkersDao() {
        return new WorkersDao();
    }

    public static ApartamentsDao getApartamentsDao() {
        return new ApartamentsDao();
    }

    public static StreetsDao getStreetsDao() {
        return new StreetsDao();
    }

    public static CustomersDao getCustomersDao() {
        return new CustomersDao();
    }

    public static ApartamentsPhotoDao getApartamentsPhotoDao() {
        return new ApartamentsPhotoDao();
    }

    public static HelpDeskDao getHelpDeskDao() {
        return new HelpDeskDao();
    }

    public static CallsDao getCallsDao() {
        return new CallsDao();
    }

    public static ReportGeneratorDao getReportGeneratorDao() {
        return new ReportGeneratorDao();
    }
}
