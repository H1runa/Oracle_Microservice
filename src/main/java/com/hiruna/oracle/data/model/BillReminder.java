package com.hiruna.oracle.data.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BILLREMINDER")
public class BillReminder {
    @Id
    @Column(name = "remindID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "manual-id")
    @GenericGenerator(name = "manual-id", strategy = "assigned")
    private long remindID;

    @Column(name = "remindName")
    private String remindName;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "status")
    private String status;

    @Column(name = "userID")
    private long userID;

    public BillReminder() {
    }

    public BillReminder(long remindID, String remindName, String deadline, String status, long userID) {
        this.remindID = remindID;
        this.remindName = remindName;
        this.deadline = deadline;
        this.status = status;
        this.userID = userID;
    }

    public long getRemindID() {
        return remindID;
    }

    public void setRemindID(long remindID) {
        this.remindID = remindID;
    }

    public String getRemindName() {
        return remindName;
    }

    public void setRemindName(String remindName) {
        this.remindName = remindName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

}
