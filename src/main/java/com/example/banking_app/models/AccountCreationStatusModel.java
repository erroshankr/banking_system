package com.example.banking_app.models;

import com.example.banking_app.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "Status")
public class AccountCreationStatusModel extends BaseEntity {
    private Long applicationId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToOne(mappedBy = "accountCreationStatus")
    private AccountModel accountStatus;

    public AccountModel getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountModel accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
