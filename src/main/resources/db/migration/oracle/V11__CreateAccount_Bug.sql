CREATE OR REPLACE PROCEDURE createAccount(
        p_accID IN NUMBER,
        p_accName IN VARCHAR2,
        p_description IN VARCHAR2,
        p_initialAmount IN NUMBER,
        p_userID IN NUMBER,
        p_accountType IN VARCHAR2,
        p_category IN VARCHAR2,
        p_limit IN NUMBER
        ) 
AS
    v_accID NUMBER;
BEGIN
    INSERT INTO Account (accID, accName, description, balance, initialAmount, userID)
    VALUES (p_accID, p_accName, p_description, p_initialAmount, p_initialAmount, p_userID)
    RETURNING accID INTO v_accID;
    
    IF p_accountType = 'Expense' THEN
    INSERT INTO ExpenseAccount (accID, expenseCategory, spendingLimit)
    VALUES (v_accID, p_category, p_limit);
    
    ELSIF p_accountType = 'Fund' THEN
    INSERT INTO FundAccount(accID, fundType, minimumLimit)
    VALUES (v_accID, p_category, p_limit);
    
    END IF;    

END;