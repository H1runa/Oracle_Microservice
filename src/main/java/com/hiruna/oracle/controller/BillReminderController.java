package com.hiruna.oracle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiruna.oracle.data.model.BillReminder;
import com.hiruna.oracle.service.BillReminderService;

@RestController
@RequestMapping("/billreminder")
public class BillReminderController {
    private BillReminderService billReminderService;

    public BillReminderController(BillReminderService billReminderService){
        this.billReminderService=billReminderService;
    }

    @PostMapping("")
    public ResponseEntity<?> createReminder(@RequestBody BillReminder reminder) {
        try{            
            System.out.println(reminder);
            return ResponseEntity.ok(billReminderService.createReminder(reminder));            
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(409).body(e.getMessage()); //Conflict error
        }
    }
}
