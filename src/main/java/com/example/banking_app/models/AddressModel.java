package com.example.banking_app.models;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressModel extends BaseEntity{

    private String line1;
    private String line2;
    private Long zipCode;
    private String city;
    private  String state;
    private String country;
    @OneToOne(mappedBy = "permanentAddress")
    private UserModel customerModel;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public UserModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(UserModel customerModel) {
        this.customerModel = customerModel;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }
}
