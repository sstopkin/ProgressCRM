package org.progress.crm.controllers;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.exceptions.IsNotAuthenticatedException;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.util.ParamUtil;

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

    public boolean addApartament(Session session, String token, Map<String, String> map) throws CustomException, SQLException {
        int typeOfSales = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "typeOfSales");
        String cityName = map.get("cityName");
        String streetName = map.get("streetName");
        String houseNumber = map.get("houseNumber");
        String buildingNumber = map.get("buildingNumber");
        String kladrId = map.get("kladrId");
        String shortAddress = map.get("shortAddress");
        String apartamentLan = map.get("apartamentLan");
        String apartamentLon = map.get("apartamentLon");
        int rooms = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "rooms");
        int dwellingType = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "dwellingType");
        int price = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "price");
        int cityDistrict = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "cityDistrict");
        int floor = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "floor");
        int floors = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "floors");
        int roomNumber = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "roomNumber");
        int material = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "material");
        BigDecimal sizeApartament = ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, "sizeApartament");
        BigDecimal sizeLiving = ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, "sizeLiving");
        BigDecimal sizeKitchen = ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, "sizeKitchen");
        int balcony = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "balcony");
        int loggia = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "loggia");
        int yearOfConstruction = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "yearOfConstruction");
        String description = map.get("description");
        Boolean pureSale = ParamUtil.getNotEmptyBoolean(map, "pureSale");
        Boolean mortgage = ParamUtil.getNotEmptyBoolean(map, "mortgage");
        Boolean exchange = ParamUtil.getNotEmptyBoolean(map, "exchange");
        Boolean rent = ParamUtil.getNotEmptyBoolean(map, "rent");
        Boolean rePplanning = ParamUtil.getNotEmptyBoolean(map, "rePlanning");
        int idWorkerTarget = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "idWorkerTarget");
        int idCustomer = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "idCustomer");
        int status = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, "status");
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        DaoFactory.getApartamentsDao().addApartament(session, typeOfSales,
                cityName, streetName, houseNumber, buildingNumber, kladrId, shortAddress,
                apartamentLan, apartamentLon, rooms, dwellingType, price, cityDistrict, floor,
                floors, roomNumber, material, sizeApartament, sizeLiving, sizeKitchen, balcony,
                loggia, yearOfConstruction, description,
                pureSale, mortgage, exchange, rent, rePplanning, idWorker, idWorkerTarget, idCustomer, status, false);
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
        if (status == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session, Integer.valueOf(status));
        return apartaments;
    }
}
