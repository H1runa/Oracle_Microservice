package com.hiruna.oracle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiruna.oracle.data.model.SavingGoal;
import com.hiruna.oracle.service.SavingGoalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/savinggoal")
public class SavingGoalController {
    private SavingGoalService savingGoalService;

    public SavingGoalController(SavingGoalService savingGoalService){
        this.savingGoalService=savingGoalService;
    }

    @PostMapping("")
    public ResponseEntity<?> createSavingGoal(@RequestBody SavingGoal entity) {
        // entity.setDeadline(savingGoalService.formatDate(entity.getDeadline()));
        try{
            return ResponseEntity.ok(savingGoalService.createSavingGoal(entity));
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<Boolean> updateSavingGoal(@RequestBody SavingGoal entity) {
        // entity.setDeadline(savingGoalService.formatDate(entity.getDeadline()));
        try{
            return ResponseEntity.ok(savingGoalService.updateSavingGoal(entity));
        } catch (Exception e){
            return ResponseEntity.status(500).body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteSavingGoal(@PathVariable Long id){
        try{
            return ResponseEntity.ok(savingGoalService.deleteSavingGoal(id));
        } catch(Exception e){
            return ResponseEntity.status(404).body(false);
        }
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> getMethodName(@PathVariable Long id) {
        return ResponseEntity.ok(savingGoalService.existsSavingGoal(id));
    }

    @GetMapping("/{id}/getsavinggoals")
    public ResponseEntity<?> getSavingGoals(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(savingGoalService.getSavingGoals(id));
        } catch(Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    
    
    
}
