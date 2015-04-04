package org.progress.crm.controllers;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.PermissionsDeniedException;
import org.progress.crm.logic.ACLEntry;
import org.progress.crm.logic.AccessCategories;
import org.progress.crm.logic.AccessTypes;
import org.progress.crm.logic.Entities;
import org.progress.crm.util.Pair;

@Singleton
public class AclController {

    @EJB
    AuthenticationManager authManager;

    public static Pair<Integer, Integer> getAclCheckAccess(Session session, int idEntity, int idWorker, int idAccessType) throws SQLException, CustomException {
        List<ACLEntry> aclList = DaoFactory.getAclDao().getACL(session, idEntity, idWorker, idAccessType);
        if (aclList.isEmpty()) {
            throw new PermissionsDeniedException();
        } else {
            ACLEntry acl = aclList.get(0);
            return new Pair<>(acl.getIdAccessType(), acl.getIdAccessCategory());
        }
    }

    public List getAllAclList(Session session) throws SQLException, CustomException {
        List<ACLEntry> aclList = DaoFactory.getAclDao().getACLList(session);
        return aclList;
    }

    public List getAllAclListByEntityId(Session session, int idEntity) throws SQLException, CustomException {
        List<Entities> entities = DaoFactory.getAclDao().getACLListByEntityId(session, idEntity);
        return entities;
    }

    public boolean setAllAclList(Session session, ACLEntry[] aclList) throws SQLException {
        List<ACLEntry> currentAclList = DaoFactory.getAclDao().getACLList(session);
        Map<Integer, ACLEntry> hashmap = new HashMap<>();
        for (ACLEntry currentAclList1 : currentAclList) {
            hashmap.put(currentAclList1.getId(), currentAclList1);
        }
        for (ACLEntry aclList1 : aclList) {
            if (hashmap.containsKey(aclList1.getId())) {
                ACLEntry entry = hashmap.get(aclList1.getId());
                DaoFactory.getAclDao().editACLListById(session, aclList1.getId(), aclList1.getIdAccessCategory(),
                        aclList1.getIdAccessType(), aclList1.getIdEntity(), aclList1.getIdWorker());
                hashmap.remove(aclList1.getId());
            } else {
                DaoFactory.getAclDao().addACLList(session, aclList1.getIdEntity(), aclList1.getIdAccessType(),
                        aclList1.getIdWorker(), aclList1.getIdAccessCategory());
            }
        }
        for (Entry<Integer, ACLEntry> e : hashmap.entrySet()) {
            DaoFactory.getAclDao().removeACLListById(session, e.getKey());
        }
        return true;
    }

    class IdComparator implements Comparator<ACLEntry> {

        @Override
        public int compare(ACLEntry a, ACLEntry b) {
            return a.getId() < b.getId() ? -1 : a.getId() == b.getId() ? 0 : 1;
        }
    }

    public List getAllEntitiesList(Session session) {
        List<Entities> entitiesList = DaoFactory.getAclDao().getEntitiesList(session);
        return entitiesList;
    }

    public List getAllAccessCategoriesList(Session session) {
        List<AccessCategories> accessCategoriesList = DaoFactory.getAclDao().getAccessCategoriesList(session);
        return accessCategoriesList;
    }

    public List getAllAccessTypesList(Session session) {
        List<AccessTypes> accessCategoriesList = DaoFactory.getAclDao().getAccessTypesList(session);
        return accessCategoriesList;
    }
}
