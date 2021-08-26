package com.example.banking_app.models;

import com.example.banking_app.enums.Month;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cards")
public class CardModel extends BaseEntity{
    private Long cardNumber;
    private String cardHolderName;
    private int cvv;
    private String cardType;
    @Enumerated(EnumType.STRING)
    private Month month;
    private int year;
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountModel account;

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

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
