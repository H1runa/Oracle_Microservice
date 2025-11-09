package com.hiruna.oracle.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.AccountTransaction;
import com.hiruna.oracle.data.repo.AccountTransactionRepo;
import com.hiruna.oracle.data.repo.function_repo.OracleFunction;

@Service
public class AccountTransactionService {
    private AccountTransactionRepo accountTransactionRepo;
    private OracleFunction oracleFunction;

    public AccountTransactionService(AccountTransactionRepo accountTransactionRepo, OracleFunction oracleFunction){
        this.accountTransactionRepo=accountTransactionRepo;
        this.oracleFunction=oracleFunction;
    }

    public Boolean createAccountTransaction(AccountTransaction transaction){
        accountTransactionRepo.addTransaction(transaction.getTransName(), transaction.getTransType(), transaction.getAmount(), transaction.getAccID());
        return true;
    }

    public List<Map<String,Object>> viewTransaction(Long id){
        return oracleFunction.viewTransaction(id);
    }
}
