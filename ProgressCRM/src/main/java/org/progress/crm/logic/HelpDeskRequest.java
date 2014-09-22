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
@Table(name = "HelpDesk")
public class HelpDeskRequest implements Serializable {

    private int id;
    private int idWorker;
    private String request;
    private String text;
    private int status;
    private Date creationDate;
    private Date lastModify;
    private boolean deleted;

    public HelpDeskRequest(int idWorker, String request, String text, int status) {
        this.idWorker = idWorker;
        this.request = request;
        this.text = text;
        this.status = status;
        this.creationDate = new Date();
        this.lastModify = new Date();
        this.deleted = false;
    }

    public HelpDeskRequest() {
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

    @Column(name = "Deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "LastModify")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date LastModify) {
        this.lastModify = LastModify;
    }

    @Column(name = "idWorker")
    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    @Column(name = "Request")
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Column(name = "Status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
