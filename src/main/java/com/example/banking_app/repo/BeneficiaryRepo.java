package com.example.banking_app.repo;

import com.example.banking_app.models.BeneficiaryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaryRepo extends JpaRepository<BeneficiaryModel,Integer> {
}
