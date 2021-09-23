package com.example.banking_app.models;

import com.example.banking_app.enums.CardType;
import com.example.banking_app.enums.Month;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cards")
public class CardModel extends BaseEntity{
    @Column(unique = true)
    private Long cardNumber;
    private String cardHolderName;
    private int cvv;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    private int month;
    private int year;
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountModel account;
    private int pin;
    private double ccBalance;

    public double getCcBalance() {
        return ccBalance;
    }

    public void setCcBalance(double ccBalance) {
        this.ccBalance = ccBalance;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
