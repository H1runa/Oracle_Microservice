package com.hiruna.oracle.service;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.AccountTransaction;
import com.hiruna.oracle.data.repo.AccountTransactionRepo;

@Service
public class AccountTransactionService {
    private AccountTransactionRepo accountTransactionRepo;

    public AccountTransactionService(AccountTransactionRepo accountTransactionRepo){
        this.accountTransactionRepo=accountTransactionRepo;
    }

    public Boolean createAccountTransaction(AccountTransaction transaction){
        accountTransactionRepo.addTransaction(transaction.getTransName(), transaction.getTransType(), transaction.getAmount(), transaction.getAccID());
        return true;
    }
}
