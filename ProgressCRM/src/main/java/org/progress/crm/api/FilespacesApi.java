package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
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
import org.progress.crm.controllers.FilespacesController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("filespaces")
public class FilespacesApi {

    @EJB
    FilespacesController filespacesController;

    @POST
    @Path("createfilespace")
    public Response createFilespace(@CookieParam("token") final String token, @FormParam("targetuuid") final String targetUUID,
            @FormParam("type") final String type) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson rootFolderFileList = new GsonBuilder().create();
                    //FIXME add token
                    String result = rootFolderFileList.toJson(filespacesController.createFilespace(session, token, targetUUID, type));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(FilespacesApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getfilespace")
    public Response getFilespacesByTargetUUID(@CookieParam("token") final String token, @QueryParam("uuid") final String uuid) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson rootFolderFileList = new GsonBuilder().create();
                    String result = rootFolderFileList.toJson(filespacesController.getFilespacePathByTargetUUID(session, token, uuid));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(FilespacesApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
