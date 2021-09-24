package com.example.banking_app.controller;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.enums.CardType;
import com.example.banking_app.forms.AccountCreationForm1;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.repo.AccountRepository;
import com.example.banking_app.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class AdminController {

    @Autowired
    private  AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @GetMapping("/approval")
    public String getApprovalPage(Model model){
         populateApprovaLPageData(model);
        return "accountApproval";

    }

    private void populateApprovaLPageData(Model model) {
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
            if(!CollectionUtils.isEmpty(a.getApplicationStatus())){
                form.setStatus(a.getApplicationStatus().get(a.getApplicationStatus().size()-1));
            }
            formList.add(form);
        }
        model.addAttribute("forms",formList);
        model.addAttribute("form1",new AccountCreationForm1());
    }

    @PostMapping(value = "/submitStatus", params = "action=Approve")
    public String getApproved(@ModelAttribute AccountCreationForm1 accountCreationForm1, Model model) throws Exception {
       final AccountModel account=accountRepository.findByApplicationId(accountCreationForm1.getApplicationId());
        List<ApplicationStatus> curr_status = account.getApplicationStatus();
        if(CollectionUtils.isEmpty(curr_status)) {
            throw new Exception();
           }
           ApplicationStatus status = curr_status.get(curr_status.size() - 1);
           List<ApplicationStatus> availableStatus = Arrays.asList(ApplicationStatus.REQUESTED, ApplicationStatus.PENDING_VERIFICATION, ApplicationStatus.KYC_VERIFIED, ApplicationStatus.PROCESSING_DEBITCARD, ApplicationStatus.PROCESSING_CREDITCARD, ApplicationStatus.APPROVED, ApplicationStatus.REJECTED);
           if (status.equals(ApplicationStatus.APPROVED) || status.equals(ApplicationStatus.REJECTED)) {
               if(status.equals(ApplicationStatus.APPROVED)){
                    account.setAccountNumber(Long.valueOf(RandomStringUtils.random(16,false,true)));
                    account.setBranch("Bokaro");
                    account.setActive(true);
                    account.setIfscCode("BOK123456");
                    accountRepository.save(account);
               }
               populateApprovaLPageData(model);
               return "accountApproval";
           }
           int currentIndex = availableStatus.indexOf(status);
           int nextIndex = 0;
           if (status.equals(ApplicationStatus.KYC_VERIFIED)) {
               if (account.getCards() != null && !account.getCards().isEmpty()) {
                   if (account.getCards().stream().anyMatch(c -> c.getCardType().equals(CardType.DEBITCARD)) && !account.getCards().stream().anyMatch(c -> c.getCardType().equals(CardType.CREDITCARD))) {
                       nextIndex = currentIndex + 1;
                   } else if (!account.getCards().stream().anyMatch(c -> c.getCardType().equals(CardType.DEBITCARD)) && account.getCards().stream().anyMatch(c -> c.getCardType().equals(CardType.CREDITCARD))) {
                       nextIndex = currentIndex + 2;
                   } else {
                       nextIndex = currentIndex + 1;
                   }
               } else {
                   nextIndex = currentIndex + 3;
               }
           } else if (status.equals(ApplicationStatus.PROCESSING_DEBITCARD)){
                 if( !account.getCards().stream().anyMatch(c -> c.getCardType().equals(CardType.CREDITCARD))) {
                     nextIndex = currentIndex + 2;
                 }
           } else {
               nextIndex = currentIndex + 1;
           }
        accountService.generateCardDetails(account,status);
        curr_status.add(availableStatus.get(nextIndex));
           account.setApplicationStatus(curr_status);
        accountRepository.save(account);
        populateApprovaLPageData(model);
      return "accountApproval";
   }
    @PostMapping(value = "/submitStatus", params = "action=Reject")
    public String getRejected(@ModelAttribute AccountCreationForm1 accountCreationForm1, Model model){
        final AccountModel account=accountRepository.findByApplicationId(accountCreationForm1.getApplicationId());
        List<ApplicationStatus> availStatus = account.getApplicationStatus();
        availStatus.add(ApplicationStatus.REJECTED);
        accountRepository.save(account);
        populateApprovaLPageData(model);
        return "accountApproval";
    }

    @GetMapping("/cards")
    public  String getCardsPage(){
        return "cards";
    }


}
