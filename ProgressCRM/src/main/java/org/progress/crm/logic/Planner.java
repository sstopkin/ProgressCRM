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
@Table(name = "Planner")
public class Planner implements Serializable {

    private int id;
    private int idWorker;
    private String color;
    private String targetOjectUUID;
    private String title;
    private String description;
    private Date creationDate;
    private Date start;
    private Date end;
    private boolean deleted;

    public Planner() {
    }

    public Planner(int idWorker, String taskColor, String targetOjectUUID, String taskTitle, String taskDescription, Date taskStartDate, Date taskEndDate) {
        this.idWorker = idWorker;
        this.color = taskColor;
        this.targetOjectUUID = targetOjectUUID;
        this.description = taskDescription;
        this.creationDate = new Date();
        this.start = taskStartDate;
        this.end = taskEndDate;
        this.deleted = false;
        this.title = taskTitle;
    }

    @Column(name = "TaskTitle")
    public String getTaskTitle() {
        return title;
    }

    public void setTaskTitle(String taskTitle) {
        this.title = taskTitle;
    }

    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTaskStartDate() {
        return start;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.start = taskStartDate;
    }

    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTaskEndDate() {
        return end;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.end = taskEndDate;
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

    @Column(name = "idWorker")
    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    @Column(name = "CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "TaskClass")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "TaskTargetObjectUUID")
    public String getTargetOjectUUID() {
        return targetOjectUUID;
    }

    public void setTargetOjectUUID(String targetOjectUUID) {
        this.targetOjectUUID = targetOjectUUID;
    }

    @Column(name = "TaskDescription")
    public String getTaskDescription() {
        return description;
    }

    public void setTaskDescription(String taskDescription) {
        this.description = taskDescription;
    }

}
