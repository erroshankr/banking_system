package com.example.banking_app.forms;

public class BeneficiaryForm {

    private Long recieverAccountNumber;
    private String recieverBranch;
    private String recieverIfscCode;
    private String recieverAccountHolderName;
    private boolean add_beneficiary;

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