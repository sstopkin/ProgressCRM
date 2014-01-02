package org.progress.crm.logic;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Calls")
public class Calls implements Serializable {

    private int id;
    private int apartamentId;
    private Date date;
    private String description;
    private int idWorker;

    public Calls() {
    }

    public Calls(int apartamentId, Date date, String description, int idWorker) {
        this.apartamentId = apartamentId;
        this.date = date;
        this.description = description;
        this.idWorker = idWorker;
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

    @Column(name = "idWorker")
    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ApartamentsId")
    public int getApartamentId() {
        return apartamentId;
    }

    public void setApartamentId(int apartamentId) {
        this.apartamentId = apartamentId;
    }

    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
