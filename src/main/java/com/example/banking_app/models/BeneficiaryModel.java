package com.example.banking_app.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "beneficiaries")
public class BeneficiaryModel extends  BaseEntity{
    private Long recieverAccountNumber;
    private String recieverBranch;
    private String recieverIfscCode;
    private String recieverAccountHolderName;
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountModel account;

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public Long getRecieverAccountNumber() {
        return recieverAccountNumber;
    }

    public void setRecieverAccountNumber(Long recieverAccountNumber) {
        this.recieverAccountNumber = recieverAccountNumber;
    }

    public String getRecieverBranch() {
        return recieverBranch;
    }

    public void setRecieverBranch(String recieverBranch) {
        this.recieverBranch = recieverBranch;
    }

    public String getRecieverIfscCode() {
        return recieverIfscCode;
    }

    public void setRecieverIfscCode(String recieverIfscCode) {
        this.recieverIfscCode = recieverIfscCode;
    }

    public String getRecieverAccountHolderName() {
        return recieverAccountHolderName;
    }

    public void setRecieverAccountHolderName(String recieverAccountHolderName) {
        this.recieverAccountHolderName = recieverAccountHolderName;
    }
}
