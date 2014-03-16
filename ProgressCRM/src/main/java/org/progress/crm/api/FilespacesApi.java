package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    public Response createFilespace(@FormParam("filespacename") final String filespaceName) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson rootFolderFileList = new GsonBuilder().create();
                //FIXME add token
                String result = rootFolderFileList.toJson(filespacesController.createFilespace(session, filespaceName));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getfilespace")
    public Response getFilespacesByTargetUUID(@QueryParam("uuid") final String uuid) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson rootFolderFileList = new GsonBuilder().create();
                String result = rootFolderFileList.toJson(filespacesController.getFilespacePathByTargetUUID(session, uuid));
                return ApiHelper.getResponse(result);
            }
        });
    }
}
