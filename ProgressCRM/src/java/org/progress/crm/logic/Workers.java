package org.progress.crm.logic;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "Workers")
public class Workers implements Serializable {

    private int id;
    @Expose
    private String fName;
    @Expose
    private String lName;
    @Expose
    private String mName;
    private String pwdhash;
    private int permissions;
    @Expose
    private String email;
    private boolean deleted;

    public Workers() {
    }

    public Workers(String theEmail, String theFName, String theMName, String theLname, String thePass) {
        this.fName = theFName;
        this.lName = theLname;
        this.mName = theMName;
        this.pwdhash = thePass;
        this.email = theEmail;
        this.deleted = false;
    }

    @Column(name = "Deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "FName")
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @Column(name = "LName")
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Column(name = "MName")
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
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

    @Column(name = "PwdHash")
    public String getPwdhash() {
        return pwdhash;
    }

    public void setPwdhash(String pwdhash) {
        this.pwdhash = pwdhash;
    }

    @Column(name = "Permissions")
    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    @NaturalId
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
