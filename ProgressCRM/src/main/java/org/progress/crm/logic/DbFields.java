package org.progress.crm.logic;

public class DbFields {

    public class APARTAMENTS {

        public final static String ID = "id";
        public final static String AD = "isAD";
        public final static String APPROVED = "IsApproved";
        public final static String DELETED = "deleted";
        public final static String ROOMS = "rooms";
        public final static String STATUS = "status";
        public final static String FILESPACEUUID = "filespaceUUID";
        public final static String APARTAMENTUUID = "apartamentUUID";
        public final static String ASSIGNED = "idWorkerTarget";
        public final static String IDWORKER = "idWorker";
        public final static String IDCUSTOMER = "idCustomer";
        public final static String CREATIONDATE = "сreationDate";
    }

    public class CUSTOMERS {

        public final static String ID = "id";
        public final static String STATUS = "status";
        public final static String DELETED = "deleted";
    }

    public class WORKERS {

        public final static String ID = "id";
        public final static String EMAIL = "email";
        public final static String IS_ACTIVE = "isActive";
    }

    public class GROUPS {

        public final static String ID = "id";
        public final static String DELETED = "deleted";
        public final static String CREATIONDATE = "CreationDate";
    }

    public class WORKERS_IN_GROUPS {

        public final static String ID = "id";
        public final static String WORKER_ID = "idWorker";
        public final static String GROUP_ID = "idGroup";

    }

    public class CALLS {

        public final static String ID = "id";
        public final static String DATE = "date";
        public final static String OBJECTUUID = "objectUUID";
    }

    public class COMMENTS {

        public final static String CREATIONDATE = "CreationDate";
        public final static String OBJECTUUID = "objectUUID";
    }

    public class NEWS {

        public final static String ID = "id";
        public final static String LASTMODIFY = "lastModify";
        public final static String DELETED = "deleted";
    }

    public class PLANNER {

        public final static String ID = "id";
        public final static String TASKTYPE = "taskType";
        public final static String TASKTARGETUUID = "targetOjectUUID";
        public final static String CREATIONDATE = "creationDate";
        public final static String TASKSTARTDATE = "taskStartDate";
        public final static String TASKENDDATE = "taskEndDate";
        public final static String IDWORKER = "idWorker";
        public final static String DELETED = "deleted";
    }

    public class HELPDESK {

        public final static String DELETED = "deleted";
    }

    public class FILESPACES {

        public final static String FILESPACESUUID = "filespacesUUID";
    }

    public class SETTINGS {

        public final static String PARAMETERS = "parameters";
        public final static String VALUE = "value";
    }

    public class ACLENTRY {

        public final static String ID = "id";
        public final static String ENTITY_ID = "idEntity";
        public final static String ACCESS_TYPE_ID = "idAccessType";
        public final static String WORKER_ID = "idWorker";
        public final static String ACCESS_CATEGORY_ID = "idAccessCategory";

    }

    public class ENTITIES {

        public final static String ID = "id";
    }

    public class ACCESS_CATEGORIES {

        public final static String ID = "id";
    }

    public class ACCESS_TYPES {

        public final static String ID = "id";
    }
}
