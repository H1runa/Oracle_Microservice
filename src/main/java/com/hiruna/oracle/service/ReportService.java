package com.hiruna.oracle.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hiruna.oracle.data.repo.function_repo.OracleFunction;

@Service
public class ReportService {
    private OracleFunction oracleFunction;

    public ReportService(OracleFunction oracleFunction){
        this.oracleFunction=oracleFunction;
    }

    //monthly expenditure
    public List<Map<String,Object>> getMonthlyExpenditure(Long id){
        return oracleFunction.getMonthlyExpenditure(id);
    }
    
}
