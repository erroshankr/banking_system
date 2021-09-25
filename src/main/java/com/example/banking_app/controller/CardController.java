package com.example.banking_app.controller;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.CardModel;
import com.example.banking_app.models.UserModel;
import com.example.banking_app.repo.UserRepository;
import com.example.banking_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CardController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/account/userCards")
    public String getUsercards(Model model){
        try {
            UserModel user = userService.getCurrentUser();
            if (user != null) {
                List<AccountModel> accounts = user.getAccounts().stream().filter(a -> a.getApplicationStatus().contains(ApplicationStatus.APPROVED)).collect(Collectors.toList());
                List<CardModel> cards = new ArrayList<>();
                for (AccountModel a : accounts) {
                    cards.addAll(a.getCards());
                }
                model.addAttribute("cards", cards);
            }
        }catch (Exception e){
            model.addAttribute("userNotFoundError");
        }
        return "cards";
    }

}
