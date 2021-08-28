package com.example.banking_app.enums;

public enum ApplicationStatus {
    REQUESTED("Requested"),
    PENDING_VERIFICATION("PENDING VERIFICATION"),
    KYC_VERIFIED("KYC VERIFIED"),
    PROCESSING_DEBITCARD("PROCESSING DEBIT CARD"),
    PROCESSING_CREDITCARD("PROCESSING CREDIT CARD"),
    REJECTED("REJECTED"),
    APPROVED("APPROVED");

    private String name;

    ApplicationStatus(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
