package com.hiruna.oracle.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.BillReminder;
import com.hiruna.oracle.data.repo.BillReminderRepo;

@Service
public class BillReminderService {
    private BillReminderRepo billReminderRepo;

    public BillReminderService(BillReminderRepo billReminderRepo){
        this.billReminderRepo=billReminderRepo;
    }

    //inserting
    public BillReminder createReminder(BillReminder reminder){
        BillReminder saved_rem = billReminderRepo.save(reminder);
        System.out.println("Bill Reminder saved to database");
        return saved_rem;
    }
    //updating
    public Boolean updateReminder(BillReminder reminder){
        Optional<BillReminder> rem = billReminderRepo.findById(reminder.getRemindID());
        if (rem.isPresent()){
            BillReminder got_rem = rem.get();
            got_rem.setRemindName(reminder.getRemindName());
            got_rem.setDeadline(reminder.getDeadline());
            got_rem.setStatus(reminder.getStatus());            
            got_rem.setUserID(reminder.getUserID());            

            billReminderRepo.save(got_rem);
            return true;
        } else {
            System.out.println("ERROR: Failed to update BillReminder");            
            return false;
        }
    }

    //delete
    public Boolean deleteReminder(long id){
        try{
            billReminderRepo.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    //exists check
    public Boolean existsReminder(long id){
        return billReminderRepo.existsById(id);
    }
}
