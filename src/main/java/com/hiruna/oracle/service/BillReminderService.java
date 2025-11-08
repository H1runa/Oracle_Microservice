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
   
    public BillReminder createReminder(BillReminder reminder){
        return genericEntityService.insertRecord(billReminderRepo, reminder);
    }
    //updating
    public Boolean updateReminder(BillReminder reminder){        
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
