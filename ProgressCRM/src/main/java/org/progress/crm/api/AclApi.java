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
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import static org.progress.crm.api.ApiHelper.ser;
import org.progress.crm.controllers.AclController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.ACLEntry;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("acl")
public class AclApi {

    @EJB
    AclController aclController;

    @GET
    @Path("getacllist")
    public Response getAclList(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    if (token == null) {
                        throw new IsNotAuthenticatedException();
                    }
                    Gson aclList = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String result = aclList.toJson(aclController.getAllAclList(session));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AclApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("setacllist")
    public Response setAclList(@CookieParam("token") final String token, @FormParam("acllist") final String aclList) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    if (token == null) {
                        throw new IsNotAuthenticatedException();
                    }
                    Gson responce = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    Gson gson = new Gson();
                    ACLEntry[] data = gson.fromJson(aclList, ACLEntry[].class);
                    String result = responce.toJson(aclController.setAllAclList(session, data));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AclApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getentities")
    public Response getEntitiesList(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    if (token == null) {
                        throw new IsNotAuthenticatedException();
                    }
                    Gson aclList = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String result = aclList.toJson(aclController.getAllEntitiesList(session));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AclApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getaccesscategories")
    public Response getAccessCategoriesList(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    if (token == null) {
                        throw new IsNotAuthenticatedException();
                    }
                    Gson aclList = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String result = aclList.toJson(aclController.getAllAccessCategoriesList(session));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AclApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getaccesstypes")
    public Response getAccessTypesList(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    if (token == null) {
                        throw new IsNotAuthenticatedException();
                    }
                    Gson aclList = new GsonBuilder().registerTypeAdapter(Date.class, ser).create();
                    String result = aclList.toJson(aclController.getAllAccessTypesList(session));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(AclApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

//    @POST
//    @Path("addcall")
//    public Response addCall(@CookieParam("token") final String token,
//            @FormParam("objectUUID") final String objectUUID,
//            @FormParam("incomingPhoneNumber") final String incomingPhoneNumber,
//            @FormParam("description") final String description) throws SQLException, CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                try {
//                    boolean result = callsController.addCallsByObjectUUID(session, token, objectUUID, incomingPhoneNumber, description);
//                    return ApiHelper.getResponse(result);
//                } catch (CustomException ex) {
//                    Logger.getLogger(CallsApi.class.getName()).log(Level.SEVERE, null, ex);
//                    return ApiHelper.getResponse(ex);
//                }
//            }
//        });
//    }
}
