package com.hiruna.oracle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hiruna.oracle.data.model.AccountTransaction;
import com.hiruna.oracle.service.AccountTransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/accounttransaction")
public class AccountTransactionController {
    private AccountTransactionService accountTransactionService;

    public AccountTransactionController(AccountTransactionService accountTransactionService){
        this.accountTransactionService=accountTransactionService;
    }

    @PostMapping("")
    public ResponseEntity<Boolean> createAccTransaction(@RequestBody AccountTransaction entity) {
        try{
            return ResponseEntity.ok(accountTransactionService.createAccountTransaction(entity));
        } catch(Exception e){
            System.err.println(e.getMessage());
            return ResponseEntity.status(500).body(false);
        }
    }
    
}
