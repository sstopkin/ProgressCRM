package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.ReportGeneratorController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("report")
public class ReportGeneratorAPI {

    @EJB
    ReportGeneratorController reportGeneratorController;

    @GET
    @Path("getprice")
    public Response getCallsByApartamentsId(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(reportGeneratorController.getPrice(session, token));
                return ApiHelper.getResponse(result);
            }
        });
    }
}
