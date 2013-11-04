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
@Table(name = "AnnouncementsCalls")
public class AnnouncementsCalls implements Serializable {

    private int id;
    private int announcementsId;
    private String description;
    private boolean deleted;
    private int idWorker;
    private Date creationDate;

    public AnnouncementsCalls() {
    }

    public AnnouncementsCalls(int announcementsId, String description, int idWorker) {
        this.announcementsId = announcementsId;
        this.description = description;
        this.deleted = false;
        this.idWorker = idWorker;
        this.creationDate = new Date();
    }

    @Column(name = "AnnouncementsId")
    public int getAnnouncementsId() {
        return announcementsId;
    }

    public void setAnnouncementsId(int announcementsId) {
        this.announcementsId = announcementsId;
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
