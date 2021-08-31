package com.example.banking_app.Controller;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.enums.CardType;
import com.example.banking_app.enums.IdentityProof;
import com.example.banking_app.exception.UserNotFoundException;
import com.example.banking_app.forms.*;
import com.example.banking_app.models.*;
import com.example.banking_app.repo.AccountRepository;
import com.example.banking_app.repo.UserRepository;
import com.example.banking_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
public class TestController {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

    @GetMapping("/register1")
    public String getregistration1(Model model){
        model.addAttribute("registerForm1",new RegistrationForm1());
        return "registrationPage1";
    }

    @PostMapping("/submitRegister1")
    public String submitRegistration1(@ModelAttribute RegistrationForm1 registrationForm1, Model model){
        if(!registrationForm1.getPassword().equals(registrationForm1.getReTypePassword())) {
          model.addAttribute("passwordError","NOT_EQUAL");
          model.addAttribute("registerForm1",new RegistrationForm1());
          return "registration";
        }
        UserModel userModel =new UserModel();
        userModel.setFirstName(registrationForm1.getFirstName());
        userModel.setMiddleName(registrationForm1.getMiddleName());
        userModel.setLastName(registrationForm1.getLastName());
        userModel.setUsername(registrationForm1.getEmail());
        userModel.setDateOfBirth(registrationForm1.getDateOfBirth());
        userModel.setGender(registrationForm1.getGender());
        userModel.setPassword(registrationForm1.getPassword());
        userModel.setPhoneNumber(registrationForm1.getMobileNumber());
        userModel.setName(registrationForm1.getFirstName() + " " + registrationForm1.getMiddleName() + " " +registrationForm1.getLastName());
        userModel.setRoles("ROLE_USER");
        userModel.setActive(Boolean.TRUE);
        try {
            userRepository.save(userModel);
        }catch (Exception e){
            model.addAttribute("regError","exits");
            model.addAttribute("registerForm1",new RegistrationForm1());
        }
        final RegistrationForm2 registrationForm2 = new RegistrationForm2();
        registrationForm2.setEmail(registrationForm1.getEmail());
        model.addAttribute("registerForm2", registrationForm2);
        return "registrationPage2";
   }

   /*@GetMapping("/register2")
   public String getRegistration2(Model model){
        model.addAttribute("registerForm2",new RegistrationForm2());
        return "registrationPage2";
   }*/

   @PostMapping("/submitRegister2")
   public String submitRegistration2(@ModelAttribute RegistrationForm2 registrationForm2, Model model){
        final UserModel user = userRepository.findByUsername(registrationForm2.getEmail());
        AddressModel addressModel= new AddressModel();
        addressModel.setLine1(registrationForm2.getLine1());
        addressModel.setLine2(registrationForm2.getLine2());
        addressModel.setZipCode(registrationForm2.getZipCode());
        addressModel.setCity(registrationForm2.getCity());
        addressModel.setState(registrationForm2.getState());
        addressModel.setCountry(registrationForm2.getCountry());
        user.setPermanentAddress(addressModel);
        userRepository.save(user);
        model.addAttribute("loginForm", new LoginForm());
        return "login";
   }

    @GetMapping("/login")
    public String getLogin(Model model){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            model.addAttribute("loginForm",new LoginForm());
            return "login";
        }
       return "redirect:/";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute LoginForm loginForm, Model model, RedirectAttributes redirectModel){
        try {
            final boolean res = userService.validateUser(loginForm.getUsername(), loginForm.getPassword());
            if(res){
                model.addAttribute("PasswordError","wrongpass");
                model.addAttribute("loginForm",new LoginForm());
                return "login";
            }else{
                return "home";
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            model.addAttribute("NullUser","NOT_EQUAL");
            model.addAttribute("loginForm",new LoginForm());
            return "login";
        }
    }

    @GetMapping("/forgotPassword")
    public String getForgotPassword(Model model){
        model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
        return "forgotPassword";
    }
    @PostMapping("/submitForgotPassword")
    public String submitForgotPassword(@ModelAttribute ForgotPasswordForm forgotPasswordForm,Model model){
        final UserModel user= userRepository.findByUsername(forgotPasswordForm.getEmail());
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
            userRepository.save(user);
        }catch (Exception e){
            model.addAttribute("errorInForgotPassword");
            model.addAttribute("forgotPasswordForm",new ForgotPasswordForm());
            return "forgotPassword";
        }
        model.addAttribute("forgotPassSuccess","SUCCESS");
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

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
//        AddressModel address=new AddressModel();
        account.setAccountHolderName(accountCreationForm1.getAccountHolderName());
        account.setAccountType(accountCreationForm1.getAccountType());
        account.setIdentityProof(accountCreationForm1.getIdentityProof());
        account.setAge(accountCreationForm1.getAge());
        account.setUniqueIdNumber(accountCreationForm1.getUniqueIdentityfication());

        List<CardModel> cards=new ArrayList<>();
        if (accountCreationForm1.isDebitCard()){
            CardModel card=new CardModel();
            card.setCardType(CardType.DEBITCARD);
            cards.add(card);
        }
        if (accountCreationForm1.isCreditCard()){
            CardModel card=new CardModel();
            card.setCardType(CardType.CREDITCARD);
            cards.add(card);
        }
        account.setCards(cards);
        String applicationID =accountCreationForm1.getAccountHolderName().split(" ")[0].toUpperCase()+ UUID.randomUUID().toString().toLowerCase();
        account.setApplicationId(applicationID);
        try {
            accountRepository.save(account);
            model.addAttribute("applicationID",applicationID);
//            model.addAttribute("successfull","exits");
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
        AccountCreationStatusModel accStatus = new AccountCreationStatusModel();
        accStatus.setAccount(account);
        accStatus.setApplicationId(account.getApplicationId());
        accStatus.setApplicationStatus(ApplicationStatus.REQUESTED);
        account.setAccountCreationStatus(accStatus);
        accountRepository.save(account);
        return "home";

    }
//    public String getRandomNumber(AccountCreationForm1 form) {
//        return form.getAccountHolderName().split(" ")[0].toUpperCase()+ (Math.abs((int)Math.random()) + 100000);
//    }
}
