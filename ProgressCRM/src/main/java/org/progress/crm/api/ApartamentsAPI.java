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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.ApartamentsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.TransactionService;

@Stateless
@Path("apartament")
public class ApartamentsAPI {

    @EJB
    ApartamentsController apartamentsController;

    @GET
    @Path("getapartament")
    public Response getApartamentById(@QueryParam("id") final String id,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson apartamentById = new GsonBuilder().create();
                String result = apartamentById.toJson(apartamentsController.getApartamentById(session, token, id));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getallapartament")
    public Response getAllApartaments(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson allApartament = new GsonBuilder().create();
                String result = allApartament.toJson(apartamentsController.getAllApartament(session, token, false));
                return ApiHelper.getResponse(result);
            }
        });
    }

    @GET
    @Path("getallapartamentзprepare")
    public Response getAllApartamentsPrepare(@CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                Gson allApartament = new GsonBuilder().create();
                String result = allApartament.toJson(apartamentsController.getAllApartament(session, token, true));
                return ApiHelper.getResponse(result);
            }
        });
    }

//            $.get("api/auth/info", function(data) {
//            JSON.parse(data, function(key, value) {
//                if (key == "email")
//                    $("#profileEmail").html(value);
//                if (key == "fName")
//                    $("#profileFName").html(value);
//                if (key == "lName")
//                    $("#profileLName").html(value);
//                if (key == "mName") {
//                    $("#profileMName").html(value);
//                }
//
//            });
//        });
//    @FormParam("idcustomer") final String idCustomer,
//            @FormParam("idtypeoftransaction") final String idTypeOfTransaction,
//            @FormParam("idstreet") final String idStreet,
//            @FormParam("cost") final String cost
    @POST
    @Path("addapartament")
    public Response addApartament(@CookieParam("token") final String token,
            @FormParam("typeofsales") final String typeOfSales,
            @FormParam("cityName") final String cityName,
            @FormParam("streetName") final String streetName,
            @FormParam("houseNumber") final String houseNumber,
            @FormParam("buildingNumber") final String buildingNumber,
            @FormParam("kladrId") final String kladrId,
            @FormParam("shortAddress") final String shortAddress,
            @FormParam("apartamentLan") final String apartamentLan,
            @FormParam("apartamentLon") final String apartamentLon,
            @FormParam("rooms") final String rooms,
            @FormParam("dwellingType") final String dwellingType,
            @FormParam("price") final String price,
            @FormParam("citydistrict") final String cityDistrict,
            @FormParam("floor") final String floor,
            @FormParam("floors") final String floors,
            @FormParam("roomnumber") final String roomNumber,
            @FormParam("material") final String material,
            @FormParam("sizeapartament") final String sizeApartament,
            @FormParam("sizeliving") final String sizeLiving,
            @FormParam("sizekitchen") final String sizeKitchen,
            @FormParam("balcony") final String balcony,
            @FormParam("loggia") final String loggia,
            @FormParam("yearofconstruction") final String yearOfConstruction,
            @FormParam("description") final String description,
            @FormParam("puresale") final String pureSale,
            @FormParam("mortgage") final String mortgage,
            @FormParam("exchange") final String exchange,
            @FormParam("rent") final String rent,
            @FormParam("replanning") final String rePlanning,
            @FormParam("idWorkerTarget") final String idWorkerTarget,
            @FormParam("idCustomer") final String idCustomer,
            @FormParam("status") final String status) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = apartamentsController.addApartament(session, token,
                        typeOfSales, cityName, streetName, houseNumber, buildingNumber,
                        kladrId, shortAddress, apartamentLan, apartamentLon, rooms, dwellingType,
                        price, cityDistrict, floor, floors, roomNumber,
                        material, sizeApartament, sizeLiving, sizeKitchen,
                        balcony, loggia, yearOfConstruction, description,
                        pureSale, mortgage, exchange, rent, rePlanning, idWorkerTarget, idCustomer, status);
                return ApiHelper.getResponse(result);
            }
        });
    }

    @POST
    @Path("editapartament")
    public Response editApartament(@CookieParam("token") final String token,
            @FormParam("id") final String id,
            @FormParam("typeofsales") final String typeOfSales,
            @FormParam("rooms") final String rooms,
            @FormParam("dwellingType") final String dwellingType,
            @FormParam("price") final String price,
            @FormParam("citydistrict") final String cityDistrict,
            @FormParam("floor") final String floor,
            @FormParam("floors") final String floors,
            @FormParam("roomnumber") final String roomNumber,
            @FormParam("material") final String material,
            @FormParam("sizeapartament") final String sizeApartament,
            @FormParam("sizeliving") final String sizeLiving,
            @FormParam("sizekitchen") final String sizeKitchen,
            @FormParam("balcony") final String balcony,
            @FormParam("loggia") final String loggia,
            @FormParam("yearofconstruction") final String yearOfConstruction,
            @FormParam("description") final String description,
            @FormParam("puresale") final String pureSale,
            @FormParam("mortgage") final String mortgage,
            @FormParam("exchange") final String exchange,
            @FormParam("rent") final String rent,
            @FormParam("replanning") final String rePlanning,
            @FormParam("idWorkerTarget") final String idWorkerTarget,
            @FormParam("idCustomer") final String idCustomer,
            @FormParam("status") final String status) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = apartamentsController.editApartament(session, token, id,
                        typeOfSales, rooms, dwellingType, price, cityDistrict, floor, floors, roomNumber,
                        material, sizeApartament, sizeLiving, sizeKitchen,
                        balcony, loggia, yearOfConstruction, description,
                        pureSale, mortgage, exchange, rent, rePlanning, idWorkerTarget, idCustomer, status);
                return ApiHelper.getResponse(false);
            }
        });
    }

    @POST
    @Path("remove")
    public Response removeApartament(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws CustomException, SQLException {
                boolean result = apartamentsController.removeApartament(session, token, id);
                return ApiHelper.getResponse(result);
            }
        });
    }
}
