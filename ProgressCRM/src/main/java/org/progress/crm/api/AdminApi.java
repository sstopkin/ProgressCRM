package org.progress.crm.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.WorkersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("admin")
public class AdminApi {

    @EJB
    WorkersController workersController;

    @POST
    @Path("banuser")
    public Response banUserById(@CookieParam("token") final String token, @FormParam("id") final String id)
            throws CustomException, SQLException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                workersController.setActivityUserById(session, token, id, false);
                return ApiHelper.getResponse(true);
            }
        });
    }

    @POST
    @Path("unbanuser")
    public Response unBanUserById(@CookieParam("token") final String token, @FormParam("id") final String id)
            throws CustomException, SQLException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                workersController.setActivityUserById(session, token, id, true);
                return ApiHelper.getResponse(true);
            }
        });
    }

//    plannerAddUserModalLName').val(),
//    fname: $('#plannerAddUserModalFName').val(),
//    mname: $('#plannerAddUserModalMName').val(),
//    email: $('#plannerAddUserModalEmail').val(),
//    password: $('#plannerAddTaskModalDescription').val(),
    @POST
    @Path("adduser")
    public Response addWorker(@CookieParam("token") final String token,
            @FormParam("lname") final String workerLName,
            @FormParam("fname") final String workerFName,
            @FormParam("mname") final String workerMName,
            @FormParam("email") final String workerEmail,
            @FormParam("password") final String workerPassword
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {

                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.WORKER_LAST_NAME, workerLName);
                    map.put(ParamName.WORKER_FIRST_NAME, workerFName);
                    map.put(ParamName.WORKER_MIDDLE_NAME, workerMName);
                    map.put(ParamName.WORKER_EMAIL, workerEmail);
                    map.put(ParamName.WORKER_PASSWORD, workerPassword);
                    int result = workersController.addWorker(session, token, map);

                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AdminApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
