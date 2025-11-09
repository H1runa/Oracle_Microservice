package com.hiruna.oracle.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hiruna.oracle.data.model.SavingGoal;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SavingGoalRepo extends JpaRepository<SavingGoal, Long> {
    
}
