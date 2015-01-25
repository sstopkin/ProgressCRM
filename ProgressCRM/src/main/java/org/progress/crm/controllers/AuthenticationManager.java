package org.progress.crm.controllers;

import com.google.common.cache.CacheBuilder;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadLogInException;
import org.progress.crm.exceptions.BadLogOutException;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.DisabledUserException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Constants;
import org.progress.crm.logic.Workers;
import org.progress.crm.util.SHA1;

@Singleton
@Lock(LockType.READ)
public class AuthenticationManager {

    private Map tokens;

    public AuthenticationManager() {
        tokens = (Map) CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(7, TimeUnit.DAYS)
                .build().asMap();
    }

    public boolean isAuthentificated(UUID token) {
        if (!tokens.containsKey(token)) {
            return false;
        }
        return true;
    }

    public int getUserIdByToken(UUID token) {
        int userId;
        userId = (int) tokens.get(token);
        return userId;
    }

    public UUID getUserTokenById(int id) {
        Set<Map.Entry> set = tokens.entrySet();
        for (Map.Entry element : set) {
            if ((int) element.getValue() == id) {
                return (UUID) element.getKey();
            }
        }
        return null;
    }

    @Lock(LockType.WRITE)
    public String authUser(Session session, String email, String password)
            throws NoSuchAlgorithmException, SQLException, CustomException {
        if (email == null || password == null) {
            throw new BadRequestException();
        }
        Workers pr = DaoFactory.getWorkersDao().getWorkerByEmail(session, email);
        if (pr == null) {
            //first user must be NULL
            Logger.getLogger(AuthenticationManager.class.getName()).log(Level.SEVERE, null, email + " [" + password + "]" + " Code:" + Constants.LOGSERVICEACTIONSCODE.AUTHFAIL);
            throw new BadLogInException();
        }

        if (!pr.getIsActive()) {
            throw new DisabledUserException();
        }

        if (pr.getPwdhash().equals(SHA1.sha1(password))) {
            UUID token = UUID.randomUUID();
            tokens.put(token, pr.getId());
            Logger.getLogger(AuthenticationManager.class.getName()).log(Level.SEVERE, null, email + " [" + password + "]" + " Code:" + Constants.LOGSERVICEACTIONSCODE.AUTHOK);
            return token.toString();
        } else {
            Logger.getLogger(AuthenticationManager.class.getName()).log(Level.SEVERE, null, email + " [" + password + "]" + " Code:" + Constants.LOGSERVICEACTIONSCODE.AUTHFAIL);
            throw new BadLogInException();
        }
    }

    @Lock(LockType.WRITE)
    public boolean logOut(String token) throws CustomException {
        //FIXME
        try {
            int workerId = getUserIdByToken(UUID.fromString(token));
            Logger.getLogger(AuthenticationManager.class.getName()).log(Level.SEVERE, null, "workerId" + workerId + " Code:" + Constants.LOGSERVICEACTIONSCODE.LOGOUTOK);
            tokens.remove(UUID.fromString(token));
            return true;
        } catch (Exception ex) {
            Logger.getLogger(AuthenticationManager.class.getName()).log(Level.SEVERE, null, "Code:" + Constants.LOGSERVICEACTIONSCODE.LOGOUTFAIL);
            throw new BadLogOutException();
        }
    }

    public String getStatus(Session session, String token) throws SQLException, CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        if (isAuthentificated(UUID.fromString(token))) {
            UUID uuid = UUID.fromString(token);
            int userId = getUserIdByToken(uuid);
            Workers pr = DaoFactory.getWorkersDao().getWorkerById(session, userId);
            if (pr == null) {
                return null;
            }
            return pr.getfName() + ' ' + pr.getlName();
        }
        throw new IsNotAuthenticatedException();
    }

    public int getUserIdByToken(String token) throws SQLException, CustomException {
        if (token == null) {
            return 0;
        }
        UUID uuid = UUID.fromString(token);
        if (isAuthentificated(uuid)) {
            int userId = getUserIdByToken(uuid);
            return userId;
        }
        return 0;
    }

    public boolean validate(Session session, String token) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int workerId = getUserIdByToken(UUID.fromString(token));
        return DaoFactory.getWorkersInGroupsDao().isUserInGroup(session, workerId, Constants.GROUPS.ADMIN_GROUP);
    }
}
