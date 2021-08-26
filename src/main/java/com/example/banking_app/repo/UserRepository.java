package com.example.banking_app.repo;

import com.example.banking_app.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Integer> {
    UserModel findByUsername(String username);
}
