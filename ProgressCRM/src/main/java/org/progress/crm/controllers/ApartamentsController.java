package org.progress.crm.controllers;

import java.math.BigDecimal;
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

    public Apartaments getApartamentById(Session session, String token, String apartamentId) throws CustomException {
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
            String rePplanning, String idWorkerTarget, String idCustomer, String status) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        int typeOfSalesInt = typeOfSales.equals("") ? 0 : Integer.valueOf(typeOfSales);
        int roomsInt = rooms.equals("") ? 0 : Integer.valueOf(rooms);
        int dwellingTypeInt = dwellingType.equals("-1") ? 0 : Integer.valueOf(dwellingType);
        int priceInt = price.equals("") ? 0 : Integer.valueOf(price);
        int cityDistrictInt = cityDistrict.equals("-1") ? 0 : Integer.valueOf(cityDistrict);
        int floorInt = floor.equals("") ? 0 : Integer.valueOf(floor);
        int floorsInt = floors.equals("") ? 0 : Integer.valueOf(floors);
        int roomNumberInt = roomNumber.equals("") ? 0 : Integer.valueOf(roomNumber);
        int materialInt = material.equals("-1") ? 0 : Integer.valueOf(material);
        int balconyInt = balcony.equals("-1") ? 0 : Integer.valueOf(balcony);
        int loggiaInt = loggia.equals("-1") ? 0 : Integer.valueOf(loggia);
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
                Boolean.parseBoolean(rePplanning), idWorker, Integer.valueOf(idWorkerTarget), Integer.valueOf(idCustomer), Integer.valueOf(status), false);
        return true;
    }

    public boolean editApartament(Session session, String token, String apartamentsId,
            String typeOfSales, String rooms, String dwellingType, String price, String cityDistrict, String floor, String floors, String roomNumber,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent, String rePlanning, String idWorkerTarget, String idCustomer, String status) throws CustomException {
        if (apartamentsId == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentsId));
        apartaments.setBalcony(Integer.valueOf(balcony));
        apartaments.setCityDistrict(Integer.valueOf(cityDistrict));
        apartaments.setIdCustomer(Integer.valueOf(idCustomer));
        apartaments.setDescription(description);
        apartaments.setFloor(Integer.valueOf(floor));
        apartaments.setFloors(Integer.valueOf(floors));
        apartaments.setRooms(Integer.valueOf(rooms));
        apartaments.setRoomNumber(Integer.valueOf(roomNumber));
        apartaments.setDwellingType(Integer.valueOf(dwellingType));
        apartaments.setIdWorker(idWorker);
        apartaments.setIdWorkerTarget(Integer.valueOf(idWorkerTarget));
        apartaments.setLastModify(new Date());
        apartaments.setLoggia(Integer.valueOf(loggia));
        apartaments.setMaterial(Integer.valueOf(material));
        apartaments.setMethodOfPurchase_Exchange(Boolean.parseBoolean(exchange));
        apartaments.setMethodOfPurchase_Mortgage(Boolean.parseBoolean(mortgage));
        apartaments.setMethodOfPurchase_PureSale(Boolean.parseBoolean(pureSale));
        apartaments.setMethodOfPurchase_Rent(Boolean.parseBoolean(rent));
        apartaments.setPrice(Integer.valueOf(price));
        apartaments.setRePplanning(Boolean.parseBoolean(rePlanning));
        apartaments.setSizeApartament(new BigDecimal(sizeApartament));
        apartaments.setSizeKitchen(new BigDecimal(sizeKitchen));
        apartaments.setSizeLiving(new BigDecimal(sizeLiving));
        apartaments.setTypeOfSales(Integer.valueOf(typeOfSales));
        apartaments.setYearOfConstruction(Integer.valueOf(yearOfConstruction));
        apartaments.setStatus(Integer.valueOf(status));
        DaoFactory.getApartamentsDao().modifyApartament(session, apartaments);
        return true;
    }

    public boolean removeApartament(Session session, String token, String apartamentsId) throws CustomException {
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

    public List<Apartaments> getAllApartament(Session session, String token, boolean prepare) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session, prepare);
        return apartaments;
    }
}
