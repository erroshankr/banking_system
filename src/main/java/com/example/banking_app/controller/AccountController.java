package com.example.banking_app.controller;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.enums.CardType;
import com.example.banking_app.enums.IdentityProof;
import com.example.banking_app.forms.AccountCreationForm1;
import com.example.banking_app.forms.AccountCreationForm2;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.AddressModel;
import com.example.banking_app.models.CardModel;
import com.example.banking_app.repo.AccountRepository;
import com.example.banking_app.repo.CardRepository;
import com.example.banking_app.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class AccountController {

    @Resource
    private AccountRepository accountRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserService userService;

    @GetMapping("/user/account/create")
    public String getAccountCreation( Model model){
        AccountCreationForm1 form = new AccountCreationForm1();
        List<IdentityProof> identityProofs = new ArrayList<>();
        identityProofs.add(IdentityProof.DRIVING_LISENCE);
        identityProofs.add(IdentityProof.AADHAR_CARD);
        identityProofs.add(IdentityProof.PANCARD);
        identityProofs.add(IdentityProof.VOTERID_CARD);
        model.addAttribute("identityProofs",identityProofs);
        model.addAttribute("accountCreationForm1",form);
        return "accountCreation1";
    }

    @PostMapping("/user/account/submitAccount1")
    public String submitAccountCreation(@ModelAttribute AccountCreationForm1 accountCreationForm1, Model model){
        AccountModel account=new AccountModel();
        account.setAccountHolderName(accountCreationForm1.getAccountHolderName());
        account.setAccountType(accountCreationForm1.getAccountType());
        account.setIdentityProof(accountCreationForm1.getIdentityProof());
        account.setAge(accountCreationForm1.getAge());
        account.setUniqueIdNumber(accountCreationForm1.getUniqueIdentityfication());

        List<CardModel> cards=new ArrayList<>();
        if ((accountCreationForm1.isDebitCard())){
            CardModel card=new CardModel();
            card.setAccount(account);
            card.setCardType(CardType.DEBITCARD);
            cards.add(card);
        }
        if (accountCreationForm1.isCreditCard()){
            CardModel card=new CardModel();
            card.setAccount(account);
            card.setCardType(CardType.CREDITCARD);
            cards.add(card);
        }
        account.setCards(cards);
        String applicationID = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
        account.setApplicationId(applicationID);
        List<ApplicationStatus> statusList = new ArrayList<>();
        statusList.add(ApplicationStatus.REQUESTED);
        account.setApplicationStatus(statusList);
        account.setUser(userService.getCurrentUser());
        try {
            accountRepository.save(account);
            model.addAttribute("applicationID",applicationID);
            model.addAttribute("accountCreationForm1",new AccountCreationForm1());
        }catch (Exception e){
            model.addAttribute("accountCreationForm1",new AccountCreationForm1());
            return "accountCreation1";
        }
        final AccountCreationForm2 accountCreationForm2=new AccountCreationForm2();
        accountCreationForm2.setApplicationId(applicationID);
        model.addAttribute("accountCreationForm2",accountCreationForm2);
        return "accountCreation2";
    }
    @PostMapping("/user/account/submitAccount2")
    public  String submitAccountCreation2(@ModelAttribute AccountCreationForm2 accountCreationForm2){
        AddressModel address=new AddressModel();
        AccountModel account= accountRepository.findByApplicationId(accountCreationForm2.getApplicationId());
        address.setLine1(accountCreationForm2.getLine1());
        address.setLine2(accountCreationForm2.getLine2());
        address.setState(accountCreationForm2.getState());
        address.setZipCode(accountCreationForm2.getZipCode());
        address.setCountry(accountCreationForm2.getCountry());
        address.setCity(accountCreationForm2.getCity());
        account.setAddress(address);
        accountRepository.save(account);
        return "home";

    }

    @GetMapping("/findAppStatus")
    public String getStatus(Model model){
        model.addAttribute("statusForm",new AccountCreationForm2());
        return "applicationstatus";
    }

    @PostMapping("/trackAppStatus")
    public String submitStatus(@ModelAttribute AccountCreationForm2 accountCreationForm2,Model model){
        String result="";
        try {
            final AccountModel account= accountRepository.findByApplicationId(accountCreationForm2.getApplicationId());
            if(account == null) throw new Exception();
            List<ApplicationStatus> availableStatus = new ArrayList<>(Arrays.asList(ApplicationStatus.REQUESTED,ApplicationStatus.PENDING_VERIFICATION,ApplicationStatus.KYC_VERIFIED,ApplicationStatus.PROCESSING_DEBITCARD,ApplicationStatus.PROCESSING_CREDITCARD,ApplicationStatus.APPROVED,ApplicationStatus.REJECTED));
            if (!account.getApplicationStatus().contains(ApplicationStatus.REJECTED)){
                availableStatus.remove(ApplicationStatus.REJECTED);
            }
            if(CollectionUtils.isEmpty(account.getCards())) {
                availableStatus.remove(ApplicationStatus.PROCESSING_DEBITCARD);
                availableStatus.remove(ApplicationStatus.PROCESSING_CREDITCARD);
            }else{
                if(account.getCards().stream().anyMatch(c -> !c.getCardType().equals(CardType.DEBITCARD))){
                    availableStatus.remove(ApplicationStatus.PROCESSING_DEBITCARD);
                }
                if(account.getCards().stream().anyMatch(c -> !c.getCardType().equals(CardType.CREDITCARD))){
                    availableStatus.remove(ApplicationStatus.PROCESSING_CREDITCARD);
                }
            }
            List<ApplicationStatus> doneStatus = account.getApplicationStatus();
            availableStatus.removeAll(doneStatus);
            model.addAttribute("size",availableStatus.size());
            model.addAttribute("doneStatus",doneStatus);
            if (account.getApplicationStatus().contains(ApplicationStatus.REJECTED)){
                availableStatus = Collections.EMPTY_LIST;
            }
            model.addAttribute("todoStatus",availableStatus);
            result = "trackstatus";

        }catch (Exception e){
            model.addAttribute("errorInApplicationId");
            model.addAttribute("statusForm",new AccountCreationForm2());
            result= "applicationstatus";
        }
        return result;
    }
}
