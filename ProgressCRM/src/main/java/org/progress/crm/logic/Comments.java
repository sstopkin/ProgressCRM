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
import javax.persistence.Transient;

@Entity
@Table(name = "Comments")
public class Comments implements Serializable {

    private int id;
    private int idWorker;
    private String objectUUID;
    private String text;
    private Date сreationDate;
    private String сreationDateStr;
    private boolean deleted;

    public Comments() {
    }

    public Comments(int idWorker, String objectUUID, String text) {
        this.idWorker = idWorker;
        this.objectUUID = objectUUID;
        this.text = text;
        this.сreationDate = new Date();
        this.deleted = false;
    }

    @Transient
    public String getСreationDateStr() {
        return сreationDateStr;
    }

    public void setСreationDateStr(String сreationDateStr) {
        this.сreationDateStr = сreationDateStr;
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

    @Column(name = "Deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "objectUUID")
    public String getObjectUUID() {
        return objectUUID;
    }

    public void setObjectUUID(String objectUUID) {
        this.objectUUID = objectUUID;
    }

    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "creationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getСreationDate() {
        return сreationDate;
    }

    public void setСreationDate(Date сreationDate) {
        this.сreationDate = сreationDate;
    }
}
