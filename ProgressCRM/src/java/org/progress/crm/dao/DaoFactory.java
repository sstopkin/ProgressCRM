package org.progress.crm.dao;

public class DaoFactory {

    public static final String UNDEFINED = "undefined";

    public static WorkersDao getWorkersDao() {
        return new WorkersDao();
    }

    public static ApartamentsDao getApartamentsDao() {
        return new ApartamentsDao();
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

    public static NewsDao getNewsDao() {
        return new NewsDao();
    }

    public static LogServiceDao getLogServiceDao() {
        return new LogServiceDao();
    }

    public static AnnouncementsDao getAnnouncementsDao() {
        return new AnnouncementsDao();
    }

    public static AnnouncementsCallsDao getAnnouncementsCallsDao() {
        return new AnnouncementsCallsDao();
    }

    public static AnnouncementsRentDao getAnnouncementsRentDao() {
        return new AnnouncementsRentDao();
    }
}
