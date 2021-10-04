package com.example.banking_app.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "transactions")
public class TransactionModel extends BaseEntity{
    private String sender;
    private String receiver;
    private double amount;
    private Date date;
}
