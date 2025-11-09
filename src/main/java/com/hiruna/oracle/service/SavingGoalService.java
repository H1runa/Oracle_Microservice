package com.hiruna.oracle.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.model.SavingGoal;
import com.hiruna.oracle.data.repo.SavingGoalRepo;
import com.hiruna.oracle.data.repo.function_repo.OracleFunction;

@Service
public class SavingGoalService {
    private GenericEntityService genericEntityService;
    private SavingGoalRepo savingGoalRepo;
    private OracleFunction oracleFunction;

    public SavingGoalService(GenericEntityService genericEntityService, SavingGoalRepo savingGoalRepo, OracleFunction oracleFunction){
        this.genericEntityService=genericEntityService;
        this.savingGoalRepo=savingGoalRepo;
        this.oracleFunction=oracleFunction;
    }

    public SavingGoal createSavingGoal(SavingGoal goal){
        return genericEntityService.insertRecord(savingGoalRepo, goal);
    }

    public Boolean updateSavingGoal(SavingGoal goal){
        return genericEntityService.updateRecord(savingGoalRepo, goal, (entity, updated)->{
            entity.setGoalName(updated.getGoalName());
            entity.setTargetAmount(updated.getTargetAmount());
            entity.setDeadline(updated.getDeadline());  
        });
    }

    public Boolean deleteSavingGoal(Long id){
        return genericEntityService.deleteRecord(savingGoalRepo, id);
    }

    public Boolean existsSavingGoal(Long id){
        return genericEntityService.existsRecord(savingGoalRepo, id);
    }

    public LocalDate formatDate(String date){        
        LocalDate localDate = LocalDate.parse("2025-11-25", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return localDate;
    }
    
    //function
    public List<Map<String,Object>> getSavingGoals(Long id){
        return oracleFunction.getSavingGoals(id);
    }
}
