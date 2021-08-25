package com.example.banking_app.Controller;

import com.example.banking_app.forms.ForgotPasswordForm;
import com.example.banking_app.forms.RegistrationForm;
import com.example.banking_app.forms.TransactionForm;
import com.example.banking_app.models.AddressModel;
import com.example.banking_app.models.CustomerModel;
import com.example.banking_app.models.TransactionModel;
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
        customerModel.setDateOfBirth(registrationForm.getDateofbirth());
        customerModel.setGender(registrationForm.getGender());
        customerModel.setPassword(registrationForm.getPassword());
        customerModel.setPermanentAddress(addressModel);
        customerModel.setName(registrationForm.getFirstName() + " " + registrationForm.getMiddleName() + " " +registrationForm.getLastName());
        customerRepository.save(customerModel);
        return "home";
   }

    @GetMapping("/forgotPassword")
    public String getForgotPassword(Model model){
        model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
        return "forgottPassword";
    }
    @PostMapping("/submitForgotPassword")
    public String submitForgotPassword(@ModelAttribute ForgotPasswordForm forgotPasswordForm,Model model){
        final CustomerModel user=customerRepository.findByEmail(forgotPasswordForm.getEmail());
        if(user == null){
            model.addAttribute("userNameError","NOT_FOUND_USER");
            model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
            return "forgottPassword";

        }
        else if(!forgotPasswordForm.getNewPassword().equals(forgotPasswordForm.getRepeatPassword())){
            model.addAttribute("passwordError","PASSWORD_NOT_SAME");
            model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
            return "forgottPassword";
        }
        user.setPassword(forgotPasswordForm.getNewPassword());
        customerRepository.save(user);
        return "home";
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
            form.setSerialNo(c.getSerialNo());
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
        transactionForm.setSerialNo(user.getSerialNo());
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
