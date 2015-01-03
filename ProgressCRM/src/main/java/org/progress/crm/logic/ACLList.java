package org.progress.crm.logic;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACLList")
public class ACLList implements Serializable {

    private int id;
    private int idEntity;
    private int idAccessType;
    private int idWorker;
    private int idAccessCategory;

    public ACLList() {
    }

    public ACLList(int idEntity, int idAccessType, int idWorker, int idAccessCategory) {
        this.idEntity = idEntity;
        this.idAccessType = idAccessType;
        this.idWorker = idWorker;
        this.idAccessCategory = idAccessCategory;
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

    @Column(name = "idEntity")
    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    @Column(name = "idAccessType")
    public int getIdAccessType() {
        return idAccessType;
    }

    public void setIdAccessType(int idAccessType) {
        this.idAccessType = idAccessType;
    }

    @Column(name = "idWorker")
    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    @Column(name = "idAccessCategory")
    public int getIdAccessCategory() {
        return idAccessCategory;
    }

    public void setIdAccessCategory(int idAccessCategory) {
        this.idAccessCategory = idAccessCategory;
    }

}
