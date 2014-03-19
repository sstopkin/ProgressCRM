package org.progress.crm.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.DbFields;
import org.progress.crm.logic.Filespaces;

public class FilespacesDao {

    public String createFilespace(Session session, String filespaceName) {
        Filespaces fs = new Filespaces(filespaceName);
        session.save(fs);
        return fs.getFilespacesUUID();
    }

    public String getFilespacePathByTargetUUID(Session session, String uuid) throws CustomException {
        String filespaceUUID = DaoFactory.getApartamentsDao().getApartamentsByUUID(session, uuid).getFilespaceUUID();
        List<Filespaces> list = session.createCriteria(Filespaces.class).add(Restrictions.eq(DbFields.FILESPACES.FILESPACESUUID, filespaceUUID)).list();
        return list.get(0).getFilespacesUUID();
    }
}
