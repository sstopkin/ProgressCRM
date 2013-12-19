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
import org.progress.crm.controllers.CustomersRentController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("customersrent")
public class CustomersRentApi {

    @EJB
    CustomersRentController customersRentController;

    @GET
    @Path("getallcustomersrent")
    public Response getAllCustomersRent(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson allHelpDeskRequest = new GsonBuilder().create();
                String result = allHelpDeskRequest.toJson(customersRentController.getAllCustomersRent(session, token));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addcustomersrent")
    public Response addCustomersRent(@CookieParam("token") final String token,
            @FormParam("status") final String status,
            @FormParam("assigned") final String assigned,
            @FormParam("idcustomer") final String idcustomer,
            @FormParam("description") final String description) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersRentController.addCustomersRent(session, token,
                        status, assigned, description, idcustomer);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("editcustomersrent")
    public Response editCustomersRent(@CookieParam("token") final String token,
            @FormParam("id") final String id,
            @FormParam("status") final String status,
            @FormParam("assigned") final String assigned,
            @FormParam("idcustomer") final String idcustomer,
            @FormParam("description") final String description) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersRentController.editCustomersRentById(session, token, id,
                        status, assigned, description, idcustomer);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("deletecustomersrent")
    public Response deleteCustomersRent(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersRentController.deleteCustomersRent(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("search")
    public Response getCustomersRentListByQuery(@CookieParam("token") final String token,
            @QueryParam("assigned") final String assigned,
            @QueryParam("idworker") final String idWorker,
            @QueryParam("startdate") final String startdate,
            @QueryParam("enddate") final String enddate,
            @QueryParam("status") final String status) throws CustomException {//@CookieParam("token") final String token,
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson customers = new GsonBuilder().create();
                String result = customers.toJson(customersRentController.getCustomersRentListByQuery(session, token, assigned, idWorker, startdate, enddate, status));
                return ApiHelper.getResponse(result);
            }
        });
    }
}
