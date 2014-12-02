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
import javax.ws.rs.PathParam;
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

    @GET
    @Path("all")
    public Response getAllPlannerTasksByWorker(
            @CookieParam("token") final String token,
            @QueryParam("browser_timezone") final String timezone) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson tasksList = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    return ApiHelper.getResponse(tasksList.toJson(plannerController.getTasks(session, token, "", timezone)));
                } catch (CustomException ex) {
                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("uuid/{path:.*}")
    public Response getAllPlannerTasksByWorker(
            @PathParam("path") final String uuid,
            @CookieParam("token") final String token,
            @QueryParam("browser_timezone") final String timezone) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson tasksList = new GsonBuilder().create();
                    return ApiHelper.getResponse(tasksList.toJson(plannerController.getTasks(session, token, uuid, timezone)));
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
