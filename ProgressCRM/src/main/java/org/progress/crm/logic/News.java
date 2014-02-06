package org.progress.crm.logic;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
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
@Table(name = "News")
public class News implements Serializable {

    private int id;
    private int idWorker;
    private String header;
    private String text;
    private Date creationDate;
    private Date lastModify;
    private boolean deleted;
    private String formattedCreationDate;
    private String formattedLastModifyDate;

    @Transient
    public String getFormattedCreationDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        this.formattedCreationDate = df.format(this.creationDate);
        return this.formattedCreationDate;
    }

    @Transient
    public String getFormattedLastModifyDate() {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        this.formattedLastModifyDate = df.format(this.lastModify);
        return this.formattedLastModifyDate;
    }

    public News() {
    }

    public News(int idWorker, String header, String text) {
        this.idWorker = idWorker;
        this.header = header;
        this.text = text;
        this.creationDate = new Date();
        this.lastModify = new Date();
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

    @Column(name = "Header")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Column(name = "Text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
