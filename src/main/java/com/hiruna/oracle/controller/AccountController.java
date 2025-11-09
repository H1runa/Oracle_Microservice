package com.hiruna.oracle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiruna.oracle.data.dto.AccountDTO;
import com.hiruna.oracle.data.model.Account;
import com.hiruna.oracle.service.AccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    @PostMapping("")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO entity) {
        try{
            return ResponseEntity.ok(accountService.createAccount(entity));
        } catch(Exception e){
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<Boolean> updateAccount( @RequestBody AccountDTO entity) {
        try{
            return ResponseEntity.ok(accountService.updateAccount(entity));
        } catch(Exception e){
            return ResponseEntity.status(500).body(false);
        }
    }

    @DeleteMapping("/{id}/{category}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id, @PathVariable String category){
        try{
            System.out.println("CATEGORY: "+category); //delete this
            return ResponseEntity.ok(accountService.deleteAccount(id, category));
        } catch(Exception e){
            System.out.println("ERROR: Failed to delete account");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
    }

    @GetMapping("/{id}/exists")
    public ResponseEntity<Boolean> existsAccount(@PathVariable Long id) {
        return ResponseEntity.ok( accountService.existsAccount(id));
    }
    
    
}
