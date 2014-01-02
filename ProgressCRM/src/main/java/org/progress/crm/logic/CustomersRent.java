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

/**
 *
 * @author best
 */
@Entity
@Table(name = "CustomersRent")
public class CustomersRent implements Serializable {

    private int id;
    private int status;
    private int assigned;
    private String description;
    private int idWorker;
    private int idCustomer;
    private Date creationDate;
    private Date lastModify;
    private boolean deleted;

    public CustomersRent() {
    }

    public CustomersRent(int status, int assigned, String description, int idWorker, int idCustomer) {
        this.status = status;
        this.assigned = assigned;
        this.description = description;
        this.idWorker = idWorker;
        this.idCustomer = idCustomer;
        this.creationDate = new Date();
        this.lastModify = new Date();
    }

    @Column(name = "Status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "Assigned")
    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    @Column(name = "idCustomer")
    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    @Column(name = "LastModify")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
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

    @Column(name = "CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "idWorker")
    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }
}
