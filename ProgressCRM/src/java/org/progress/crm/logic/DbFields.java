package org.progress.crm.logic;

public class DbFields {

    // table Apartaments
    public class APARTAMENTS {

        public final static String ID = "id";
        public final static String APPROVED = "IsApproved";
        public final static String DELETED = "deleted";
        public final static String ROOMS = "rooms";
    }

    public class WORKERS {

        public final static String EMAIL = "email";
    }

    // table ApartamentsPhoto
    public class APARTAMENTS_PHOTO {

        public final static String APARTAMENTS_ID = "ApartamentsId";
    }

    public class CALLS {

        public final static String ID = "id";
        public final static String DATE = "date";
    }

    public class NEWS {

        public final static String ID = "id";
        public final static String LASTMODIFY = "lastModify";
        public final static String DELETED = "deleted";
    }
}
