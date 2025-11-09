package com.hiruna.oracle.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.BillReminder;
import com.hiruna.oracle.data.repo.BillReminderRepo;
import com.hiruna.oracle.data.repo.function_repo.OracleFunction;

@Service
public class BillReminderService {
    private BillReminderRepo billReminderRepo;
    private GenericEntityService genericEntityService;
    private OracleFunction oracleFunction;

    public BillReminderService(BillReminderRepo billReminderRepo, GenericEntityService genericEntityService, OracleFunction oracleFunction){
        this.billReminderRepo=billReminderRepo;
        this.genericEntityService=genericEntityService;
        this.oracleFunction=oracleFunction;
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

    //function
    public List<Map<String,Object>> viewBillReminder(Long id){
        return oracleFunction.viewBillReminder(id);
    }

    public List<Map<String,Object>> getUpcomingBillReminders(Long id){
        return oracleFunction.getUpcomingBillReminders(id);
    }
}
