package com.example.banking_app.service;

import com.example.banking_app.exception.UserNotFoundException;

public interface UserService {

   // boolean createUser(RegistrationForm form);
    boolean validateUser(String username,String password) throws UserNotFoundException;
}
