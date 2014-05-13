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
    @Path("getallcustomer")
    public Response getAllCustomers(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                String result = apartamentById.toJson(customersController.getAllCustomers(session, token));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getallcustomerfull")
    public Response getAllCustomersFull(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getAllCustomers(session, token));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getcustomer")
    public Response getCustomerById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getCustomerById(session, token, id));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getcustomerwithinfo")
    public Response getCustomerWithInfoById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getCustomerWithInfoById(session, token, id));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("searchcustomer")
    public Response getCustomersByString(@QueryParam("str") final String str,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getCustomerByString(session, token, str));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addcustomer")
    public Response addCustomer(@CookieParam("token") final String token,
            @FormParam("customersFname") final String customersFname,
            @FormParam("customersMname") final String customersMname,
            @FormParam("customersLname") final String customersLname,
            @FormParam("customersYearOfBirthday") final String customersYearOfBirthday,
            @FormParam("customersMonthOfBirthday") final String customersMonthOfBirthday,
            @FormParam("customersDayOfBirthday") final String customersDayOfBirthday,
            @FormParam("customersSex") final String customersSex,
            @FormParam("customersEmail") final String customersEmail,
            @FormParam("customersPhone") final String customersPhone,
            @FormParam("customersAddress") final String customersAddress,
            @FormParam("customersExtra") final String customersExtra
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersController.addCustomer(session,
                        token,
                        customersFname,
                        customersLname,
                        customersMname,
                        customersMonthOfBirthday,
                        customersDayOfBirthday,
                        customersYearOfBirthday,
                        customersSex,
                        customersPhone,
                        customersEmail,
                        customersAddress,
                        customersExtra);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("editcustomer")
    public Response editCustomer(@CookieParam("token") final String token, @FormParam("id") final String id,
            @FormParam("customersFname") final String customersFname,
            @FormParam("customersMname") final String customersMname,
            @FormParam("customersLname") final String customersLname,
            @FormParam("customersYearOfBirthday") final String customersYearOfBirthday,
            @FormParam("customersMonthOfBirthday") final String customersMonthOfBirthday,
            @FormParam("customersDayOfBirthday") final String customersDayOfBirthday,
            @FormParam("customersSex") final String customersSex,
            @FormParam("customersEmail") final String customersEmail,
            @FormParam("customersPhone") final String customersPhone,
            @FormParam("customersAddress") final String customersAddress,
            @FormParam("customersExtra") final String customersExtra
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = customersController.editCustomer(session, token, id,
                        customersFname,
                        customersLname,
                        customersMname,
                        customersMonthOfBirthday,
                        customersDayOfBirthday,
                        customersYearOfBirthday,
                        customersSex,
                        customersPhone,
                        customersEmail,
                        customersAddress,
                        customersExtra);
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

    @GET
    @Path("search")
    public Response getCustomerByQuery(@CookieParam("token") final String token,
            @QueryParam("query") final String query) throws CustomException {//@CookieParam("token") final String token,
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(customersController.getCustomersListByQuery(session, token, query));
                return ApiHelper.getResponse(result);
            }
        });
    }

}
