package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.Date;
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
import org.progress.crm.controllers.PlannerController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("planner")
public class PlannerApi {

    @EJB
    PlannerController plannerController;

//    @GET
//    @Path("all")
//    public Response getAllPlannerTasks(@CookieParam("token") final String token,
//            @QueryParam("from") final String from,
//            @QueryParam("to") final String to,
//            @QueryParam("browser_timezone") final String timezone) throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                try {
//                    Gson tasksList = new GsonBuilder().registerTypeAdapter(Date.class, ser)                             .create();
//                    String newsJson = tasksList.toJson(plannerController.getTasks(session, token));
////                    return ApiHelper.getResponse(newsJson);
////from=1362070800000&to=1364749200000&browser_timezone=Asia%2FOmsk
//                    //1363197600
//                    //Wed, 13 Mar 2013 18:00:00 GMT
//
//                    return ApiHelper.getResponse(responce);
//                } catch (CustomException ex) {
//                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
//                    return ApiHelper.getResponse(ex);
//                }
//            }
//        });
//    }
//
    @GET
    @Path("all")
    public Response getAllPlannerTasksByWorker(
            @CookieParam("token") final String token,
            @QueryParam("from") final String from,
            @QueryParam("to") final String to,
            @QueryParam("browser_timezone") final String timezone) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson tasksList = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String newsJson = tasksList.toJson(plannerController.getTasksByWorker(session, token, from, to, timezone));
                    return ApiHelper.getResponse(newsJson.replace("\"Class\"", "\"class\""));
                } catch (CustomException ex) {
                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("deletetask")
    public Response deletePlannerTasks(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    boolean result = plannerController.deleteTaskById(session, token, id);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("addtask")
    public Response addPlannerTask(@CookieParam("token") final String token,
            @FormParam("tasktype") final String taskType,
            @FormParam("targetobjectuuid") final String targetobjectuuid,
            @FormParam("taskclass") final String taskClass,
            @FormParam("title") final String taskTitle,
            @FormParam("description") final String taskDescription,
            @FormParam("startdate") final String taskStartDate,
            @FormParam("enddate") final String taskEndDate)
            throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    boolean result = plannerController.addTask(session, token, taskClass, targetobjectuuid, taskTitle,
                            taskDescription, taskStartDate, taskEndDate);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
