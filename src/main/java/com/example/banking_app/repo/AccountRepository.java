package com.example.banking_app.repo;

import com.example.banking_app.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel,Integer> {
}
