package org.progress.crm.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.ApartamentAndPhotos;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.ApartamentsPhoto;

@Singleton
public class ApartamentsController {
    
    public ApartamentAndPhotos getApartamentById(Session session, String apartamentId) throws CustomException {
//        FIXME
//        if (apartamentId == null) {
//            throw new BadRequestException();
//        }
        ApartamentAndPhotos result = new ApartamentAndPhotos();
        result.setApartaments(DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentId)));
//        result.setApartamentsPhotosList(DaoFactory.getApartamentsPhotoDao().getAllApartamentsPhotoByApartamentId(session,
//                Integer.valueOf(apartamentId)));
        result.setApartamentsPhotosList(new ArrayList<ApartamentsPhoto>());
        return result;
    }
    
    public boolean addApartament(Session session, String token, String typeOfSales,
            String cityName, String streetName, String houseNumber, String buildingNumber, String kladrId,
            String price, String cityDistrict, String floor, String floors,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent,
            String rePplanning, String clientPhone, String clientDescription,
            String idWorker) throws CustomException {
//        FIXME
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }

        DaoFactory.getApartamentsDao().addApartament(session, Integer.valueOf(typeOfSales),
                cityName, streetName, houseNumber, buildingNumber, kladrId,
                Integer.valueOf(price), Integer.valueOf(cityDistrict), Integer.valueOf(floor),
                Integer.valueOf(floors), Integer.valueOf(material), Integer.valueOf(sizeApartament),
                Integer.valueOf(sizeLiving), Integer.valueOf(sizeKitchen), Integer.valueOf(balcony),
                Integer.valueOf(loggia), Integer.valueOf(yearOfConstruction), description,
                Boolean.parseBoolean(pureSale), Boolean.parseBoolean(mortgage),
                Boolean.parseBoolean(exchange), Boolean.parseBoolean(rent),
                Boolean.parseBoolean(rePplanning), clientPhone, clientDescription,
                Integer.valueOf(idWorker), false);
        return true;
    }
    
    public boolean editApartament(Session session, String token, String apartamentsId,
            String typeOfSales, String price, String cityDistrict, String floor, String floors,
            String material, String sizeApartament, String sizeLiving, String sizeKitchen,
            String balcony, String loggia, String yearOfConstruction, String description,
            String pureSale, String mortgage, String exchange, String rent, String rePlanning,
            String clientPhone, String clientDescription, String idWorker) throws CustomException {
        //FIXME
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentsId));
        apartaments.setBalcony(Integer.valueOf(balcony));
        apartaments.setCityDistrict(Integer.valueOf(cityDistrict));
        apartaments.setClientDescription(clientDescription);
        apartaments.setClientPhone(clientPhone);
        apartaments.setDescription(description);
        apartaments.setFloor(Integer.valueOf(floor));
        apartaments.setFloors(Integer.valueOf(floors));
        apartaments.setIdWorker(Integer.valueOf(idWorker));
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
//        FIXME
//        if (token == null) {
//            throw new IsNotAuthenticatedException();
//        }
        Apartaments apartaments = DaoFactory.getApartamentsDao().getApartamentsById(session, Integer.valueOf(apartamentsId));
        apartaments.setDeleted(true);
        DaoFactory.getApartamentsDao().modifyApartament(session, apartaments);
        return true;
    }
    
    public List<ApartamentAndPhotos> getAllApartament(Session session) throws CustomException {
        List<ApartamentAndPhotos> result = new ArrayList<>();
        List<Apartaments> apartaments = DaoFactory.getApartamentsDao().getAllApartaments(session);
        for (Apartaments apartaments1 : apartaments) {
//            FIXME
//            result.add(new ApartamentAndPhotos(apartaments1,
//                    DaoFactory.getApartamentsPhotoDao().getAllApartamentsPhotoByApartamentId(session, apartaments1.getId())));
            result.add(new ApartamentAndPhotos(apartaments1, null));
        }
        return result;
    }
}
