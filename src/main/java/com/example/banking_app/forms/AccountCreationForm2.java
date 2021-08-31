package com.example.banking_app.forms;

import com.example.banking_app.enums.AccountType;
import com.example.banking_app.enums.IdentityProof;

public class AccountCreationForm2 {

    private String line1;
    private String line2;
    private Long zipCode;
    private String city;
    private String State;
    private String country;
    private boolean termsCondition;
    private String applicationId;

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
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
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isTermsCondition() {
        return termsCondition;
    }

    public void setTermsCondition(boolean termsCondition) {
        this.termsCondition = termsCondition;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
