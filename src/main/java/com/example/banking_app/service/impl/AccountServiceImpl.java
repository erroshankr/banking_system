package com.example.banking_app.service.impl;

import com.example.banking_app.enums.ApplicationStatus;
import com.example.banking_app.enums.CardType;
import com.example.banking_app.models.AccountModel;
import com.example.banking_app.models.CardModel;
import com.example.banking_app.repo.CardRepository;
import com.example.banking_app.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public void generateCardDetails(AccountModel account, ApplicationStatus status) {
      if(status.equals(ApplicationStatus.PROCESSING_DEBITCARD)){
          createCardDetails(CardType.DEBITCARD,account);
      }
      if(status.equals(ApplicationStatus.PROCESSING_CREDITCARD)){
          createCardDetails(CardType.CREDITCARD,account);
      }
    }

    private void createCardDetails(CardType type, AccountModel account) {
        CardModel cardModel = null;
        if(!CollectionUtils.isEmpty(account.getCards())){
            Optional<CardModel> cardOpt = account.getCards().stream().filter(c -> c.getCardType().equals(type)).findFirst();
            if(cardOpt.isPresent()){
                cardModel = cardOpt.get();
            }else{
                cardModel = new CardModel();
                cardModel.setCardType(type);
                cardModel.setAccount(account);
            }
        }else{
            cardModel = new CardModel();
            cardModel.setCardType(type);
            cardModel.setAccount(account);
        }
        cardModel.setCardNumber(Long.valueOf(RandomStringUtils.random(16,false,true)));
        cardModel.setCvv(Integer.valueOf(RandomStringUtils.random(3,false,true)));
        cardModel.setPin(Integer.valueOf(RandomStringUtils.random(3,false,true)));
        cardModel.setCardHolderName(account.getAccountHolderName());
        cardModel.setMonth(Integer.valueOf(RandomStringUtils.random(1,1,12,false,true)));
        cardModel.setYear(LocalDateTime.now().getYear() + 5);
        if(cardModel.getCardType().equals(CardType.CREDITCARD)){
            cardModel.setCcBalance(cardModel.getAccount().getCurrentBalance()*3);
        }
        cardRepository.save(cardModel);
    }


}