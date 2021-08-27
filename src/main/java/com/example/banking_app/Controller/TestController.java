package com.example.banking_app.Controller;

import com.example.banking_app.enums.CardType;
import com.example.banking_app.enums.IdentityProof;
import com.example.banking_app.forms.*;
import com.example.banking_app.models.*;
import com.example.banking_app.repo.AccountRepository;
import com.example.banking_app.repo.CustomerRepository;
import com.example.banking_app.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/register")
    public String getregistration(Model model){
        model.addAttribute("registerForm",new RegistrationForm());
        return "registration";
    }

    @PostMapping("/submitregistration")
    public String submitRegistration(@ModelAttribute RegistrationForm registrationForm, Model model){
        if(!registrationForm.getPassword().equals(registrationForm.getReTypePassword())) {
          model.addAttribute("passwordError","NOT_EQUAL");
          model.addAttribute("registerForm",new RegistrationForm());
          return "registration";
        }
        CustomerModel customerModel=new CustomerModel();
        AddressModel addressModel=new AddressModel();
        addressModel.setCity(registrationForm.getCity());
        addressModel.setCountry(registrationForm.getCountry());
        addressModel.setZipCode(registrationForm.getZipCode());
        addressModel.setState(registrationForm.getState());
        addressModel.setLine1(registrationForm.getLine1());
        addressModel.setLine2(registrationForm.getLine2());
        customerModel.setFirstName(registrationForm.getFirstName());
        customerModel.setMiddleName(registrationForm.getMiddleName());
        customerModel.setLastName(registrationForm.getLastName());
        customerModel.setEmail(registrationForm.getEmail());
        customerModel.setPhoneNumber(registrationForm.getMobileNumber());
        customerModel.setDateOfBirth(registrationForm.getDateOfBirth());
        customerModel.setGender(registrationForm.getGender());
        customerModel.setPassword(registrationForm.getPassword());
        customerModel.setPermanentAddress(addressModel);
        customerModel.setName(registrationForm.getFirstName() + " " + registrationForm.getMiddleName() + " " +registrationForm.getLastName());
        try {
            customerRepository.save(customerModel);
        }catch (Exception e){
            model.addAttribute("regError","exits");
            model.addAttribute("registerForm",new RegistrationForm());
        }
        return "home";
   }

    @GetMapping("/login")
    public String getLogin(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }
    @PostMapping("/submitLogin")
    public String login(@ModelAttribute LoginForm loginForm,Model model){
        final CustomerModel user = customerRepository.findByEmail(loginForm.getEmail());
        if (null==user){
            model.addAttribute("NullUser","NOT_EQUAL");
            model.addAttribute("loginForm",new LoginForm());
            return "login";
        }
        else if (!loginForm.getPassword().equals(user.getPassword())){
            model.addAttribute("PasswordError","wrongpass");
            model.addAttribute("loginForm",new LoginForm());
            return "login";
        }
        else
            return "home";
    }

    @GetMapping("/forgotPassword")
    public String getForgotPassword(Model model){
        model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
        return "forgotPassword";
    }
    @PostMapping("/submitForgotPassword")
    public String submitForgotPassword(@ModelAttribute ForgotPasswordForm forgotPasswordForm,Model model){
        final CustomerModel user=customerRepository.findByEmail(forgotPasswordForm.getEmail());
        if(user == null){
            model.addAttribute("userNameError","NOT_FOUND_USER");
            model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
            return "forgotPassword";

        }
        else if(!forgotPasswordForm.getNewPassword().equals(forgotPasswordForm.getRepeatPassword())){
            model.addAttribute("passwordError","PASSWORD_NOT_SAME");
            model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
            return "forgotPassword";
        }
        user.setPassword(forgotPasswordForm.getNewPassword());
        try {
            customerRepository.save(user);
        }catch (Exception e){
            model.addAttribute("errorInForgotPassword");
            model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
            return "forgotPassword";
        }
        model.addAttribute("forgotPassSuccess","SUCCESS");
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @GetMapping("/accountCreation")
    public String getAccountCreation( Model model){
        AccountCreationForm form = new AccountCreationForm();
        List<IdentityProof> identityProofs = new ArrayList<>();
        identityProofs.add(IdentityProof.DRIVING_LISENCE);
        identityProofs.add(IdentityProof.AADHAR_CARD);
        identityProofs.add(IdentityProof.PANCARD);
        identityProofs.add(IdentityProof.VOTERID_CARD);
        model.addAttribute("identityProofs",identityProofs);
        model.addAttribute("accountCreationForm",form);
        return "accountCreation";
    }

    @PostMapping("/accountCreationSubmit")
    public String submitAccountCreation(@ModelAttribute AccountCreationForm accountCreationForm,Model model){
        AccountModel account=new AccountModel();
        AddressModel address=new AddressModel();
        account.setAccountHolderName(accountCreationForm.getAccountHolderName());
        account.setAccountType(accountCreationForm.getAccountType());
        account.setIdentityProof(accountCreationForm.getIdentityProof());
        account.setAge(accountCreationForm.getAge());
        account.setUniqueIdNumber(accountCreationForm.getUniqueIdentityfication());
        address.setLine1(accountCreationForm.getLine1());
        address.setLine2(accountCreationForm.getLine2());
        address.setState(accountCreationForm.getState());
        address.setZipCode(accountCreationForm.getZipCode());
        address.setCountry(accountCreationForm.getCountry());
        address.setCity(accountCreationForm.getCity());
        List<CardModel> cards=new ArrayList<>();
        if (accountCreationForm.isDebitCard()){
            CardModel card=new CardModel();
            card.setCardType(CardType.DEBITCARD);
            cards.add(card);
        }
        if (accountCreationForm.isCreditCard()){
            CardModel card=new CardModel();
            card.setCardType(CardType.CREDITCARD);
            cards.add(card);
        }
        account.setCards(cards);
        String applicationID =getRandomNumber(accountCreationForm);
        account.setApplicationId(applicationID);
        try {
            accountRepository.save(account);
            model.addAttribute("applicationID",applicationID);
            model.addAttribute("successfull","exits");
            model.addAttribute("accountCreationForm",new AccountCreationForm());
        }catch (Exception e){
            model.addAttribute("accountCreationForm",new AccountCreationForm());
            return "AccountCreation";
        }
        return "home";
    }
    public String getRandomNumber(AccountCreationForm form) {
        return form.getAccountHolderName().split(" ")[0].toUpperCase()+ (Math.abs((int)Math.random()) + 100000);
    }

    @GetMapping("/userDetails")
    public String getDetails(Model model){
        List<CustomerModel> customers = customerRepository.findAll();
        List<TransactionForm> formList= new ArrayList<>();
        for(CustomerModel c:customers) {
            TransactionForm form = new TransactionForm();
            form.setCurrentBalance(c.getCurrentBalance());
            form.setSender(c.getName());
            form.setSenderEmail(c.getEmail());
         //   form.setSerialNo(c.getPK());
            formList.add(form);
        }
        model.addAttribute("forms",formList);
        model.addAttribute("transactionForm", new TransactionForm());
        return "customers";
    }
    @GetMapping("/txnHistory")
    public String getTxnDetails(Model model){
        List<TransactionModel> txns = transactionRepository.findAll();
        model.addAttribute("transactions",txns);
        return "transactionHistory";
    }

    @PostMapping("/initiateTxn")
    public String submitForm(@RequestParam("email") String email, Model model) {
        final CustomerModel user = customerRepository.findByEmail(email);
        TransactionForm transactionForm = new TransactionForm();
        transactionForm.setSenderEmail(user.getEmail());
       // transactionForm.setSerialNo(user.getSerialNo());
        transactionForm.setSender(user.getName());
        transactionForm.setCurrentBalance(user.getCurrentBalance());
        List<CustomerModel> userList = customerRepository.findAll();
        userList.remove(user);
        List<String> userNames = new ArrayList<>();
        for (CustomerModel u:userList) {
            userNames.add(u.getName() + " - " + u.getEmail());
        }
        model.addAttribute("users",userNames);
        model.addAttribute("txnForm",transactionForm);
        return "transaction";
    }

    @PostMapping("/transact")
    public String transactAmount(@ModelAttribute TransactionForm transactionForm, Model model) {
       final CustomerModel sender =  customerRepository.findByEmail(transactionForm.getSenderEmail());
       final CustomerModel receiver = customerRepository.findByEmail(transactionForm.getReceiver().split("-")[1].trim());
       final double amountToSend = transactionForm.getAmount();
       boolean result = false;
       if(amountToSend > 0 && sender.getCurrentBalance() >= amountToSend){
           try {
               TransactionModel transactionModel = new TransactionModel();
               transactionModel.setAmount(amountToSend);
               transactionModel.setSender(sender.getName());
               transactionModel.setReceiver(receiver.getName());
               LocalDate todayLocalDate = LocalDate.now(ZoneId.of("Asia/Calcutta"));
               transactionModel.setDate(Date.valueOf(todayLocalDate));
               transactionRepository.save(transactionModel);
               receiver.setCurrentBalance(receiver.getCurrentBalance() + amountToSend);
               customerRepository.save(receiver);
               sender.setCurrentBalance(sender.getCurrentBalance() - amountToSend);
               customerRepository.save(sender);
               result = true;
           }catch (final Exception e){
               result = false;
           }
       }
       model.addAttribute("txnRes", result);
       return "txnResult";
    }
}
