package com.example.banking_app.repo;

import com.example.banking_app.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressModel,Integer> {
}
