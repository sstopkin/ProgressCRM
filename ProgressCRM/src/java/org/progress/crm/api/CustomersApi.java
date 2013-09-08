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
import org.progress.crm.controllers.CustomersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("customers")
public class CustomersApi {

    @EJB
    CustomersController customersController;

    @GET
    @Path("getcustomer")
    public Response getCustomerById(@QueryParam("id") final String id) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getCustomerById(session, id));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("searchcustomer")
    public Response getCustomersByString(@QueryParam("str") final String str) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getCustomerByString(session, str));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addcustomer")
    public Response addCustomer(@CookieParam("token") final String token,
            @FormParam("fname") final String fName,
            @FormParam("mname") final String mName,
            @FormParam("lname") final String lName) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersController.addCustomer(session, token, fName, mName, lName);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("editcustomer")
    public Response editCustomer(@CookieParam("token") final String token, @FormParam("id") final String id,
            @FormParam("fname") final String fName,
            @FormParam("mname") final String mName,
            @FormParam("lname") final String lName) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersController.editCustomer(session, token, id, fName, lName, mName);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("remove")
    public Response removeApartament(@CookieParam("token") final String token,
            @QueryParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersController.removeCustomer(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }
}
