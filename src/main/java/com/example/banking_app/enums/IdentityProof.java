package com.example.banking_app.enums;

public enum IdentityProof {
    AADHAR_CARD("Aadhar Card"),
    PANCARD("Pan Card"),
    VOTERID_CARD("Voter ID Card"),
    DRIVING_LISENCE("Driving Licence");

    private String name;

    IdentityProof(String s) {
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

//    public String toString() {
//        return this.name;
//    }
}
