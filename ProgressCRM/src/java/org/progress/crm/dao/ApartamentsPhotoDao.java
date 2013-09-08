package org.progress.crm.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.progress.crm.exceptions.CustomException;
import org.progress.crm.logic.ApartamentsPhoto;
import org.progress.crm.logic.DbFields;

public class ApartamentsPhotoDao {

    public int addApartamentPhoto(final Session session, final String filename, final String description, final int idApartament) throws CustomException {
        return (int) session.save(new ApartamentsPhoto(filename, description, idApartament));
    }

//    public boolean removeApartamentPhotoById(final int apartamentId) {
//        return TransactionService.runInScope(new Command<Boolean>() {
//            @Override
//            public Boolean execute(Session session) {
//                ApartamentsPhoto apartamentsPhoto = getApartamentsPhotosById(apartamentId);
//                session.delete(apartamentsPhoto);
//                return true;
//            }
//        });
//    }
    public List<ApartamentsPhoto> getAllApartamentsPhotoByApartamentId(final Session session, final int apartamentId) throws CustomException {
        List<ApartamentsPhoto> helper = session.createCriteria(ApartamentsPhoto.class).
                add(Restrictions.eq(DbFields.APARTAMENTS_PHOTO.APARTAMENTS_ID, apartamentId)).list();
        //FIXME
//                List<List<String>>
        List tagsList = new ArrayList<>();
//                for (CoursesTags tag : helper) {
//                    tagsList.add(tag.getTag());
//                }
        return helper;
    }
//    public List getAllApartamentsPhotos(final String approved, final String tag) {
//        return TransactionService.runInScope(new Command<List>() {
//            @Override
//            public List execute(Session session) {
//                if (!tag.equals(DaoFactory.UNDEFINED)) {
////                    return getCourseListByTag(tag);
//                }
//                final String APPROVED_ALL = "all";
//                if (approved.equals(APPROVED_ALL)) {
//                    return session.createCriteria(Apartaments.class).list();
//                }
//                boolean isApproved = Boolean.parseBoolean(approved);
//                return session.createCriteria(Apartaments.class).
//                        add(Restrictions.eq(DbFields.APARTAMENTS.APPROVED, isApproved)).list();
//            }
//        });
//    }
}
