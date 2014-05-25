package org.progress.crm.controllers;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Apartaments;

@Singleton
public class ApartamentsController {

    @EJB
    AuthenticationManager authManager;

    public Apartaments getApartamentById(Session session, String token, String apartamentId) throws CustomException, SQLException {
        if (apartamentId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        return DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentId));
    }

    public boolean addApartament(Session session, String token, String typeOfSales,
            String cityName, String streetName, String houseNumber, String buildingNumber, String kladrId,
            String shortAddress, String apartamentLan, String apartamentLon, String rooms, String dwellingType,
            String price, String cityDistrict, String floor, String floors, String roomNumber,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent,
            String rePplanning, String idWorkerTarget, String idCustomer, String status) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        int typeOfSalesInt = typeOfSales.equals("") ? 0 : Integer.valueOf(typeOfSales);
        int roomsInt = rooms.equals("") ? 0 : Integer.valueOf(rooms);
        int dwellingTypeInt = dwellingType.equals("0") ? 0 : Integer.valueOf(dwellingType);
        int priceInt = price.equals("") ? 0 : Integer.valueOf(price);
        int cityDistrictInt = cityDistrict.equals("0") ? 0 : Integer.valueOf(cityDistrict);
        int floorInt = floor.equals("") ? 0 : Integer.valueOf(floor);
        int floorsInt = floors.equals("") ? 0 : Integer.valueOf(floors);
        int roomNumberInt = roomNumber.equals("") ? 0 : Integer.valueOf(roomNumber);
        int materialInt = material.equals("0") ? 0 : Integer.valueOf(material);
        int balconyInt = balcony.equals("0") ? 0 : Integer.valueOf(balcony);
        int loggiaInt = loggia.equals("0") ? 0 : Integer.valueOf(loggia);
        int statusInt = status.equals("") ? 0 : Integer.valueOf(status);
        int yearOfConstructionInt = yearOfConstruction.equals("") ? 0 : Integer.valueOf(yearOfConstruction);
        BigDecimal sizeApartamentBig = sizeApartament.equals("") ? BigDecimal.ZERO : new BigDecimal(sizeApartament);
        BigDecimal sizeLivingBig = sizeLiving.equals("") ? BigDecimal.ZERO : new BigDecimal(sizeLiving);
        BigDecimal sizeKitchenBig = sizeKitchen.equals("") ? BigDecimal.ZERO : new BigDecimal(sizeKitchen);

        DaoFactory.getApartamentsDao().addApartament(session, typeOfSalesInt,
                cityName, streetName, houseNumber, buildingNumber, kladrId, shortAddress,
                apartamentLan, apartamentLon, roomsInt, dwellingTypeInt, priceInt, cityDistrictInt, floorInt,
                floorsInt, roomNumberInt, materialInt, sizeApartamentBig, sizeLivingBig, sizeKitchenBig, balconyInt,
                loggiaInt, yearOfConstructionInt, description,
                Boolean.parseBoolean(pureSale), Boolean.parseBoolean(mortgage),
                Boolean.parseBoolean(exchange), Boolean.parseBoolean(rent),
                Boolean.parseBoolean(rePplanning), idWorker, Integer.valueOf(idWorkerTarget), Integer.valueOf(idCustomer), statusInt, false);
        return true;
    }

    public boolean editApartament(Session session, String token, String apartamentsId,
            String typeOfSales, String rooms, String dwellingType, String price, String cityDistrict, String floor, String floors, String roomNumber,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent, String rePlanning, String idWorkerTarget, String idCustomer, String status) throws CustomException, SQLException {
        if (apartamentsId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentsId));

        apartaments.setTypeOfSales(typeOfSales.equals("") ? 0 : Integer.valueOf(typeOfSales));
        apartaments.setRooms(rooms.equals("") ? 0 : Integer.valueOf(rooms));
        apartaments.setDwellingType(dwellingType.equals("0") ? 0 : Integer.valueOf(dwellingType));
        apartaments.setPrice(price.equals("") ? 0 : Integer.valueOf(price));
        apartaments.setCityDistrict(cityDistrict.equals("0") ? 0 : Integer.valueOf(cityDistrict));
        apartaments.setFloor(floor.equals("") ? 0 : Integer.valueOf(floor));
        apartaments.setFloors(floors.equals("") ? 0 : Integer.valueOf(floors));
        apartaments.setRoomNumber(roomNumber.equals("") ? 0 : Integer.valueOf(roomNumber));
        apartaments.setMaterial(material.equals("0") ? 0 : Integer.valueOf(material));
        apartaments.setBalcony(balcony.equals("0") ? 0 : Integer.valueOf(balcony));
        apartaments.setLoggia(loggia.equals("0") ? 0 : Integer.valueOf(loggia));
        apartaments.setStatus(status.equals("") ? 0 : Integer.valueOf(status));
        apartaments.setYearOfConstruction(yearOfConstruction.equals("") ? 0 : Integer.valueOf(yearOfConstruction));
        apartaments.setSizeApartament(sizeApartament.equals("") ? BigDecimal.ZERO : new BigDecimal(sizeApartament));
        apartaments.setSizeLiving(sizeLiving.equals("") ? BigDecimal.ZERO : new BigDecimal(sizeLiving));
        apartaments.setSizeKitchen(sizeKitchen.equals("") ? BigDecimal.ZERO : new BigDecimal(sizeKitchen));

        apartaments.setIdCustomer(Integer.valueOf(idCustomer));
        apartaments.setDescription(description);
        apartaments.setIdWorker(idWorker);
        apartaments.setIdWorkerTarget(Integer.valueOf(idWorkerTarget));
        apartaments.setLastModify(new Date());
        apartaments.setMethodOfPurchase_Exchange(Boolean.parseBoolean(exchange));
        apartaments.setMethodOfPurchase_Mortgage(Boolean.parseBoolean(mortgage));
        apartaments.setMethodOfPurchase_PureSale(Boolean.parseBoolean(pureSale));
        apartaments.setMethodOfPurchase_Rent(Boolean.parseBoolean(rent));
        apartaments.setRePplanning(Boolean.parseBoolean(rePlanning));
        DaoFactory.getApartamentsDao().modifyApartament(session, apartaments);
        return true;
    }

    public boolean removeApartament(Session session, String token, String apartamentsId) throws CustomException, SQLException {
        if (apartamentsId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentsId));
        apartaments.setDeleted(true);
        DaoFactory.getApartamentsDao().modifyApartament(session, apartaments);
        return true;
    }

    public List<Apartaments> getAllApartament(Session session, String token, String status) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session, Integer.valueOf(status));
        return apartaments;
    }
}
