package com.example.banking_app.controller;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.forms.AccountCreationForm1;
import com.example.banking_app.forms.AccountCreationForm2;
import com.example.banking_app.forms.RegistrationForm2;
import com.example.banking_app.models.AccountCreationStatusModel;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.UserModel;
import com.example.banking_app.repo.AccountRepository;
import com.example.banking_app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private  AccountRepository accountRepository;

    @GetMapping("/approval")
    public String getApprovalPage(Model model){
        List<AccountModel> account= accountRepository.findAll();
        List<AccountCreationForm1> formList= new ArrayList<>();
        for (AccountModel a:account){
            AccountCreationForm1 form=new AccountCreationForm1();
            form.setApplicationId(a.getApplicationId());
            form.setAccountHolderName(a.getAccountHolderName());
            form.setAccountType(a.getAccountType());
            form.setAge(a.getAge());
            form.setIdentityProof(a.getIdentityProof());
            form.setUniqueIdentityfication(a.getUniqueIdNumber());
            form.setStatus(a.getAccountCreationStatus().getApplicationStatus());
            formList.add(form);
        }
        model.addAttribute("forms",formList);
        model.addAttribute("form",new AccountCreationForm1());
        return "accountApproval";
    }
    @RequestMapping(value = "/submitStatus", method = RequestMethod.POST, params = "Approve")
    public String getApproved(@RequestParam AccountCreationForm1 accountCreationForm1, Model model){
       final AccountModel user=accountRepository.findByApplicationId(accountCreationForm1.getApplicationId());
       AccountCreationStatusModel accountCreationStatusModel=new AccountCreationStatusModel();
       accountCreationForm1.setStatus(ApplicationStatus.APPROVED);
      accountRepository.save(user);
      return "accountApproval";
   }
    @RequestMapping(value = "/submitStatus", method = RequestMethod.POST, params = "Reject")
    public String getRejected(@RequestParam AccountCreationForm1 accountCreationForm1, Model model){
        final AccountModel user=accountRepository.findByApplicationId(accountCreationForm1.getApplicationId());
        AccountCreationStatusModel accountCreationStatusModel=new AccountCreationStatusModel();
        accountCreationForm1.setStatus(ApplicationStatus.REJECTED);
        accountRepository.save(user);
        return "accountApproval";
    }

}
