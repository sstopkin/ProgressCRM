package org.progress.crm.controllers;

import java.util.List;
import javax.ejb.Singleton;
import org.hibernate.Session;
import org.progress.crm.dao.DaoFactory;
import org.progress.crm.exceptions.BadRequestException;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.ApartamentsPhoto;

@Singleton
public class ApartamentsPhotoController {

    public List<ApartamentsPhoto> getAllApartamentPhotoByApartamentId(Session session, String apartamentId) throws CustomException {
        if (apartamentId == null) {
            throw new BadRequestException();
        }
//        FIXME
        return null;
    }

    public boolean addApartamentPhoto(Session session, String filename, String description, String apartamentId) throws CustomException {
//        FIXME
//        if (token == null) { 
//            throw new IsNotAuthenticatedException();
//        }
//        DaoFactory.getApartamentsPhotoDao().addApartamentPhoto(session, filename, description, Integer.valueOf(apartamentId));
        return true;
    }
}
