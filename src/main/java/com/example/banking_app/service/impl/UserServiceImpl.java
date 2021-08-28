package com.example.banking_app.service.impl;

import com.example.banking_app.models.UserModel;
import com.example.banking_app.repo.UserRepository;
import com.example.banking_app.service.UserService;
import com.example.banking_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean validateUser(String username, String password) throws UserNotFoundException {
        final UserModel user = userRepository.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException();
        }
        if(user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }
}
