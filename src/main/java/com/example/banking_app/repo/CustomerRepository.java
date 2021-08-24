package com.example.banking_app.repo;

import com.example.banking_app.models.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerModel,Integer> {
    CustomerModel findByEmail(String email);
}
