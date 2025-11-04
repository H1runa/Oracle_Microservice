package com.hiruna.oracle.data.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "APPUSER")
public class AppUser {
    @Id
    @Column(name = "userID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "manual-id")
    @GenericGenerator(name = "manual-id", strategy = "assigned")
    private long userID;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "password")
    private String password;

    public AppUser() {
    }

    public AppUser(long userID, String accountName, String password) {
        this.userID = userID;
        this.accountName = accountName;
        this.password = password;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
