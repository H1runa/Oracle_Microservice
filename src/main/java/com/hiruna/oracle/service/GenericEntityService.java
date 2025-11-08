package com.hiruna.oracle.service;

import java.util.Optional;
import java.util.function.BiConsumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.AppUser;
import com.hiruna.oracle.interfaces.SyncModel;

@Service
public class GenericEntityService {
    public GenericEntityService(){

    }

    public <T> T insertRecord(JpaRepository<T, Long> repo, T entity){
        repo.save(entity);
        return entity;
    }

    //update user
    public <T extends SyncModel> Boolean updateRecord(JpaRepository<T, Long> repo, T entity, BiConsumer<T,T> updater){
        try{
            Optional<T> ret_entity = repo.findById(entity.getId());
            if (ret_entity.isPresent()){
                T got_entity = ret_entity.get();
                // got_entity.setAccountName(user.getAccountName());
                // got_entity.setPassword(user.getPassword());

                updater.accept(got_entity, entity);

                repo.save(got_entity);
                
                return true;
            } else {
                System.out.println("ERROR: Entity not found in the database. Not updated.");
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    //delete user
    public <T> Boolean deleteRecord(JpaRepository<T,Long> repo, Long id){
        try{
            repo.deleteById(id);
            System.out.println("Entity Deleted");
            return true;
        } catch (Exception e){
            System.err.println("ERROR: Could not delete entity");
            e.printStackTrace();            
            return false;
        }
    }
    //exists check
    public <T> Boolean existsRecord(JpaRepository<T, Long> repo ,Long id){
        Boolean exists = repo.existsById(id);
        return exists;
    }
}
