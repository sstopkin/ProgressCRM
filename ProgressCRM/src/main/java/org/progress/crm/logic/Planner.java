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
    private String taskClass;
    private String targetOjectUUID;
    private String taskTitle;
    private String taskDescription;
    private Date creationDate;
    private Date taskStartDate;
    private Date taskEndDate;
    private boolean deleted;

    public Planner() {
    }

    public Planner(int idWorker, String taskClass, String targetOjectUUID, String taskTitle, String taskDescription, Date taskStartDate, Date taskEndDate) {
        this.idWorker = idWorker;
        this.taskClass = taskClass;
        this.targetOjectUUID = targetOjectUUID;
        this.taskDescription = taskDescription;
        this.creationDate = new Date();
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
        this.deleted = false;
        this.taskTitle = taskTitle;
    }

    @Column(name = "TaskTitle")
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Date taskStartDate) {
        this.taskStartDate = taskStartDate;
    }

    @Column(name = "EndDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
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
    public String getTaskClass() {
        return taskClass;
    }

    public void setTaskClass(String taskType) {
        this.taskClass = taskType;
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
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

}
