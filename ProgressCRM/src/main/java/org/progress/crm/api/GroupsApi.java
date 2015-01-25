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
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import static org.progress.crm.api.ApiHelper.ser;
import org.progress.crm.controllers.GroupsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.ParamName;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("groups")
public class GroupsApi {

    @EJB
    GroupsController groupsController;

    @GET
    @Path("getallgroups")
    public Response getAllWorkerGroups(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson workersGroup = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String result = workersGroup.toJson(groupsController.getAllGroups(session, token));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(WorkersApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("addgroup")
    public Response addGroup(@CookieParam("token") final String token,
            @FormParam("groupname") final String groupName
    ) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.GROUP_NAME, groupName);
                    boolean result = groupsController.addGroup(session, token, map);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AdminApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
