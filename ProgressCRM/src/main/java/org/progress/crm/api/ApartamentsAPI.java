package org.progress.crm.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.hibernate.Session;
import org.progress.crm.controllers.ApartamentsController;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.util.Command;
import org.progress.crm.util.ParamName;
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
            public Response execute(Session session) throws SQLException {
                try {
                    Gson apartamentById = new GsonBuilder().create();
                    String result = apartamentById.toJson(apartamentsController.getApartamentById(session, token, id));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(ApartamentsAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @GET
    @Path("getallapartament")
    public Response getAllApartaments(@QueryParam("status") final String status,
            @CookieParam("token") final String token) throws CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    Gson allApartament = new GsonBuilder().create();
                    String result = allApartament.toJson(apartamentsController.getAllApartament(session, token, status));
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(ApartamentsAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

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
            public Response execute(Session session) throws SQLException {
                try {

                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.TYPE_OF_SALES, typeOfSales);
                    map.put(ParamName.CITY_NAME, cityName);
                    map.put(ParamName.STREET_NAME, streetName);
                    map.put(ParamName.HOUSE_NUMBER, houseNumber);
                    map.put(ParamName.BUILDING_NUMBER, buildingNumber);
                    map.put(ParamName.KLADR_ID, kladrId);
                    map.put(ParamName.SHORT_ADDRESS, shortAddress);
                    map.put(ParamName.APARTAMENT_LAN, apartamentLan);
                    map.put(ParamName.APARTAMENT_LON, apartamentLon);
                    map.put(ParamName.ROOMS, rooms);
                    map.put(ParamName.DWELLING_TYPE, dwellingType);
                    map.put(ParamName.PRICE, price);
                    map.put(ParamName.CITY_DISTRICT, cityDistrict);
                    map.put(ParamName.FLOOR, floor);
                    map.put(ParamName.FLOORS, floors);
                    map.put(ParamName.ROOM_NUMBER, roomNumber);
                    map.put(ParamName.MATERIAL, material);
                    map.put(ParamName.SIZE_APARTAMENT, sizeApartament);
                    map.put(ParamName.SIZE_LIVING, sizeLiving);
                    map.put(ParamName.SIZE_KITCHEN, sizeKitchen);
                    map.put(ParamName.BALCONY, balcony);
                    map.put(ParamName.LOGGIA, loggia);
                    map.put(ParamName.YEAR_OF_CONSTRUCTION, yearOfConstruction);
                    map.put(ParamName.DESCRIPTION, description);
                    map.put(ParamName.PURE_SALE, pureSale);
                    map.put(ParamName.MORTGAGE, mortgage);
                    map.put(ParamName.EXCHANGE, exchange);
                    map.put(ParamName.RENT, rent);
                    map.put(ParamName.RE_PLANNING, rePlanning);
                    map.put(ParamName.ID_WORKER_TARGET, idWorkerTarget);
                    map.put(ParamName.ID_CUSTOMER, idCustomer);
                    map.put(ParamName.STATUS, status);

                    boolean result = apartamentsController.addApartament(session, token, map);

                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(ApartamentsAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
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
            public Response execute(Session session) throws SQLException {
                try {

                    Map<String, String> map = new HashMap<>();
                    map.put(ParamName.TYPE_OF_SALES, typeOfSales);
//                    map.put(ParamName.CITY_NAME, cityName);
//                    map.put(ParamName.STREET_NAME, streetName);
//                    map.put(ParamName.HOUSE_NUMBER, houseNumber);
//                    map.put(ParamName.BUILDING_NUMBER, buildingNumber);
//                    map.put(ParamName.KLADR_ID, kladrId);
//                    map.put(ParamName.SHORT_ADDRESS, shortAddress);
//                    map.put(ParamName.APARTAMENT_LAN, apartamentLan);
//                    map.put(ParamName.APARTAMENT_LON, apartamentLon);
                    map.put(ParamName.ROOMS, rooms);
                    map.put(ParamName.DWELLING_TYPE, dwellingType);
                    map.put(ParamName.PRICE, price);
                    map.put(ParamName.CITY_DISTRICT, cityDistrict);
                    map.put(ParamName.FLOOR, floor);
                    map.put(ParamName.FLOORS, floors);
                    map.put(ParamName.ROOM_NUMBER, roomNumber);
                    map.put(ParamName.MATERIAL, material);
                    map.put(ParamName.SIZE_APARTAMENT, sizeApartament);
                    map.put(ParamName.SIZE_LIVING, sizeLiving);
                    map.put(ParamName.SIZE_KITCHEN, sizeKitchen);
                    map.put(ParamName.BALCONY, balcony);
                    map.put(ParamName.LOGGIA, loggia);
                    map.put(ParamName.YEAR_OF_CONSTRUCTION, yearOfConstruction);
                    map.put(ParamName.DESCRIPTION, description);
                    map.put(ParamName.PURE_SALE, pureSale);
                    map.put(ParamName.MORTGAGE, mortgage);
                    map.put(ParamName.EXCHANGE, exchange);
                    map.put(ParamName.RENT, rent);
                    map.put(ParamName.RE_PLANNING, rePlanning);
                    map.put(ParamName.ID_WORKER_TARGET, idWorkerTarget);
                    map.put(ParamName.ID_CUSTOMER, idCustomer);
                    map.put(ParamName.STATUS, status);
                    map.put(ParamName.APARTAMENTS_ID, id);

                    boolean result = apartamentsController.editApartament(session, token, map);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(ApartamentsAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

    @POST
    @Path("remove")
    public Response removeApartament(@CookieParam("token") final String token,
            @FormParam("id") final String id) throws SQLException, CustomException {
        return TransactionService.runInScope(new Command<Response>() {
            @Override
            public Response execute(Session session) throws SQLException {
                try {
                    boolean result = apartamentsController.removeApartament(session, token, id);
                    return ApiHelper.getResponse(result);
                } catch (CustomException ex) {
                    Logger.getLogger(ApartamentsAPI.class.getName()).log(Level.SEVERE, null, ex);
                    return ApiHelper.getResponse(ex);
                }
            }
        });
    }

//    @GET
//    @Path("genyml")
//    public Response getApartamentsYML() throws CustomException {
//        return TransactionService.runInScope(new Command<Response>() {
//            @Override
//            public Response execute(Session session) throws SQLException {
//                try {
//                    Gson apartamentById = new GsonBuilder().create();
//                    String result = apartamentById.toJson(apartamentsController.getApartamentsYML(session));
//                    return ApiHelper.getResponse(result);
//                } catch (CustomException ex) {
//                    Logger.getLogger(ApartamentsAPI.class.getName()).log(Level.SEVERE, null, ex);
//                    return ApiHelper.getResponse(ex);
//                }
//            }
//        });
//    }
}
