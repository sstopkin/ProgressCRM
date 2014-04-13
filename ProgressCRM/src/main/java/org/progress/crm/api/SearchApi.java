package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.SearchController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("search")
public class SearchApi {

    @EJB
    SearchController searchController;

    @GET
    @Path("search")
    public Response getCustomersRentListByQuery(@CookieParam("token") final String token,
            @QueryParam("assigned") final String assigned,
            @QueryParam("idworker") final String idWorker,
            @QueryParam("startdate") final String startdate,
            @QueryParam("enddate") final String enddate,
            @QueryParam("contains") final String contains,
            @QueryParam("type") final String type) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson customers = new GsonBuilder().create();
                String result = customers.toJson(searchController.getListByQuery(session, token, assigned, idWorker, startdate, enddate, contains, type));
                return ApiHelper.getResponse(result);
            }
        });
    }
}
