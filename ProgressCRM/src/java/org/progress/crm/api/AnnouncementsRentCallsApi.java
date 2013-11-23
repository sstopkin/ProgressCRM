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
import org.progress.crm.controllers.AnnouncementsRentCallsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

/**
 *
 * @author best
 */
@Stateless
@Path("announcementsrentcalls")
public class AnnouncementsRentCallsApi {

    @EJB
    AnnouncementsRentCallsController announcementsRentCallsController;

    @GET
    @Path("getallannouncementsrentcalls")
    public Response getAllAnnouncementsRentCalls() throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson all = new GsonBuilder().create();
                String result = all.toJson(announcementsRentCallsController.getAllAnnouncementsRentCalls(session));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addannouncementsrentcalls")
    public Response addAnnouncementsRentCalls(@CookieParam("token") final String token,
            @FormParam("description") final String description,
            @FormParam("id") final String annId) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = announcementsRentCallsController.addAnnouncementsRentCalls(session, token, annId, description);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("deleteannouncementsrentcalls")
    public Response deleteAnnouncementsRentCalls(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = announcementsRentCallsController.deleteAnnouncementsRentCalls(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getcalls")
    public Response getAnnouncementsRentCallsById(@QueryParam("id") final String id) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson announcementsCallsById = new GsonBuilder().create();
                String result = announcementsCallsById.toJson(announcementsRentCallsController.getAnnouncementsRentCallsByAnnouncementsId(session, id));
                return ApiHelper.getResponse(result);
            }
        });
    }
}
