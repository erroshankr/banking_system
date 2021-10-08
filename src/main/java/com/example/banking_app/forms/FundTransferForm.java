package com.example.banking_app.forms;

public class FundTransferForm {
    private  String bnfAccNo;
    private String senderAccNo;
    private Long recieverAccountNumber;
    private String recieverBranch;
    private String recieverIfscCode;
    private String recieverAccountHolderName;
    private boolean add_beneficiary;
    private double amount;


    public String getBnfAccNo() {
        return bnfAccNo;
    }

    public void setBnfAccNo(String bnfAccNo) {
        this.bnfAccNo = bnfAccNo;
    }

    public String getSenderAccNo() {
        return senderAccNo;
    }

    public void setSenderAccNo(String senderAccNo) {
        this.senderAccNo = senderAccNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getRecieverAccountNumber() {
        return recieverAccountNumber;
    }

    public void setRecieverAccountNumber(Long recieverAccountNumber) {
        this.recieverAccountNumber = recieverAccountNumber;
    }

    public String getRecieverBranch() {
        return recieverBranch;
    }

    public void setRecieverBranch(String recieverBranch) {
        this.recieverBranch = recieverBranch;
    }

    public String getRecieverIfscCode() {
        return recieverIfscCode;
    }

    public void setRecieverIfscCode(String recieverIfscCode) {
        this.recieverIfscCode = recieverIfscCode;
    }

    public String getRecieverAccountHolderName() {
        return recieverAccountHolderName;
    }

    public void setRecieverAccountHolderName(String recieverAccountHolderName) {
        this.recieverAccountHolderName = recieverAccountHolderName;
    }

    public boolean isAdd_beneficiary() {
        return add_beneficiary;
    }

    public void setAdd_beneficiary(boolean add_beneficiary) {
        this.add_beneficiary = add_beneficiary;
    }
}