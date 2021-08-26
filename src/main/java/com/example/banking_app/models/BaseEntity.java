package com.example.banking_app.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PK;

    public Long getPK() {
        return PK;
    }

    public void setPK(Long PK) {
        this.PK = PK;
    }
}
