package com.example.banking_app.service;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.models.AccountModel;

public interface AccountService {

    void generateCardDetails(AccountModel account, ApplicationStatus status);
}
