package com.example.banking_app.repo;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.forms.AccountCreationForm2;
import com.example.banking_app.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountModel,Integer> {
    AccountModel findByApplicationId(String appID);
    AccountModel findByAccountNumber(Long accountNumber);
    List<AccountModel> findByUserAndApplicationStatus(Long userID, List<ApplicationStatus> status);
}
