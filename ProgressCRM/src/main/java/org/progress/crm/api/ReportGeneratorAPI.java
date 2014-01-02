package org.progress.crm.api;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Path;
import org.progress.crm.controllers.ReportGeneratorController;

@Stateless
@Path("report")
public class ReportGeneratorAPI {

    @EJB
    ReportGeneratorController reportGeneratorController;

//    @GET
//    @Path("getprice")
//    public Response getPrice(@CookieParam("token") final String token) throws CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                File f = reportGeneratorController.getPrice(session, token);
//                return ApiHelper.getResponse(f);
//            }
//        });
//    }

//    @GET
//    @Path("getapartamentsreport")
//    public Response getPriceByApartamentsId(@FormParam("id") final String apartamentId,
//            @CookieParam("token") final String token) throws CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws CustomException, SQLException {
//                File f = reportGeneratorController.getPriceByApartamentsId(session, token, apartamentId);
//                return ApiHelper.getResponse(f);
//            }
//        });
//    }
}
