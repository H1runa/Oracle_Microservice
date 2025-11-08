CREATE OR REPLACE PROCEDURE updateAccount(
            p_accName IN VARCHAR2,
            p_description IN VARCHAR2,
            p_limit IN NUMBER,
            p_category IN VARCHAR2,
            p_accID IN NUMBER)
AS
BEGIN 
    UPDATE Account 
    SET accName = p_accName,
        description = p_description
    WHERE accID = p_accID;
    
    IF p_category IN ('Entertainment', 'BillPayment', 'DailyExpense') THEN
    UPDATE ExpenseAccount 
    SET spendingLimit = p_limit
    WHERE accID = p_accID;
    
    ELSIF p_category IN ('LongTermSaving', 'EmergencyFund') THEN
    UPDATE FundAccount
    SET minimumLimit = p_limit
    WHERE accID = p_accID;
    
    END IF;
    
END;
/

-- DELETE ACCOUNT
CREATE OR REPLACE PROCEDURE deleteAccount(
                p_category IN VARCHAR2,
                p_accID IN NUMBER)
AS
BEGIN
    IF p_category IN ('Entertainment', 'BillPayment', 'DailyExpense') THEN
    DELETE FROM ExpenseAccount
    WHERE accID = p_accID;
    
    ELSIF p_category IN ('LongTermSaving', 'EmergencyFund') THEN
    DELETE FROM FundAccount
    WHERE accID = p_accID;
    
    END IF;     
    
    DELETE FROM Account
    WHERE accID = p_accID;
END;