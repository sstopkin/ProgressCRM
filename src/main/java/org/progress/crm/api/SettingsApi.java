package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import static org.progress.crm.api.ApiHelper.ser;
import org.progress.crm.controllers.SettingsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("settings")
public class SettingsApi {

    @EJB
    SettingsController settingsController;

    @GET
    public Response settings(@CookieParam("token") final String token) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson newsList = new GsonBuilder().registerTypeAdapter(Date.class, ser)
                            .create();
                    String newsJson = newsList.toJson(settingsController.getParametersByWorkerId(session, token));
                    return ApiHelper.getResponse(newsJson);
                } catch (CustomException ex) {
                    Logger.getLogger(NewsApi.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

}
