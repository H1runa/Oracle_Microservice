package com.hiruna.oracle.service;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.BillReminder;
import com.hiruna.oracle.data.repo.BillReminderRepo;

@Service
public class BillReminderService {
    private BillReminderRepo billReminderRepo;

    public BillReminderService(BillReminderRepo billReminderRepo){
        this.billReminderRepo=billReminderRepo;
    }

    public BillReminder createReminder(BillReminder reminder){
        BillReminder saved_rem = billReminderRepo.save(reminder);
        System.out.println("Bill Reminder saved to database");
        return saved_rem;
    }
}
