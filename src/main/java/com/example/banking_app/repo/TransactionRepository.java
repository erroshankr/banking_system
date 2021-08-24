package com.example.banking_app.repo;

import com.example.banking_app.models.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionModel,Integer> {
}
