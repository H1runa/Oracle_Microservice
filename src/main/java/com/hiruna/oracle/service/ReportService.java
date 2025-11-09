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

    //expenditure report grouped by category
    public List<Map<String,Object>> categoryWiseExpenseReport(Long id){
        return oracleFunction.categoryWiseExpenseReport(id);
    }

    //budget tracking report
    public List<Map<String,Object>> budgetTrackingReport(Long id){
        return oracleFunction.budgetTrackingReport(id);
    }

    //forecasted savings report
    public List<Map<String,Object>> getForcastedSaving(Long id){
        return oracleFunction.getForcastedSaving(id);
    }
}
