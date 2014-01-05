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
import org.progress.crm.controllers.WorkersController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("admin")
public class AdminApi {

    @EJB
    WorkersController workersController;
//    @EJB
//    TaskEvaluatedController taskEvaluatedController;
//    @EJB
//    CourseController courseController;
//    @EJB
//    TaskController taskController;
//    @EJB
//    ScoreController scoreController;

//    @GET
//    @Path("getunevaluated")
//    public Response getUnevaluatedTask(@CookieParam("token") final String token)
//            throws CustomException, SQLException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                Gson unevaluated = new GsonBuilder().create();
//                String result = unevaluated.toJson(taskEvaluatedController
//                        .getNotEvaluatedTasksList(session, token));
//                return ApiHelper.getResponse(result);
//            }
//        });
//    }

//    @POST
//    @Path("evaluatesuccess")
//    public Response EvaluateSuccessTask(@CookieParam("token") final String token,
//            @FormParam("id") final String id) throws CustomException, SQLException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                scoreController.checkTaskByAdmin(session, token, id, true);
//                return ApiHelper.getResponse(true);
//            }
//        });
//    }

//    @POST
//    @Path("evaluatefail")
//    public Response EvaluateFailTask(@CookieParam("token") final String token,
//            @FormParam("id") final String id) throws CustomException, SQLException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                scoreController.checkTaskByAdmin(session, token, id, false);
//                return ApiHelper.getResponse(true);
//            }
//        });
//    }

    @GET
    @Path("getallusers")
    public Response getAllUsers(@CookieParam("token") final String token) throws
            CustomException, SQLException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson allUsers = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                        .create();
                String result = allUsers.toJson(workersController.getAllUsersToAdmin(session, token));
                return ApiHelper.getResponse(result);
            }
        });
    }

//    @POST
//    @Path("banuser")
//    public Response banUserById(@CookieParam("token") final String token, @FormParam("id") final String id)
//            throws CustomException, SQLException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                workersController.setActivityUserById(session, token, id, false);
//                return ApiHelper.getResponse(true);
//            }
//        });
//    }

//    @POST
//    @Path("unbanuser")
//    public Response unBanUserById(@CookieParam("token") final String token, @FormParam("id") final String id)
//            throws CustomException, SQLException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                workersController.setActivityUserById(session, token, id, true);
//                return ApiHelper.getResponse(true);
//            }
//        });
//    }

//    @POST
//    @Path("chngpoints")
//    public Response changeUserPointsById(@CookieParam("token") final String token,
//            @FormParam("id") final String id, @FormParam("points") final String points)
//            throws CustomException, SQLException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                profileController.changeUserPiontsById(session, token, id, points);
//                return ApiHelper.getResponse(true);
//            }
//        });
//    }

//    @GET
//    @Path("getnonapprovedcourses")
//    public Response getNonApprovedCourses(@CookieParam("token") final String token)
//            throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                Gson courseById = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//                String coursesJson = courseById.toJson(courseController
//                        .getCoursesForModeration(session, token));
//                return ApiHelper.getResponse(coursesJson);
//            }
//        });
//    }
//
//    @GET
//    @Path("getnonapprovedtasks")
//    public Response getNonApprovedTasks(@CookieParam("token") final String token)
//            throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                Gson courseById = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//                String coursesJson = courseById.toJson(taskController
//                        .getTasksForModeration(session, token));
//                return ApiHelper.getResponse(coursesJson);
//            }
//        });
//    }
}
