package com.example.banking_app.forms;

import com.example.banking_app.enums.AccountType;
import com.example.banking_app.enums.IdentityProof;

public class AccountCreationForm {

    private String accountHolderName;
    private AccountType accountType;
    private Integer age;
    private IdentityProof identityProof;
    private Long uniqueIdentityfication;
    private boolean debitCard;
    private boolean creditCard;
    private Long mobileNumber;
    private String line1;
    private String line2;
    private Long zipCode;
    private String city;
    private String State;
    private String country;
    private boolean termsCondition;
    private String applicationId;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public IdentityProof getIdentityProof() {
        return identityProof;
    }

    public void setIdentityProof(IdentityProof identityProof) {
        this.identityProof = identityProof;
    }

    public Long getUniqueIdentityfication() {
        return uniqueIdentityfication;
    }

    public void setUniqueIdentityfication(Long uniqueIdentityfication) {
        this.uniqueIdentityfication = uniqueIdentityfication;
    }

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

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
}
