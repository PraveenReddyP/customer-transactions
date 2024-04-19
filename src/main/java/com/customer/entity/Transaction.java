package com.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Transaction{
    @Id
    private String transactionid;
    private String customerid;
    private double amount;
    private Timestamp transactiondate;

    public String getTransactionid() {
        return transactionid;
    }

    public void setTransactionid(String transactionid) {
        this.transactionid = transactionid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Timestamp transactiondate) {
        this.transactiondate = transactiondate;
    }


}