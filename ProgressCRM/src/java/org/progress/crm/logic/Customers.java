package org.progress.crm.logic;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customers implements Serializable {

    private int id;
    @Expose
    private String customersFname;
    @Expose
    private String customersLname;
    @Expose
    private String customersMname;
    private int customersMonthOfBirthday;
    private int customersDayOfBirthday;
    private int customersYearOfBirthday;
    private int customersSex;
    private String customersPhone;
    private String customersEmail;
    private String customersAddress;
    private String customersExtra;
    private boolean deleted;

    public Customers(String customersFname, String customersLname, String customersMname,
            int customersMonthOfBirthday, int customersDayOfBirthday, int customersYearOfBirthday,
            int customersSex, String customersPhone, String customersEmail, String customersAddress,
            String customersExtra) {
        this.customersFname = customersFname;
        this.customersLname = customersLname;
        this.customersMname = customersMname;
        this.customersMonthOfBirthday = customersMonthOfBirthday;
        this.customersDayOfBirthday = customersDayOfBirthday;
        this.customersYearOfBirthday = customersYearOfBirthday;
        this.customersSex = customersSex;
        this.customersPhone = customersPhone;
        this.customersEmail = customersEmail;
        this.customersAddress = customersAddress;
        this.customersExtra = customersExtra;
        this.deleted = false;
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

    @Column(name = "customersMonthOfBirthday")
    public int getCustomersMonthOfBirthday() {
        return customersMonthOfBirthday;
    }

    public void setCustomersMonthOfBirthday(int customersMonthOfBirthday) {
        this.customersMonthOfBirthday = customersMonthOfBirthday;
    }

    @Column(name = "customersDayOfBirthday")
    public int getCustomersDayOfBirthday() {
        return customersDayOfBirthday;
    }

    public void setCustomersDayOfBirthday(int customersDayOfBirthday) {
        this.customersDayOfBirthday = customersDayOfBirthday;
    }

    @Column(name = "customersYearOfBirthday")
    public int getCustomersYearOfBirthday() {
        return customersYearOfBirthday;
    }

    public void setCustomersYearOfBirthday(int customersYearOfBirthday) {
        this.customersYearOfBirthday = customersYearOfBirthday;
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
