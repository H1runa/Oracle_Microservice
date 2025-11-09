package com.hiruna.oracle.data.model;

import java.sql.Date;

import org.hibernate.annotations.GenericGenerator;

import com.hiruna.oracle.interfaces.SyncModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "savinggoal")
public class SavingGoal implements SyncModel{
    @Id
    @Column(name = "goalID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "manual-id")
    @GenericGenerator(name = "manual-id", strategy = "assigned")
    private Long goalID;

    @Column(name = "goalName")
    private String goalName;

    @Column(name = "targetAmount")
    private Double targetAmount;

    @Column(name = "createdDate", insertable = false)
    private Date createdDate;

    @Column(name = "deadline")
    private Date deadline;

    @Column(name = "accID")
    private Long accID;

    public SavingGoal() {
    }

    public SavingGoal(Long goalID, String goalName, Double targetAmount, Date createdDate, Date deadline,
            Long accID) {
        this.goalID = goalID;
        this.goalName = goalName;
        this.targetAmount = targetAmount;
        this.createdDate = createdDate;
        this.deadline = deadline;
        this.accID = accID;
    }

    public Long getGoalID() {
        return goalID;
    }

    public void setGoalID(Long goalID) {
        this.goalID = goalID;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public Double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    
    public Long getAccID() {
        return accID;
    }
    
    public void setAccID(Long accID) {
        this.accID = accID;
    }
    @Override
    public Long getId(){
        return this.goalID;
    }
    
}
