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
@Table(name = "BILLREMINDER")
public class BillReminder implements SyncModel {
    @Id
    @Column(name = "remindID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "manual-id")
    @GenericGenerator(name = "manual-id", strategy = "assigned")
    private Long remindID;

    @Column(name = "remindName")
    private String remindName;

    @Column(name = "deadline")
    private String deadline;

    @Column(name = "status")
    private String status;

    @Column(name = "userID")
    private Long userID;

    public BillReminder() {
    }

    public BillReminder(Long remindID, String remindName, String deadline, String status, Long userID) {
        this.remindID = remindID;
        this.remindName = remindName;
        this.deadline = deadline;
        this.status = status;
        this.userID = userID;
    }

    public Long getRemindID() {
        return remindID;
    }

    public void setRemindID(Long remindID) {
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

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    @Override
    public Long getId(){
        return this.remindID;
    }

}
