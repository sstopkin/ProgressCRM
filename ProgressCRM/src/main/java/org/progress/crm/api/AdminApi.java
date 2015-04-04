package org.progress.crm.api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.AuthenticationManager;
import org.progress.crm.controllers.WorkersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
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
                Map<String, String> map = new HashMap<>();
                map.put(ParamName.WORKER_ID_TARGET, id);
                map.put(ParamName.STATUS, String.valueOf(false));
                workersController.setActivityUserById(session, token, map);
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
                Map<String, String> map = new HashMap<>();
                map.put(ParamName.WORKER_ID_TARGET, id);
                map.put(ParamName.STATUS, String.valueOf(true));
                workersController.setActivityUserById(session, token, map);
                return ApiHelper.getResponse(true);
            }
        });
    }
}
