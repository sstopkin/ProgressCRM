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
@Table(name = "Groups")
public class Groups implements Serializable {

    private int id;
    private String workersGroupName;
    private boolean deleted;
    private Date creationDate;

    public Groups() {
    }

    public Groups(String workersGroupName) {
        this.workersGroupName = workersGroupName;
        this.deleted = false;
        this.creationDate = new Date();
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

    @Column(name = "groupName")
    public String getWorkersGroupName() {
        return workersGroupName;
    }

    public void setWorkersGroupName(String workersGroupName) {
        this.workersGroupName = workersGroupName;
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
