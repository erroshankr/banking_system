package com.example.banking_app.controller;

import com.example.banking_app.forms.AccountCreationForm1;
import com.example.banking_app.forms.AccountCreationForm2;
import com.example.banking_app.models.AccountCreationStatusModel;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private  AccountRepository accountRepository;

    @GetMapping("/user/approval")
    public String getApprovalPage(Model model){
        List<AccountModel> account= accountRepository.findAll();
        List<AccountCreationForm1> formList= new ArrayList<>();
        for (AccountModel a:account){
            AccountCreationForm1 form=new AccountCreationForm1();
            form.setAccountHolderName(a.getAccountHolderName());
            form.setAccountType(a.getAccountType());
            form.setAge(a.getAge());
            form.setIdentityProof(a.getIdentityProof());
            form.setUniqueIdentityfication(a.getUniqueIdNumber());
            formList.add(form);
        }
        model.addAttribute("forms",formList);
        return "accountApproval";
    }
}
