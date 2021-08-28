package com.example.banking_app.controller;

import com.example.banking_app.enums.CardType;
import com.example.banking_app.enums.IdentityProof;
import com.example.banking_app.exception.UserNotFoundException;
import com.example.banking_app.forms.*;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.AddressModel;
import com.example.banking_app.models.CardModel;
import com.example.banking_app.models.UserModel;
import com.example.banking_app.repo.AccountRepository;
import com.example.banking_app.repo.AddressRepository;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private  AddressRepository addressRepository;

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
          model.addAttribute("registerForm",new RegistrationForm1());
          return "registration";
        }
        UserModel userModel =new UserModel();
        AddressModel addressModel=new AddressModel();
        userModel.setFirstName(registrationForm1.getFirstName());
        userModel.setMiddleName(registrationForm1.getMiddleName());
        userModel.setLastName(registrationForm1.getLastName());
        userModel.setUsername(registrationForm1.getEmail());
        userModel.setDateOfBirth(registrationForm1.getDateOfBirth());
        userModel.setGender(registrationForm1.getGender());
        userModel.setPassword(registrationForm1.getPassword());
        userModel.setPermanentAddress(addressModel);
        userModel.setPhoneNumber(registrationForm1.getMobileNumber());
        userModel.setName(registrationForm1.getFirstName() + " " + registrationForm1.getMiddleName() + " " +registrationForm1.getLastName());
        userModel.setRoles("ROLE_USER");
        userModel.setActive(Boolean.TRUE);
        try {
            userRepository.save(userModel);
        }catch (Exception e){
            model.addAttribute("regError","exits");
            model.addAttribute("registerForm",new RegistrationForm1());
        }
        model.addAttribute("loginForm", new RegistrationForm2());
        model.addAttribute("email",registrationForm1.getEmail());
        return "redirect:/registrationPage2";
   }

   /*@GetMapping("/register2")
   public String getRegistration2(Model model){
        model.addAttribute("registerForm2",new RegistrationForm2());
        return "registrationPage2";
   }*/

   @PostMapping("/submitRegister2")
   public String submitRegistration2(@ModelAttribute RegistrationForm2 registrationForm2, Model model, @RequestParam String email){
        AddressModel addressModel=
        addressModel.setLine1(registrationForm2.getLine1());
        addressModel.setLine2(registrationForm2.getLine2());
        addressModel.setZipCode(registrationForm2.getZipCode());
        addressModel.setCity(registrationForm2.getCity());
        addressModel.setState(registrationForm2.getState());
        addressModel.setCountry(registrationForm2.getCountry());
        addressRepository.save(addressModel);
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

    @PostMapping("/user/account/submit")
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
            return "accountCreation";
        }
        return "home";
    }
    public String getRandomNumber(AccountCreationForm form) {
        return form.getAccountHolderName().split(" ")[0].toUpperCase()+ (Math.abs((int)Math.random()) + 100000);
    }
}
