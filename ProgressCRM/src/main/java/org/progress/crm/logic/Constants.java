package org.progress.crm.logic;

public class Constants {

    public class LOGSERVICEACTIONSCODE {

        public final static int AUTHOK = 100;
        public final static int AUTHFAIL = 101;
        public final static int LOGOUTOK = 102;
        public final static int LOGOUTFAIL = 103;
    }

    public class SETTINGS {

        public final static String SIMPLE_DATE_FORMAT = "EEE d MMM yy";
        public final static String LOCALE = "ru-RU";

        public final static String BASEPATH = "/var/progresscrm/data/";
    }

    public class ENTITIES {

        public final static int APARTAMENTS = 1;
        public final static int NEWS = 2;
        public final static int PLANNER = 3;
        public final static int WORKERS = 4;
        public final static int CUSTOMERS = 5;
    }

    public class ACL {

        public final static int ACCESS_VIEW = 1;
        public final static int ACCESS_ADD_EIDT = 2;
        public final static int ACCESS_DELETE = 3;

        public final static int CATEGORIES_ALL = 1;
        public final static int CATEGORIES_GROUP = 2;
        public final static int CATEGORIES_OWNER = 3;
        
        
    }

    public class GROUPS {

        public final static int ADMIN_GROUP = 1;
    }
}
