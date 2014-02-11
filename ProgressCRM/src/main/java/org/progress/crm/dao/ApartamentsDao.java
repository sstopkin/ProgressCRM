package org.progress.crm.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.Apartaments;
import org.progress.crm.logic.DbFields;

public class ApartamentsDao {

    public int addApartament(final Session session, int typeOfSales, String cityName,
            String streetName, String houseNumber, String buildingNumber, String kladrId, String shortAddress,
            String apartamentLan, String apartamentLon, int rooms,
            int price, int cityDistrict, int floor, int floors, int roomNumber, int material,
            BigDecimal sizeApartament, BigDecimal sizeLiving, BigDecimal sizeKitchen, int balcony,
            int loggia, int yearOfConstruction, String description,
            boolean MethodOfPurchase_PureSale, boolean MethodOfPurchase_Mortgage,
            boolean MethodOfPurchase_Exchange, boolean MethodOfPurchase_Rent,
            boolean rePplanning, int idWorker, int idCustomer, boolean IsApproved) throws CustomException {
        return (int) session.save(new Apartaments(typeOfSales, cityName, streetName,
                houseNumber, buildingNumber, kladrId, shortAddress, apartamentLan, apartamentLon,
                rooms, price, cityDistrict, floor,
                floors, roomNumber, material, sizeApartament, sizeLiving, sizeKitchen, balcony,
                loggia, yearOfConstruction, description, MethodOfPurchase_PureSale,
                MethodOfPurchase_Mortgage, MethodOfPurchase_Exchange, MethodOfPurchase_Rent,
                rePplanning, idWorker, idCustomer, IsApproved));
    }

    public boolean setApproveApartamentById(final Session session, final int apartamentId, final boolean flag) throws CustomException {
        Apartaments apartament = getApartamentsById(session, apartamentId);
        apartament.setIsApproved(flag);
        session.update(apartament);
        return true;
    }

    public boolean removeApartamentById(final Session session, final int apartamentId) throws CustomException {
        Apartaments apartament = getApartamentsById(session, apartamentId);
        session.delete(apartament);
        return true;
    }

    public boolean modifyApartament(final Session session, final Apartaments apartament) throws CustomException {
        apartament.setLastModify(new Date());
        session.update(apartament);
        return true;
    }

    public Apartaments getApartamentsById(final Session session, final int apartamentsId) throws CustomException {
        return (Apartaments) session.get(Apartaments.class, apartamentsId);
    }

    public List<Apartaments> getAllApartaments(Session session) throws CustomException {
//        final String approved, final String tag

//                if (!tag.equals(DaoFactory.UNDEFINED)) {
////                    return getCourseListByTag(tag);
//                }
//        final String APPROVED_ALL = "all";
//                if (approved.equals(APPROVED_ALL)) {
//                    return session.createCriteria(Apartaments.class).list();
//                }
//                boolean isApproved = Boolean.parseBoolean(approved);
//                return session.createCriteria(Apartaments.class).
//                        add(Restrictions.eq(DbFields.APARTAMENTS.APPROVED, isApproved)).list();
        return session.createCriteria(Apartaments.class).
                add(Restrictions.eq(DbFields.APARTAMENTS.DELETED, false)).
                addOrder(Order.desc(DbFields.APARTAMENTS.ROOMS)).list();
    }
}
