package org.progress.crm.controllers;

import java.io.File;
import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;

/**
 *
 * @author best
 */
@Singleton
public class FileManagerController {

    public List getRootFolderFileList(Session session, String path) {
        return DaoFactory.getFileManagerDao().getFolderFileList(session, path);
    }

    public File getFileByPath(Session session, String path) {
        return DaoFactory.getFileManagerDao().getFileByPath(session, path);
    }

    public Object getHomeFolder(Session session, String id) {
        return DaoFactory.getFileManagerDao().getHomeFolder(session, id);
    }

    public Object mkDir(Session session, String path) {
        return DaoFactory.getFileManagerDao().mkDir(session, path);
    }

    public Object removeFile(Session session, String path) {
        return DaoFactory.getFileManagerDao().removeFile(session, path);
    }
}
