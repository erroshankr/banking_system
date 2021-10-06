package com.example.banking_app.models;

import com.example.banking_app.enums.FundTransferStatus;
import com.example.banking_app.enums.TransactionType;
import org.springframework.transaction.TransactionStatus;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class TransactionModel extends BaseEntity{
    private String senderName;
    private String receiverName;
    private Long transferFrom;
    private Long transferTo;
    private double amount;
    private Timestamp date;
    @Enumerated(EnumType.STRING)
    private FundTransferStatus fundTransferStatus;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne(fetch = FetchType.LAZY)
    private AccountModel account;


    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public Long getTransferFrom() {
        return transferFrom;
    }

    public void setTransferFrom(Long transferFrom) {
        this.transferFrom = transferFrom;
    }

    public Long getTransferTo() {
        return transferTo;
    }

    public void setTransferTo(Long transferTo) {
        this.transferTo = transferTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public FundTransferStatus getFundTransferStatus() {
        return fundTransferStatus;
    }

    public void setFundTransferStatus(FundTransferStatus fundTransferStatus) {
        this.fundTransferStatus = fundTransferStatus;
    }
}
