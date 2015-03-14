package org.progress.crm.dao;

import org.progress.crm.logic.WorkersInGroups;

public class DaoFactory {

    public static WorkersDao getWorkersDao() {
        return new WorkersDao();
    }

    public static ApartamentsDao getApartamentsDao() {
        return new ApartamentsDao();
    }

    public static CustomersDao getCustomersDao() {
        return new CustomersDao();
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

    public static PlannerDao getPlannerDao() {
        return new PlannerDao();
    }

    public static FilespacesDao getFilespacesDao() {
        return new FilespacesDao();
    }

    public static FileManagerDao getFileManagerDao() {
        return new FileManagerDao();
    }

    public static CommentsDao getCommentsDao() {
        return new CommentsDao();
    }

    public static StatsDao getStatsDao() {
        return new StatsDao();
    }

    public static SettingsDao getSettingsDao() {
        return new SettingsDao();
    }

    public static AclDao getAclDao() {
        return new AclDao();
    }

    public static GroupsDao getGroupsDao() {
        return new GroupsDao();
    }

    public static WorkersInGroupsDao getWorkersInGroupsDao() {
        return new WorkersInGroupsDao();
    }
}
