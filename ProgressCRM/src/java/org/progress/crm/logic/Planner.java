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
    private int taskType;
    private int taskId;
    private String taskDescription;
    private Date creationDate;
    private Date taskDate;
    private boolean deleted;

    public Planner() {
    }

    public Planner(int idWorker, int taskType, int taskId, String taskDescription, Date taskDate) {
        this.idWorker = idWorker;
        this.taskType = taskType;
        this.taskId = taskId;
        this.taskDescription = taskDescription;
        this.creationDate = new Date();
        this.taskDate = taskDate;
        this.deleted = false;
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

    @Column(name = "TaskType")
    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    @Column(name = "TaskId")
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Column(name = "TaskDescription")
    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Column(name = "TaskDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
