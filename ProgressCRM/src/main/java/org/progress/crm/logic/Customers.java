package org.progress.crm.logic;

import com.google.gson.annotations.Expose;
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
import javax.persistence.Transient;

@Entity
@Table(name = "Customers")
public class Customers implements Serializable {

    @Expose
    private int id;
    @Expose
    private String customersFname;
    @Expose
    private String customersLname;
    @Expose
    private String customersMname;
    @Expose
    private String customersDateOfBirthdayStr;
    private Date customersDateOfBirthday;
    @Expose
    private int customersSex;
    @Expose
    private String customersPhone;
    @Expose
    private String customersEmail;
    @Expose
    private String customersAddress;
    @Expose
    private String customersExtra;
    private boolean deleted;
    private Date сreationDate;

    public Customers(String customersFname, String customersLname, String customersMname,
            Date customersDateOfBirthday, int customersSex, String customersPhone, String customersEmail, String customersAddress,
            String customersExtra) {
        this.customersFname = customersFname;
        this.customersLname = customersLname;
        this.customersMname = customersMname;
        this.customersDateOfBirthday = customersDateOfBirthday;
        this.customersSex = customersSex;
        this.customersPhone = customersPhone;
        this.customersEmail = customersEmail;
        this.customersAddress = customersAddress;
        this.customersExtra = customersExtra;
        this.deleted = false;
        this.сreationDate = new Date();
    }

    @Transient
    public String getCustomersDateOfBirthdayStr() {
        return customersDateOfBirthdayStr;
    }

    public void setCustomersDateOfBirthdayStr(String customersDateOfBirthdayStr) {
        this.customersDateOfBirthdayStr = customersDateOfBirthdayStr;
    }

    @Column(name = "CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getСreationDate() {
        return сreationDate;
    }

    public void setСreationDate(Date сreationDate) {
        this.сreationDate = сreationDate;
    }

    @Column(name = "customersDateOfBirthday")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCustomersDateOfBirthday() {
        return customersDateOfBirthday;
    }

    public void setCustomersDateOfBirthday(Date customersDateOfBirthday) {
        this.customersDateOfBirthday = customersDateOfBirthday;
    }

    @Column(name = "Deleted")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Column(name = "customersFname")
    public String getCustomersFname() {
        return customersFname;
    }

    public void setCustomersFname(String customersFname) {
        this.customersFname = customersFname;
    }

    @Column(name = "customersLname")
    public String getCustomersLname() {
        return customersLname;
    }

    public void setCustomersLname(String customersLname) {
        this.customersLname = customersLname;
    }

    @Column(name = "customersMname")
    public String getCustomersMname() {
        return customersMname;
    }

    public void setCustomersMname(String customersMname) {
        this.customersMname = customersMname;
    }

    @Column(name = "customersSex")
    public int getCustomersSex() {
        return customersSex;
    }

    public void setCustomersSex(int customersSex) {
        this.customersSex = customersSex;
    }

    @Column(name = "customersPhone")
    public String getCustomersPhone() {
        return customersPhone;
    }

    public void setCustomersPhone(String customersPhone) {
        this.customersPhone = customersPhone;
    }

    @Column(name = "customersEmail")
    public String getCustomersEmail() {
        return customersEmail;
    }

    public void setCustomersEmail(String customersEmail) {
        this.customersEmail = customersEmail;
    }

    @Column(name = "customersAddress")
    public String getCustomersAddress() {
        return customersAddress;
    }

    public void setCustomersAddress(String customersAddress) {
        this.customersAddress = customersAddress;
    }

    @Column(name = "customersExtra")
    public String getCustomersExtra() {
        return customersExtra;
    }

    public void setCustomersExtra(String customersExtra) {
        this.customersExtra = customersExtra;
    }

    public Customers() {
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
}
