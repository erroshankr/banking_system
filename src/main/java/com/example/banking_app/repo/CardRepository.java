package com.example.banking_app.repo;

import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardModel,Long> {
}
