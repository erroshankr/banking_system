package com.example.banking_app.controller;

import com.example.banking_app.enums.CardType;
import com.example.banking_app.enums.IdentityProof;
import com.example.banking_app.exception.UserNotFoundException;
import com.example.banking_app.forms.AccountCreationForm;
import com.example.banking_app.forms.ForgotPasswordForm;
import com.example.banking_app.forms.LoginForm;
import com.example.banking_app.forms.RegistrationForm;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.AddressModel;
import com.example.banking_app.models.CardModel;
import com.example.banking_app.models.UserModel;
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
        UserModel userModel =new UserModel();
        AddressModel addressModel=new AddressModel();
        addressModel.setCity(registrationForm.getCity());
        addressModel.setCountry(registrationForm.getCountry());
        addressModel.setZipCode(registrationForm.getZipCode());
        addressModel.setState(registrationForm.getState());
        addressModel.setLine1(registrationForm.getLine1());
        addressModel.setLine2(registrationForm.getLine2());
        userModel.setFirstName(registrationForm.getFirstName());
        userModel.setMiddleName(registrationForm.getMiddleName());
        userModel.setLastName(registrationForm.getLastName());
        userModel.setUsername(registrationForm.getEmail());
        userModel.setPhoneNumber(registrationForm.getMobileNumber());
        userModel.setDateOfBirth(registrationForm.getDateOfBirth());
        userModel.setGender(registrationForm.getGender());
        userModel.setPassword(registrationForm.getPassword());
        userModel.setPermanentAddress(addressModel);
        userModel.setName(registrationForm.getFirstName() + " " + registrationForm.getMiddleName() + " " +registrationForm.getLastName());
        userModel.setRoles("ROLE_USER");
        userModel.setActive(Boolean.TRUE);
        try {
            userRepository.save(userModel);
        }catch (Exception e){
            model.addAttribute("regError","exits");
            model.addAttribute("registerForm",new RegistrationForm());
        }
        model.addAttribute("loginForm", new LoginForm());
        return "redirect:/login";
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
