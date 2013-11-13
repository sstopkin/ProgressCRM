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
import org.progress.crm.controllers.AnnouncementsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

/**
 *
 * @author best
 */
@Stateless
@Path("announcements")
public class AnnouncementsApi {

    @EJB
    AnnouncementsController announcementsController;

    @GET
    @Path("getallannouncements")
    public Response getAllAnnouncements() throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson allHelpDeskRequest = new GsonBuilder().create();
                String result = allHelpDeskRequest.toJson(announcementsController.getAllAnnouncements(session));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addannouncements")
    public Response addAnnouncements(@CookieParam("token") final String token,
            @FormParam("street") final String street,
            @FormParam("houseNumber") final String houseNumber,
            @FormParam("rooms") final String rooms,
            @FormParam("floor") final String floor,
            @FormParam("floors") final String floors,
            @FormParam("phone") final String phone,
            @FormParam("description") final String description) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = announcementsController.addAnnouncements(session, token, street, houseNumber,
                        rooms, floor, floors, phone, description);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("deleteannouncements")
    public Response deleteAnnouncements(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = announcementsController.deleteAnnouncements(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("search")
    public Response getAnnouncementsListByQuery(@CookieParam("token") final String token,
            @QueryParam("street") final String street,
            @QueryParam("rooms") final String rooms,
            @QueryParam("housenumber") final String houseNumber,
            @QueryParam("floor") final String floor,
            @QueryParam("floors") final String floors,
            @QueryParam("idworker") final String idWorker,
            @QueryParam("startdate") final String startdate,
            @QueryParam("enddate") final String enddate) throws CustomException {//@CookieParam("token") final String token,
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson announcements = new GsonBuilder().create();
                String result = announcements.toJson(announcementsController.getAnnouncementsListByQuery(session, token, street, houseNumber,rooms, floor, floors, idWorker, startdate, enddate));
                return ApiHelper.getResponse(result);
            }
        });
    }
}
