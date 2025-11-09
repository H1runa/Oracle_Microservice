package com.hiruna.oracle.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.hiruna.oracle.data.model.AccountTransaction;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface AccountTransactionRepo extends JpaRepository<AccountTransaction, Long> {
    @Procedure(procedureName = "addTransaction")
    void addTransaction(String v_transName, String v_transType, Double v_amount, Long v_accID);
}
