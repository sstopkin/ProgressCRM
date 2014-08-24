package org.progress.crm.api;

import java.io.File;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.ReportGeneratorController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("report")
public class ReportGeneratorAPI {

    @EJB
    ReportGeneratorController reportGeneratorController;

    @GET
    @Path("getprice")
    @Produces("application/pdf")
    public Response getPrice(@CookieParam("token") final String token, @QueryParam("status") final String status) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    File f = reportGeneratorController.getPrice(session, token, status);
                    return ApiHelper.getResponse(f);
                } catch (CustomException ex) {
                    Logger.getLogger(ReportGeneratorAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getapartamentsreport/{id}")
    @Produces("application/pdf")
    public Response getPriceByApartamentsId(@PathParam("id") final String apartamentId,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    File f = reportGeneratorController.getPriceByApartamentsId(session, token, apartamentId);
                    return ApiHelper.getResponse(f);
                } catch (CustomException ex) {
                    Logger.getLogger(ReportGeneratorAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }
}
