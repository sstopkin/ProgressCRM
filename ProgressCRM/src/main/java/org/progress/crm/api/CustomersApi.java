package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.progress.crm.util.ParamName;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("customers")
public class CustomersApi {

    @EJB
    CustomersController customersController;

    @GET
    @Path("getallcustomer")
    public Response getAllCustomers(@QueryParam("status") final String status,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                    String result = apartamentById.toJson(customersController.getAllCustomers(session, token, status));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getallcustomerfull")
    public Response getAllCustomersFull(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(customersController.getAllCustomers(session, token, "-1"));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getcustomer")
    public Response getCustomerById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(customersController.getCustomerById(session, token, id));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getcustomerobjects")
    public Response getCustomerObjectsById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.
                            toJson(customersController.getCustomerObjectsById(session, token, id));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("searchcustomer")
    public Response getCustomersByString(@QueryParam("str") final String str,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(customersController.getCustomerByString(session, token, str));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("addcustomer")
    public Response addCustomer(@CookieParam("token") final String token,
            @FormParam("customersFname") final String customersFname,
            @FormParam("customersMname") final String customersMname,
            @FormParam("customersLname") final String customersLname,
            @FormParam("customersDateOfBirthday") final String customersDateOfBirthday,
            @FormParam("customersSex") final String customersSex,
            @FormParam("customersEmail") final String customersEmail,
            @FormParam("customersPhone") final String customersPhone,
            @FormParam("customersAddress") final String customersAddress,
            @FormParam("customersExtra") final String customersExtra
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.CUSTOMERS_FNAME, customersFname);
                    map.put(ParamName.CUSTOMERS_LNAME, customersLname);
                    map.put(ParamName.CUSTOMERS_MNAME, customersMname);
                    map.put(ParamName.CUSTOMERS_DATE_OF_BIRTHDAY, customersDateOfBirthday);
                    map.put(ParamName.CUSTOMERS_SEX, customersSex);
                    map.put(ParamName.CUSTOMERS_PHONE, customersPhone);
                    map.put(ParamName.CUSTOMERS_EMAIL, customersEmail);
                    map.put(ParamName.CUSTOMERS_ADDRESS, customersAddress);
                    map.put(ParamName.CUSTOMERS_EXTRA, customersExtra);

                    boolean result = customersController.addCustomer(session, token, map);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("editcustomer")
    public Response editCustomer(@CookieParam("token") final String token,
            @FormParam("id") final String customerId,
            @FormParam("customersFname") final String customersFname,
            @FormParam("customersMname") final String customersMname,
            @FormParam("customersLname") final String customersLname,
            @FormParam("customersDateOfBirthday") final String customersDateOfBirthday,
            @FormParam("customersSex") final String customersSex,
            @FormParam("customersEmail") final String customersEmail,
            @FormParam("customersPhone") final String customersPhone,
            @FormParam("customersAddress") final String customersAddress,
            @FormParam("customersExtra") final String customersExtra
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.CUSTOMERS_ID, customerId);
                    map.put(ParamName.CUSTOMERS_FNAME, customersFname);
                    map.put(ParamName.CUSTOMERS_LNAME, customersLname);
                    map.put(ParamName.CUSTOMERS_MNAME, customersMname);
                    map.put(ParamName.CUSTOMERS_DATE_OF_BIRTHDAY, customersDateOfBirthday);
                    map.put(ParamName.CUSTOMERS_SEX, customersSex);
                    map.put(ParamName.CUSTOMERS_PHONE, customersPhone);
                    map.put(ParamName.CUSTOMERS_EMAIL, customersEmail);
                    map.put(ParamName.CUSTOMERS_ADDRESS, customersAddress);
                    map.put(ParamName.CUSTOMERS_EXTRA, customersExtra);

                    boolean result = customersController.editCustomer(session, token, map);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("remove")
    public Response removeApartament(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    boolean result = customersController.removeCustomer(session, token, id);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("search")
    public Response getCustomerByQuery(@CookieParam("token") final String token,
            @QueryParam("query") final String query) throws CustomException {//@CookieParam("token") final String token,
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(customersController.getCustomersListByQuery(session, token,
                            query));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(CustomersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
