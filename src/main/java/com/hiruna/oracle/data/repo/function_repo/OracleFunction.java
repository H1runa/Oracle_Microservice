package com.hiruna.oracle.data.repo.function_repo;

import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.dialect.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;

@Repository
public class OracleFunction {
    private JdbcTemplate jdbcTemplate;

    public OracleFunction(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<String> getUserAccountNames(Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("userAccountView");
        call.declareParameters(
            new SqlParameter("p_userID", Types.NUMERIC),
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String,Object> params = new HashMap<>();
        params.put("p_userID", id);

        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> cursorData = (List<Map<String, Object>>) result.get("RETURN_VALUE");

        List<String> accountNames = cursorData.stream()
            .map(row -> (String) row.get("ACCOUNTNAME"))
            .toList();

        return accountNames;

    }


    public List<Map<String,Object>> viewAccounts(String category, Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("viewAccounts");
        call.declareParameters(
            new SqlParameter("p_category", Types.VARCHAR),
            new SqlParameter("p_userID", Types.NUMERIC),
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String, Object> params = new HashMap<>();
        params.put("p_category", category);
        params.put("p_userID", id);

        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> cursorData = (List<Map<String, Object>>) result.get("RETURN_VALUE");
        
        return cursorData;
    }

    public List<Map<String,Object>> viewTransaction(Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("viewTransaction");
        call.declareParameters(
            new SqlParameter("v_accID", Types.NUMERIC),
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String, Object> params = new HashMap<>();
        params.put("v_accID", id);

        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> cursorData = (List<Map<String, Object>>) result.get("RETURN_VALUE");

        return cursorData;
    }

    public List<Map<String,Object>> viewBillReminder(Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("viewBillReminder");
        call.declareParameters(
            new SqlParameter("v_userID", Types.NUMERIC),
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String, Object> params = new HashMap<>();
        params.put("v_userID", id);

        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> cursorData = (List<Map<String, Object>>) result.get("RETURN_VALUE");

        return cursorData;
    }

    public List<Map<String,Object>> getUpcomingBillReminders(Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("getUpcomingBillReminders");
        call.declareParameters(
            new SqlParameter("v_userID", Types.NUMERIC),
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String, Object> params = new HashMap<>();
        params.put("v_userID", id);

        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> cursorData = (List<Map<String, Object>>) result.get("RETURN_VALUE");

        return cursorData;
    }

    public List<Map<String,Object>> getSavingGoals(Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("getSavingGoals");
        call.declareParameters(
            new SqlParameter("v_userID", Types.NUMERIC),
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String, Object> params = new HashMap<>();
        params.put("v_userID", id);

        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> cursorData = (List<Map<String, Object>>) result.get("RETURN_VALUE");

        return cursorData;
    }

    public String userLogin(String accountName, String password){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withProcedureName("userLogin");
        call.declareParameters(
            new SqlParameter("p_accountName", Types.VARCHAR),
            new SqlParameter("p_password", Types.VARCHAR),
            new SqlOutParameter("p_result", Types.VARCHAR)
        );

        Map<String,Object> params = new HashMap<>();
        params.put("p_accountName", accountName);
        params.put("p_password", password);

        Map<String, Object> result = call.execute(params);

        String status = (String) result.get("p_result");
        return status;
    }

    //report functions
    public List<Map<String,Object>> getMonthlyExpenditure(Long id){
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withFunctionName("getMonthlyExpenditure");
        call.declareParameters(
            new SqlParameter("v_id", Types.NUMERIC),            
            new SqlOutParameter("RETURN_VALUE", OracleTypes.CURSOR)
        );

        Map<String,Object> params = new HashMap<>();
        params.put("v_id", id);
        
        Map<String, Object> result = call.execute(params);

        @SuppressWarnings("unchecked")
        List<Map<String,Object>> cursorData = (List<Map<String,Object>>) result.get("RETURN_VALUE");

        return cursorData;
    }
 }
