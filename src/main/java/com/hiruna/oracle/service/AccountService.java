package com.hiruna.oracle.service;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.Account;
import com.hiruna.oracle.data.repo.AccountRepo;

@Service
public class AccountService {
    private GenericEntityService genericEntityService;
    private AccountRepo accountRepo;

    public AccountService(GenericEntityService genericEntityService, AccountRepo accountRepo){
        this.genericEntityService=genericEntityService;
        this.accountRepo=accountRepo;
    }

    //insert
    public Account createAccount(Account acc){
        return genericEntityService.insertRecord(accountRepo, acc);
    }

    //update
    public Boolean updateAccount(Account acc){
        return genericEntityService.updateRecord(accountRepo, acc, (entity, updated)->{
            entity.setAccName(updated.getAccName());
            entity.setDescription(updated.getDescription());
            entity.setBalance(updated.getBalance());
            entity.setCreatedDate(updated.getCreatedDate());
            entity.setStatus(updated.getStatus());
            entity.setInitialAmount(updated.getInitialAmount());         
        });
    }

    //delete
    public Boolean deleteAccount(Long id){
        return genericEntityService.deleteRecord(accountRepo, id);
    }    

    //exists
    public Boolean existsAccount(Long id){
        return genericEntityService.existsRecord(accountRepo, id);
    }
}
