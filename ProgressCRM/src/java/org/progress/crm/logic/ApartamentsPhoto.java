package org.progress.crm.logic;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ApartamentsPhoto")
public class ApartamentsPhoto implements Serializable {

    private int id;
    private String filename;
    private String description;
    private int apartamentsId;

    public ApartamentsPhoto(String filename, String description, int apartamentsId) {
        this.filename = filename;
        this.description = description;
        this.apartamentsId = apartamentsId;
    }

    public ApartamentsPhoto() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "Filename")
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ApartamentsId")
    public int getApartamentsId() {
        return apartamentsId;
    }

    public void setApartamentsId(int apartamentsId) {
        this.apartamentsId = apartamentsId;
    }
}
