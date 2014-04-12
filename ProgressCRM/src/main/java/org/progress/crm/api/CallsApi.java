package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.CallsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("calls")
public class CallsApi {

    @EJB
    CallsController callsController;

    @GET
    @Path("getcalls")
    public Response getCallsByObjectId(@QueryParam("objectUUID") final String objectUUID,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(callsController.getCallsByObjectUUID(session, token, objectUUID));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addcall")
    public Response addCall(@CookieParam("token") final String token,
            @FormParam("objectUUID") final String objectUUID,
            @FormParam("incomingPhoneNumber") final String incomingPhoneNumber,
            @FormParam("description") final String description) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = callsController.addCallsByObjectUUID(session, token, objectUUID, incomingPhoneNumber, description);
                return ApiHelper.getResponse(result);
            }
        });
    }
}
