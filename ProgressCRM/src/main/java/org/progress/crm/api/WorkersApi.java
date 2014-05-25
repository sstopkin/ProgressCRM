package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.WorkersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("workers")
public class WorkersApi {

    @EJB
    WorkersController workersController;

    @GET
    @Path("getallworkers")
    public Response getAllWorkers(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                    String result = apartamentById.toJson(workersController.getAllWorkersToAdmin(session, token));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(WorkersApi.class.getName()).log(Level.SEVERE, null, ex);
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
                    String result = apartamentById.toJson(workersController.getAllWorkersToAdmin(session, token));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(WorkersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getworker")
    public Response getWorkerById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(workersController.getWorkerById(session, token, id));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(WorkersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getworkerobjects")
    public Response getWorkerObjectsById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(workersController.getWorkerObjectsById(session, token, id));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(WorkersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

//    @GET
//    @Path("getcustomer")
//    public Response getCustomerById(@QueryParam("id") final String id,
//            @CookieParam("token") final String token) throws CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                Gson apartamentById = new GsonBuilder().create();
//                String result = apartamentById.toJson(workersController.(session, token, id));
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }
//    @POST
//    @Path("addcustomer")
//    public Response addCustomer(@CookieParam("token") final String token,
//            @FormParam("customersFname") final String customersFname,
//            @FormParam("customersMname") final String customersMname,
//            @FormParam("customersLname") final String customersLname,
//            @FormParam("customersYearOfBirthday") final String customersYearOfBirthday,
//            @FormParam("customersMonthOfBirthday") final String customersMonthOfBirthday,
//            @FormParam("customersDayOfBirthday") final String customersDayOfBirthday,
//            @FormParam("customersSex") final String customersSex,
//            @FormParam("customersEmail") final String customersEmail,
//            @FormParam("customersPhone") final String customersPhone,
//            @FormParam("customersAddress") final String customersAddress,
//            @FormParam("customersExtra") final String customersExtra
//    ) throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                boolean result = customersController.addCustomer(session,
//                        token,
//                        customersFname,
//                        customersLname,
//                        customersMname,
//                        customersMonthOfBirthday,
//                        customersDayOfBirthday,
//                        customersYearOfBirthday,
//                        customersSex,
//                        customersPhone,
//                        customersEmail,
//                        customersAddress,
//                        customersExtra);
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }
//
//    @POST
//    @Path("editcustomer")
//    public Response editCustomer(@CookieParam("token") final String token, @FormParam("id") final String id,
//            @FormParam("customersFname") final String customersFname,
//            @FormParam("customersMname") final String customersMname,
//            @FormParam("customersLname") final String customersLname,
//            @FormParam("customersYearOfBirthday") final String customersYearOfBirthday,
//            @FormParam("customersMonthOfBirthday") final String customersMonthOfBirthday,
//            @FormParam("customersDayOfBirthday") final String customersDayOfBirthday,
//            @FormParam("customersSex") final String customersSex,
//            @FormParam("customersEmail") final String customersEmail,
//            @FormParam("customersPhone") final String customersPhone,
//            @FormParam("customersAddress") final String customersAddress,
//            @FormParam("customersExtra") final String customersExtra
//    ) throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                boolean result = customersController.editCustomer(session, token, id,
//                        customersFname,
//                        customersLname,
//                        customersMname,
//                        customersMonthOfBirthday,
//                        customersDayOfBirthday,
//                        customersYearOfBirthday,
//                        customersSex,
//                        customersPhone,
//                        customersEmail,
//                        customersAddress,
//                        customersExtra);
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }
//
//    @POST
//    @Path("remove")
//    public Response removeApartament(@CookieParam("token") final String token,
//            @QueryParam("id") final String id) throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                boolean result = customersController.removeCustomer(session, token, id);
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }
//
//    @GET
//    @Path("search")
//    public Response getCustomerByQuery(@CookieParam("token") final String token,
//            @QueryParam("query") final String query) throws CustomException {//@CookieParam("token") final String token,
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                Gson apartamentById = new GsonBuilder().create();
//                String result = apartamentById.toJson(customersController.getCustomersListByQuery(session, token, query));
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }
}
