package com.example.banking_app.models;

import com.example.banking_app.enums.ApplicationStatus;

import javax.persistence.*;

@Entity
@Table(name = "account_status")
public class AccountCreationStatusModel extends BaseEntity {
    @Column(unique = true)
    private String applicationId;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
    @OneToOne(mappedBy = "accountCreationStatus")
    private AccountModel account;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public ApplicationStatus getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(ApplicationStatus applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }
}
