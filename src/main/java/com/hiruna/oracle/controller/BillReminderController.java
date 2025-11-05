package com.hiruna.oracle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    @PutMapping("")
    public ResponseEntity<Boolean> updateReminder(@RequestBody BillReminder reminder) {
        try{
            return ResponseEntity.ok(billReminderService.updateReminder(reminder));
        } catch (Exception e){
            return ResponseEntity.status(500).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteReminder(@PathVariable long id){
        try{
            return ResponseEntity.ok(billReminderService.deleteReminder(id));
        } catch (Exception e){
            System.out.println("ERROR: Failed to delete BillReminder");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }
}
