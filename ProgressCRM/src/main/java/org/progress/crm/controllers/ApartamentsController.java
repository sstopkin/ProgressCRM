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
import org.progress.crm.util.ParamName;
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
        String cityName = map.get(ParamName.CITY_NAME);
        String streetName = map.get(ParamName.STREET_NAME);
        String houseNumber = map.get(ParamName.HOUSE_NUMBER);
        String buildingNumber = map.get(ParamName.BUILDING_NUMBER);
        String kladrId = map.get(ParamName.KLADR_ID);
        String shortAddress = map.get(ParamName.SHORT_ADDRESS);
        String apartamentLan = map.get(ParamName.APARTAMENT_LAN);
        String apartamentLon = map.get(ParamName.APARTAMENT_LON);
        int typeOfSales = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.TYPE_OF_SALES);
        int rooms = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ROOMS);
        int dwellingType = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.DWELLING_TYPE);
        int price = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.PRICE);
        int cityDistrict = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.CITY_DISTRICT);
        int floor = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.FLOOR);
        int floors = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.FLOORS);
        int roomNumber = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ROOM_NUMBER);
        int material = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.MATERIAL);
        BigDecimal sizeApartament = ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, ParamName.SIZE_APARTAMENT);
        BigDecimal sizeLiving = ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, ParamName.SIZE_LIVING);
        BigDecimal sizeKitchen = ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, ParamName.SIZE_KITCHEN);
        int balcony = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.BALCONY);
        int loggia = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.LOGGIA);
        int yearOfConstruction = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.YEAR_OF_CONSTRUCTION);
        String description = map.get(ParamName.DESCRIPTION);
        Boolean pureSale = ParamUtil.getNotEmptyBoolean(map, ParamName.PURE_SALE);
        Boolean mortgage = ParamUtil.getNotEmptyBoolean(map, ParamName.MORTGAGE);
        Boolean exchange = ParamUtil.getNotEmptyBoolean(map, ParamName.EXCHANGE);
        Boolean rent = ParamUtil.getNotEmptyBoolean(map, ParamName.RENT);
        Boolean rePlanning = ParamUtil.getNotEmptyBoolean(map, ParamName.RE_PLANNING);
        int idWorkerTarget = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ID_WORKER_TARGET);
        int idCustomer = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ID_CUSTOMER);
        int status = ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.STATUS);
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);
        int isAD = 0;

        DaoFactory.getApartamentsDao().addApartament(session, typeOfSales,
                cityName, streetName, houseNumber, buildingNumber, kladrId, shortAddress,
                apartamentLan, apartamentLon, rooms, dwellingType, price, cityDistrict, floor,
                floors, roomNumber, material, sizeApartament, sizeLiving, sizeKitchen, balcony,
                loggia, yearOfConstruction, description,
                pureSale, mortgage, exchange, rent, rePlanning, idWorker, idWorkerTarget, idCustomer, status, isAD, false);
        return true;
    }

    public boolean editApartament(Session session, String token, Map<String, String> map) throws CustomException, SQLException {
        if (map.get(ParamName.APARTAMENTS_ID) == null) {
            throw new BadRequestException();
        }
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        int apartamentsId = ParamUtil.getNotEmptyInt(map, ParamName.APARTAMENTS_ID);
        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(
                apartamentsId));
        apartaments.setIdWorker(idWorker);
        apartaments.setLastModify(new Date());
        apartaments.setTypeOfSales(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.TYPE_OF_SALES));
        apartaments.setRooms(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ROOMS));
        apartaments.setDwellingType(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.DWELLING_TYPE));
        apartaments.setPrice(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.PRICE));
        apartaments.setCityDistrict(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.CITY_DISTRICT));
        apartaments.setFloor(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.FLOOR));
        apartaments.setFloors(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.FLOORS));
        apartaments.setRoomNumber(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ROOM_NUMBER));
        apartaments.setMaterial(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.MATERIAL));
        apartaments.setSizeApartament(ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, ParamName.SIZE_APARTAMENT));
        apartaments.setSizeLiving(ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, ParamName.SIZE_LIVING));
        apartaments.setSizeKitchen(ParamUtil.getBigDecimalOfNotEmptyParamOrZero(map, ParamName.SIZE_KITCHEN));
        apartaments.setBalcony(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.BALCONY));
        apartaments.setLoggia(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.LOGGIA));
        apartaments.setYearOfConstruction(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.YEAR_OF_CONSTRUCTION));
        apartaments.setDescription(map.get(ParamName.DESCRIPTION));
        apartaments.setMethodOfPurchase_PureSale(ParamUtil.getNotEmptyBoolean(map, ParamName.PURE_SALE));
        apartaments.setMethodOfPurchase_Mortgage(ParamUtil.getNotEmptyBoolean(map, ParamName.MORTGAGE));
        apartaments.setMethodOfPurchase_Exchange(ParamUtil.getNotEmptyBoolean(map, ParamName.EXCHANGE));
        apartaments.setMethodOfPurchase_Rent(ParamUtil.getNotEmptyBoolean(map, ParamName.RENT));
        apartaments.setRePplanning(ParamUtil.getNotEmptyBoolean(map, ParamName.RE_PLANNING));
        apartaments.setIdWorkerTarget(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ID_WORKER_TARGET));
        apartaments.setIdCustomer(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.ID_CUSTOMER));
        apartaments.setStatus(ParamUtil.getNotNegativeIntOrZeroIfEmpty(map, ParamName.STATUS));

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

        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(
                apartamentsId));
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
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session, Integer.
                valueOf(status));
        return apartaments;
    }

    public boolean setApartamentsAdState(Session session, String token, Map<String, String> map) throws CustomException, SQLException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        int isAD = ParamUtil.getInt(map, ParamName.AD);
        int apartamentsId = ParamUtil.getNotEmptyInt(map, ParamName.APARTAMENTS_ID);
        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(
                apartamentsId));
        apartaments.setIsAD(isAD);
        return DaoFactory.getApartamentsDao().modifyApartament(session, apartaments);
    }
}
