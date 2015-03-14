package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.Date;
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
import static org.progress.crm.api.ApiHelper.ser;
import org.progress.crm.controllers.WorkersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.ParamName;
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
                    Gson apartamentById = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().registerTypeAdapter(Date.class, ser).create();
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
                    Gson apartamentById = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
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
                    Gson apartamentById = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
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
                    Gson apartamentById = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String result = apartamentById.toJson(workersController.getWorkerObjectsById(session, token, id));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(WorkersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

//    plannerAddUserModalLName').val(),
//    fname: $('#plannerAddUserModalFName').val(),
//    mname: $('#plannerAddUserModalMName').val(),
//    email: $('#plannerAddUserModalEmail').val(),
//    password: $('#plannerAddTaskModalDescription').val(),
    @POST
    @Path("addworker")
    public Response addWorker(@CookieParam("token") final String token,
            @FormParam("lname") final String workerLName,
            @FormParam("fname") final String workerFName,
            @FormParam("mname") final String workerMName,
            @FormParam("email") final String workerEmail,
            @FormParam("password") final String workerPassword
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {

                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.WORKER_LAST_NAME, workerLName);
                    map.put(ParamName.WORKER_FIRST_NAME, workerFName);
                    map.put(ParamName.WORKER_MIDDLE_NAME, workerMName);
                    map.put(ParamName.WORKER_EMAIL, workerEmail);
                    map.put(ParamName.WORKER_PASSWORD, workerPassword);
                    int result = workersController.addWorker(session, token, map);

                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AdminApi.class.getName()).log(Level.SEVERE, null, ex);
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
//                Gson apartamentById = new GsonBuilder().registerTypeAdapter(Date.class, ser)                             .create();
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
//                Gson apartamentById = new GsonBuilder().registerTypeAdapter(Date.class, ser)                             .create();
//                String result = apartamentById.toJson(customersController.getCustomersListByQuery(session, token, query));
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }
}
