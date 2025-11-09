package com.hiruna.oracle.service;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.dto.AccountDTO;
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
    public Boolean createAccount(AccountDTO dto){        
        accountRepo.createAccount(dto.getAccID(), dto.getAccName(), dto.getDescription(), dto.getInitialAmount(), dto.getUserID(), dto.getAccountType(), dto.getCategory(), dto.getLimit());
        return true;
    }

    //update
    public Boolean updateAccount(AccountDTO dto){
        // return genericEntityService.updateRecord(accountRepo, acc, (entity, updated)->{
        //     entity.setAccName(updated.getAccName());
        //     entity.setDescription(updated.getDescription());
        //     entity.setBalance(updated.getBalance());
        //     entity.setCreatedDate(updated.getCreatedDate());
        //     entity.setStatus(updated.getStatus());
        //     entity.setInitialAmount(updated.getInitialAmount());         
        // });
        System.out.println("LIMIT : "+dto.getLimit());
        accountRepo.updateAccount(dto.getAccName(), dto.getDescription(), dto.getLimit(), dto.getCategory(), dto.getAccID());
        return true;
    }

    //delete
    public Boolean deleteAccount(Long id, String category){
        // return genericEntityService.deleteRecord(accountRepo, id);
        accountRepo.deleteAccount(category, id);
        return true;
    }    

    //exists
    public Boolean existsAccount(Long id){
        return genericEntityService.existsRecord(accountRepo, id);
    }
}
