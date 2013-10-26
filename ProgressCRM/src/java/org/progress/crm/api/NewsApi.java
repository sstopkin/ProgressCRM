package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.NewsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("news")
public class NewsApi {

    @EJB
    NewsController newsController;

    @GET
    public Response news() throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson newsList = new GsonBuilder().create();
                String newsJson = newsList.toJson(newsController
                        .getNews(session));
                return ApiHelper.getResponse(newsJson);
            }
        });
    }

    @POST
    @Path("deletenews")
    public Response deleteHelpDeskRequest(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = newsController.deleteNewsById(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("addnews")
    public Response editCourse(@CookieParam("token") final String token, @FormParam("id") final String id,
            @FormParam("text") final String text,
            @FormParam("header") final String header)
            throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = newsController.addNews(session, token, text, header);
                return ApiHelper.getResponse(result);
            }
        });
    }
}
