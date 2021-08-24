package com.example.banking_app.models;

import com.example.banking_app.enums.Gender;


import javax.persistence.*;
import java.util.Date;

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
    private long phoneNumber;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public CustomerModel() {
    }

    public int getSerialNo() {
        return serialNo;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
