package com.example.banking_app.models;

import com.example.banking_app.enums.Gender;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customers")
public class CustomerModel extends BaseEntity{

@Table(name="users")
public class UserModel {
    @Id @GeneratedValue
    private int serialNo;
    private String name;
    @Column(unique = true)
    private String username;
    private double currentBalance;
    private String firstName;
    private String middleName;
    private String lastName;
    private String password;
    private long phoneNumber;
    private Date dateOfBirth;
    private String roles;
    private boolean active;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "PK")
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


    public UserModel() {
    }



    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
