package org.progress.crm.logic;

import java.util.List;

public class ApartamentAndPhotos {

    private Apartaments apartaments;
    private List<ApartamentsPhoto> apartamentsPhotosList;

    public ApartamentAndPhotos(Apartaments apartaments, List<ApartamentsPhoto> apartamentsPhotosList) {
        this.apartaments = apartaments;
        this.apartamentsPhotosList = apartamentsPhotosList;
    }

    public ApartamentAndPhotos() {
    }

    public Apartaments getApartaments() {
        return apartaments;
    }

    public void setApartaments(Apartaments apartaments) {
        this.apartaments = apartaments;
    }

    public List<ApartamentsPhoto> getApartamentsPhotosList() {
        return apartamentsPhotosList;
    }

    public void setApartamentsPhotosList(List<ApartamentsPhoto> apartamentsPhotosList) {
        this.apartamentsPhotosList = apartamentsPhotosList;
    }
}
