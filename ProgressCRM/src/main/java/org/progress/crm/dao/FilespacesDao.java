package org.progress.crm.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Filespaces;

public class FilespacesDao {
    public String getFilespacePathByUUID(Session session, String uuid) throws SQLException {
        List<Filespaces> list = session.createCriteria(Filespaces.class).add(Restrictions.eq(DbFields.FILESPACES.FILESPACESUUID, uuid)).list();
        return list.get(0).getFilespacesUUID();
    }
}
