package com.example.banking_app.service.impl;

import com.example.banking_app.models.UserModel;
import com.example.banking_app.repo.UserRepository;
import com.example.banking_app.security.MyUserDetails;
import com.example.banking_app.service.UserService;
import com.example.banking_app.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Override
    public UserModel getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserModel userModel= null;
        if(authentication != null && !(authentication instanceof AnonymousAuthenticationToken)){
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            userModel = userRepository.findByUsername(userDetails.getUsername());
        }
        return userModel;
    }
}
