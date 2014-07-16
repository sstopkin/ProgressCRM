package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import org.progress.crm.controllers.PlannerController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Apartaments;
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
//                    Gson tasksList = new GsonBuilder().create();
//                    String newsJson = tasksList.toJson(plannerController.getTasks(session, token));
////                    return ApiHelper.getResponse(newsJson);
////from=1362070800000&to=1364749200000&browser_timezone=Asia%2FOmsk
//                    String responce = "{\n"
//                            + "	\"success\": 1,\n"
//                            + "	\"result\": [\n"
//                            + "		{\n"
//                            + "			\"id\": \"293\",\n"
//                            + "			\"title\": \"This is warning class event with very long title to check how it fits to evet in day view\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-warning\",\n"
//                            + "			\"start\": \"1362938400000\",\n"
//                            + "			\"end\":   \"1363197686300\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"256\",\n"
//                            + "			\"title\": \"Event that ends on timeline\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-warning\",\n"
//                            + "			\"start\": \"1363155300000\",\n"
//                            + "			\"end\":   \"1363227600000\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"276\",\n"
//                            + "			\"title\": \"Short day event\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-success\",\n"
//                            + "			\"start\": \"1363245600000\",\n"
//                            + "			\"end\":   \"1363252200000\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"294\",\n"
//                            + "			\"title\": \"This is information class \",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-info\",\n"
//                            + "			\"start\": \"1363111200000\",\n"
//                            + "			\"end\":   \"1363284086400\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"297\",\n"
//                            + "			\"title\": \"This is success event\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-success\",\n"
//                            + "			\"start\": \"1363234500000\",\n"
//                            + "			\"end\":   \"1363284062400\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"54\",\n"
//                            + "			\"title\": \"This is simple event\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"\",\n"
//                            + "			\"start\": \"1363712400000\",\n"
//                            + "			\"end\":   \"1363716086400\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"532\",\n"
//                            + "			\"title\": \"This is inverse event\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-inverse\",\n"
//                            + "			\"start\": \"1364407200000\",\n"
//                            + "			\"end\":   \"1364493686400\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"548\",\n"
//                            + "			\"title\": \"This is special event\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-special\",\n"
//                            + "			\"start\": \"1363197600000\",\n"
//                            + "			\"end\":   \"1363629686400\"\n"
//                            + "		},\n"
//                            + "		{\n"
//                            + "			\"id\": \"295\",\n"
//                            + "			\"title\": \"Event 3\",\n"
//                            + "			\"url\": \"http://www.example.com/\",\n"
//                            + "			\"class\": \"event-important\",\n"
//                            + "			\"start\": \"1364320800000\",\n"
//                            + "			\"end\":   \"1364407286400\"\n"
//                            + "		}\n"
//                            + "	]\n"
//                            + "}";
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
    public class event{
            public int id;
            public String title;
            public String url;
            public String Class;
            public String start;
            public String end;

        public event(int id, String title, String url, String Class, String start, String end) {
            this.id = id;
            this.title = title;
            this.url = url;
            this.Class = Class;
            this.start = start;
            this.end = end;
        }
    }

    public class succ {

        public String success;
        public List<event> result;

        public succ() {
            this.success = "1";
            this.result = new ArrayList();
        }
        
        public void ret(){
            this.result.add(new event(295, "2", "3", "event-success", "1364320800000", "1364407286400"));
        }
    }

    @GET
    @Path("all")
    public Response getAllPlannerTasksByWorker(@CookieParam("token") final String token) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
//                try {
                Gson tasksList = new GsonBuilder().create();
                succ s=new succ();
                s.ret();

                String newsJson = tasksList.toJson(s);//plannerController.getTasksByWorker(session, token)
                return ApiHelper.getResponse(newsJson.replace("\"Class\"", "\"class\""));
//                } catch (CustomException ex) {
//                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
//                    return ApiHelper.getResponse(ex);
//                }
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
            @FormParam("taskid") final String taskId,
            @FormParam("description") final String description,
            @FormParam("taskdate") final String taskDate)
            throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    boolean result = plannerController.addTask(session, token, taskType, taskId, description, taskDate);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(PlannerApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
