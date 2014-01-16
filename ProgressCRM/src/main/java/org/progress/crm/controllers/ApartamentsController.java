package org.progress.crm.controllers;

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
            String shortAddress, String apartamentLan, String apartamentLon, String rooms, String price, String cityDistrict, String floor, String floors, String roomNumber,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent,
            String rePplanning, String idCustomer) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        UUID uuid = UUID.fromString(token);
        int idWorker = authManager.getUserIdByToken(uuid);

        DaoFactory.getApartamentsDao().addApartament(session, Integer.valueOf(typeOfSales),
                cityName, streetName, houseNumber, buildingNumber, kladrId, shortAddress,
                apartamentLan, apartamentLon, Integer.valueOf(rooms),
                Integer.valueOf(price), Integer.valueOf(cityDistrict), Integer.valueOf(floor),
                Integer.valueOf(floors), Integer.valueOf(roomNumber), Integer.valueOf(material), Integer.valueOf(sizeApartament),
                Integer.valueOf(sizeLiving), Integer.valueOf(sizeKitchen), Integer.valueOf(balcony),
                Integer.valueOf(loggia), Integer.valueOf(yearOfConstruction), description,
                Boolean.parseBoolean(pureSale), Boolean.parseBoolean(mortgage),
                Boolean.parseBoolean(exchange), Boolean.parseBoolean(rent),
                Boolean.parseBoolean(rePplanning), idWorker, Integer.valueOf(idCustomer), false);
        return true;
    }

    public boolean editApartament(Session session, String token, String apartamentsId,
            String typeOfSales, String price, String cityDistrict, String floor, String floors,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent, String rePlanning, String idCustomer) throws CustomException {
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
        apartaments.setIdWorker(idWorker);
        apartaments.setLastModify(new Date());
        apartaments.setLoggia(Integer.valueOf(loggia));
        apartaments.setMaterial(Integer.valueOf(material));
        apartaments.setMethodOfPurchase_Exchange(Boolean.parseBoolean(exchange));
        apartaments.setMethodOfPurchase_Mortgage(Boolean.parseBoolean(mortgage));
        apartaments.setMethodOfPurchase_PureSale(Boolean.parseBoolean(pureSale));
        apartaments.setMethodOfPurchase_Rent(Boolean.parseBoolean(rent));
        apartaments.setPrice(Integer.valueOf(price));
        apartaments.setRePplanning(Boolean.parseBoolean(rePlanning));
        apartaments.setSizeApartament(Integer.valueOf(sizeApartament));
        apartaments.setSizeKitchen(Integer.valueOf(sizeKitchen));
        apartaments.setSizeLiving(Integer.valueOf(sizeLiving));
        apartaments.setTypeOfSales(Integer.valueOf(typeOfSales));
        apartaments.setYearOfConstruction(Integer.valueOf(yearOfConstruction));
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

    public List<Apartaments> getAllApartament(Session session, String token) throws CustomException {
        if (token == null) {
            throw new IsNotAuthenticatedException();
        }
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session);
        return apartaments;
    }
}
