package com.example.banking_app.forms;

public class TransactionForm {

    private Long accountNumber;
    private String branch;
    private String ifscCode;
    private String accountHolderName;
    private double amount;
    private boolean add_beneficiary;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isAdd_beneficiary() {
        return add_beneficiary;
    }

    public void setAdd_beneficiary(boolean add_beneficiary) {
        this.add_beneficiary = add_beneficiary;
    }
}