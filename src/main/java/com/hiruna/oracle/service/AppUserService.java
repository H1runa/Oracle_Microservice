package com.hiruna.oracle.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.AppUser;
import com.hiruna.oracle.data.repo.AppUserRepo;

import jakarta.transaction.Transactional;

@Service
public class AppUserService {
    private AppUserRepo appUserRepo;
    private GenericEntityService genericEntityService;

    public AppUserService(AppUserRepo appUserRepo, GenericEntityService genericEntityService){
        this.appUserRepo=appUserRepo;
        this.genericEntityService=genericEntityService;
    }

    // @Transactional
    public AppUser createAppUser(AppUser user){
        // appUserRepo.save(user);
        // return user;
        return genericEntityService.insertRecord(appUserRepo, user);
    }

    //update user
    public Boolean updateAppUser(AppUser user){
        // try{
        //     Optional<AppUser> retrieved_user = appUserRepo.findById(user.getUserID());
        //     if (retrieved_user.isPresent()){
        //         AppUser got_user = retrieved_user.get();
        //         got_user.setAccountName(user.getAccountName());
        //         got_user.setPassword(user.getPassword());

        //         appUserRepo.save(got_user);
                
        //         return true;
        //     } else {
        //         System.out.println("User ID ("+user.getUserID()+") not found in the database");
        //         return false;
        //     }
        // } catch (Exception e){
        //     e.printStackTrace();
        //     return false;
        // }
        return genericEntityService.updateRecord(appUserRepo, user, (entity, updated)->{
            entity.setAccountName(updated.getAccountName());
            entity.setPassword(updated.getPassword());
        });
    }
    //delete user
    public Boolean deleteAppUser(Long id){
        // try{
        //     appUserRepo.deleteById(id);
        //     System.out.println("App User deleted");
        //     return true;
        // } catch (Exception e){
        //     System.err.println("ERROR: Could not delete record");
        //     e.printStackTrace();            
        //     return false;
        // }
        return genericEntityService.deleteRecord(appUserRepo, id);
    }
    //exists check
    public Boolean existsAppUser(Long id){
        // Boolean exists = appUserRepo.existsById(id);
        // return exists;
        return genericEntityService.existsRecord(appUserRepo, id);
    }
}
