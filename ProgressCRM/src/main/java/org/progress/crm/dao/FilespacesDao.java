package org.progress.crm.dao;

import org.hibernate.Session;
import org.progress.crm.logic.Filespaces;

public class FilespacesDao {

    public String createFilespace(Session session, String filespaceName) {
        Filespaces fs = new Filespaces(filespaceName);
        session.save(fs);
        return fs.getFilespacesUUID();
    }

    public String getFilespacePathByTargetUUID(Session session, String uuid) {
        return null;
    }
}
