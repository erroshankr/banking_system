package com.example.banking_app.forms;

import com.example.banking_app.enums.AccountType;
import com.example.banking_app.enums.IdentityProof;

public class AccountCreationForm1 {
    private  String applicationId;
    private String accountHolderName;
    private AccountType accountType;
    private Integer age;
    private IdentityProof identityProof;
    private Long uniqueIdentityfication;
    private boolean debitCard;
    private boolean creditCard;
    private Long mobileNumber;

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
}
