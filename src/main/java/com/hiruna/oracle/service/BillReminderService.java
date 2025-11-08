package com.hiruna.oracle.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.BillReminder;
import com.hiruna.oracle.data.repo.BillReminderRepo;

@Service
public class BillReminderService {
    private BillReminderRepo billReminderRepo;
    private GenericEntityService genericEntityService;

    public BillReminderService(BillReminderRepo billReminderRepo, GenericEntityService genericEntityService){
        this.billReminderRepo=billReminderRepo;
        this.genericEntityService=genericEntityService;
    }

    //inserting
    // public BillReminder createReminder(BillReminder reminder){
    //     BillReminder saved_rem = billReminderRepo.save(reminder);
    //     System.out.println("Bill Reminder saved to database");
    //     return saved_rem;
    // }
    public BillReminder createReminder(BillReminder reminder){
        return genericEntityService.insertRecord(billReminderRepo, reminder);
    }
    //updating
    public Boolean updateReminder(BillReminder reminder){
        // Optional<BillReminder> rem = billReminderRepo.findById(reminder.getRemindID());
        // if (rem.isPresent()){
        //     BillReminder got_rem = rem.get();
        //     got_rem.setRemindName(reminder.getRemindName());
        //     got_rem.setDeadline(reminder.getDeadline());
        //     got_rem.setStatus(reminder.getStatus());            
        //     got_rem.setUserID(reminder.getUserID());            

        //     billReminderRepo.save(got_rem);
        //     return true;
        // } else {
        //     System.out.println("ERROR: Failed to update BillReminder");            
        //     return false;
        // }
        return genericEntityService.updateRecord(billReminderRepo, reminder, (entity, updated)->{
            entity.setRemindName(updated.getRemindName());
            entity.setDeadline(updated.getDeadline());
            entity.setStatus(updated.getStatus());
            entity.setUserID(updated.getUserID());
        });
    }

    //delete
    public Boolean deleteReminder(Long id){
        return genericEntityService.deleteRecord(billReminderRepo, id);
    }

    //exists check
    public Boolean existsReminder(Long id){
        return genericEntityService.existsRecord(billReminderRepo, id);
    }
}
