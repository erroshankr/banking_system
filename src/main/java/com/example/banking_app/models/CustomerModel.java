package com.example.banking_app.models;

import javax.persistence.*;

@Entity
@Table(name="customers")
public class CustomerModel {
    @Id @GeneratedValue
    private int serialNo;
    private String name;
    @Column(unique = true)
    private String email;
    private double currentBalance;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private int phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private AddressModel permanentAddress;

    public AddressModel getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressModel permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public CustomerModel() {
    }

    public int getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(int serialNo) {
        this.serialNo = serialNo;
    }

    public CustomerModel(String name, String email, double currentBalance) {
        this.name = name;
        this.email = email;
        this.currentBalance = currentBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
