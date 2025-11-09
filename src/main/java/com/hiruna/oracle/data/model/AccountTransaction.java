package com.hiruna.oracle.data.model;

import org.hibernate.annotations.GenericGenerator;

import com.hiruna.oracle.interfaces.SyncModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOUNTTRANSACTION")
public class AccountTransaction implements SyncModel {
    @Id
    @Column(name = "transID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "manual-id")
    @GenericGenerator(name = "manual-id", strategy = "assigned")
    private Long transID;

    @Column(name = "transName")
    private String transName;

    @Column(name = "transType")
    private String transType;

    @Column(name = "transDate")
    private String transDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "accID")
    private Long accID;

    public AccountTransaction() {
    }

    public AccountTransaction(Long transID, String transName, String transType, String transDate, Double amount,
            Long accID) {
        this.transID = transID;
        this.transName = transName;
        this.transType = transType;
        this.transDate = transDate;
        this.amount = amount;
        this.accID = accID;
    }

    public Long getTransID() {
        return transID;
    }

    public void setTransID(Long transID) {
        this.transID = transID;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getAccID() {
        return accID;
    }

    public void setAccID(Long accID) {
        this.accID = accID;
    }
    @Override
    public Long getId(){
        return this.transID;
    }
}
