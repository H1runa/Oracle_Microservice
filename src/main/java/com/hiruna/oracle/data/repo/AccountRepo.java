package com.hiruna.oracle.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.hiruna.oracle.data.model.Account;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AccountRepo extends JpaRepository<Account, Long>{
    @Procedure(procedureName = "createAccount")
    void createAccount(Long p_accID, String p_accName, String p_description, Double p_initialAmount, Long p_userID, String p_accountType, String p_category, Double p_limit);

    @Procedure(procedureName = "updateAccount")
    void updateAccount(String p_accName, String p_description, Double p_limit, String p_category, Long p_accID);
}
