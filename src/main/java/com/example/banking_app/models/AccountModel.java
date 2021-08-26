package com.example.banking_app.models;

import com.example.banking_app.enums.AccountType;
import com.example.banking_app.enums.IdentityProof;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "accounts")
public class AccountModel extends BaseEntity{
    @Column(unique = true)
    private Long applicationId;
    private String accountHolderName;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private int age;
    @Enumerated(EnumType.STRING)
    private IdentityProof identityProof;
    private Long uniqueIdNumber;
    private Long accountNumber;
    private Long debitCardNumber;
    private Long creditCardNumber;
    private String branch;
    private String ifscCode;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CardModel> cards;
    private double currentBalance;
    private boolean terms_conditions;

    public AccountCreationStatusModel getAccountCreationStatus() {
        return accountCreationStatus;
    }

    public void setAccountCreationStatus(AccountCreationStatusModel accountCreationStatus) {
        this.accountCreationStatus = accountCreationStatus;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Status",referencedColumnName = "applicationId")
    private AccountCreationStatusModel accountCreationStatus;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public IdentityProof getIdentityProof() {
        return identityProof;
    }

    public void setIdentityProof(IdentityProof identityProof) {
        this.identityProof = identityProof;
    }

    public Long getUniqueIdNumber() {
        return uniqueIdNumber;
    }

    public void setUniqueIdNumber(Long uniqueIdNumber) {
        this.uniqueIdNumber = uniqueIdNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getDebitCardNumber() {
        return debitCardNumber;
    }

    public void setDebitCardNumber(Long debitCardNumber) {
        this.debitCardNumber = debitCardNumber;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public List<CardModel> getCards() {
        return cards;
    }

    public void setCards(List<CardModel> cards) {
        this.cards = cards;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public boolean isTerms_conditions() {
        return terms_conditions;
    }

    public void setTerms_conditions(boolean terms_conditions) {
        this.terms_conditions = terms_conditions;
    }
}
