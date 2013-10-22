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
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.HelpDeskController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("helpdesk")
public class HelpDeskApi {

    @EJB
    HelpDeskController helpDeskController;

    @GET
    @Path("getallrequest")
    public Response getAllHelpDeskRequest() throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson allHelpDeskRequest = new GsonBuilder().create();
                String result = allHelpDeskRequest.toJson(helpDeskController.getAllHelpDeskRequest(session));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addrequest")
    public Response deleteHelpDeskRequest(@CookieParam("token") final String token,
            @FormParam("request") final String request,
            @FormParam("text") final String text) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = helpDeskController.addHelpDeskRequest(session, token, request, text);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("deleterequest")
    public Response deleteHelpDeskRequest(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = helpDeskController.deleteHelpDeskRequest(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }
}
