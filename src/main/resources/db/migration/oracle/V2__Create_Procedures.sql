--System


--denura
-- create account
CREATE OR REPLACE PROCEDURE createAccount(
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
    INSERT INTO Account (accName, description, balance, initialAmount, userID)
    VALUES (p_accName, p_description, p_initialAmount, p_initialAmount, p_userID)
    RETURNING accID INTO v_accID;
    
    IF p_accountType = 'Expense' THEN
    INSERT INTO ExpenseAccount (accID, expenseCategory, spendingLimit)
    VALUES (v_accID, p_category, p_limit);
    
    ELSE IF p_accountType = 'Fund' THEN
    INSERT INTO FundAccount(accID, fundType, minimumLimit)
    VALUES (v_accID, p_category, p_limit);
    
    END IF;
    END IF;

END;


-- EXECUTE createAccount PROCEDURE 
BEGIN 
    createAccount('Emergency Fund Account', 'Backup fund for urgent needs', 5000, 1, 'Fund', 'EmergencyFund', 500);
END;




-- VIEW ANY ACCOUNT USING DROPDOWN
CREATE OR REPLACE FUNCTION viewAccounts(
                p_category IN VARCHAR2,
                p_userID IN NUMBER) 
RETURN SYS_REFCURSOR
IS 
    v_cursor SYS_REFCURSOR;
BEGIN 
    IF p_category IN ('Entertainment', 'BillPayment', 'DailyExpense') THEN
    OPEN v_cursor FOR
        SELECT
            a.accName,
            a.description,
            a.balance,
            a.createdDate,
            a.status,
            a.initialAmount,
            e.spendingLimit
            FROM Account a 
            JOIN ExpenseAccount e ON a.accID = e.accID 
            WHERE e.expenseCategory = p_category 
            AND a.userID = p_userID;
            
    ELSE IF p_category IN ('LongTermSaving', 'EmergencyFund') THEN
    OPEN v_cursor FOR
        SELECT
            a.accName,
            a.description,
            a.balance,
            a.createdDate,
            a.status,
            a.initialAmount,
            f.minimumLimit
            FROM Account a 
            JOIN FundAccount f ON a.accID = f.accID 
            WHERE f.fundType = p_category 
            AND a.userID = p_userID;
            
            END IF;
            END IF;
            
            RETURN v_cursor;
END;




-- EXECUTE viewAccounts FUNCTION
VARIABLE rc REFCURSOR;

BEGIN 
    :rc := viewAccounts('Entertainment', 1);
END;

PRINT rc;

-- UPDATE ACCOUNT
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
    
    ELSE IF p_category IN ('LongTermSaving', 'EmergencyFund') THEN
    UPDATE FundAccount
    SET minimumLimit = p_limit
    WHERE accID = p_accID;
    
    END IF;
    END IF;
    
END;


-- EXECUTE updateAccount PROCEDURE
BEGIN
    updateAccount('Entertainmen Account', 'FILMS, GANMES, SPORT', 1100, 'Entertainment', 23);
END;



-- DELETE ACCOUNT
CREATE OR REPLACE PROCEDURE deleteAccount(
                p_category IN VARCHAR2,
                p_accID IN NUMBER)
AS
BEGIN
    IF p_category IN ('Entertainment', 'BillPayment', 'DailyExpense') THEN
    DELETE FROM ExpenseAccount
    WHERE accID = p_accID;
    
    ELSE IF p_category IN ('LongTermSaving', 'EmergencyFund') THEN
    DELETE FROM FundAccount
    WHERE accID = p_accID;
    
    END IF; 
    END IF;
    
    DELETE FROM Account
    WHERE accID = p_accID;
END;



-- EXECUTE deleteAccount PROCEDURE
BEGIN 
    deleteAccount('LongTermSaving', 6);
END;

--End denura






--Add Transaction
CREATE OR REPLACE PROCEDURE addTransaction(v_transName IN VARCHAR2, v_transType IN VARCHAR2, v_amount IN NUMBER, v_accID IN NUMBER) IS
v_fundAccount NUMBER;
v_expenseAccount NUMBER;
v_balance NUMBER;
v_spendingLimit NUMBER;
v_minimumLimit NUMBER;
BEGIN
    --check amount exceed the spending limit.
    SELECT COUNT(*) INTO v_expenseAccount FROM ExpenseAccount WHERE accID = v_accID;
    IF v_expenseAccount > 0 THEN
        SELECT spendingLimit INTO v_spendingLimit FROM ExpenseAccount WHERE accID = v_accID;
        SELECT balance INTO v_balance FROM Account WHERE accID = v_accID;
    
    IF v_transType = 'Expense' THEN 
        IF v_balance - v_amount < v_spendingLimit THEN 
            RAISE_APPLICATION_ERROR(-20001, 'Transaction exceed the spending limit');
        END IF;
    END IF;
    END IF;
    
    --check amount exceed the minimun limit.
    SELECT COUNT(*) INTO v_minimumLimit FROM FundAccount WHERE accID = v_accID;
    IF v_minimumLimit > 0 THEN 
        SELECT minimumLimit INTO v_minimumLimit FROM FundAccount WHERE accID = v_accID;
        SELECT balance INTO v_balance FROM ACCOUNT WHERE accID = v_accID;
    
    IF v_transType = 'Expense' THEN 
        IF v_balance - v_amount < v_minimumLimit THEN 
            RAISE_APPLICATION_ERROR(-20002, 'Transaction exceed the minimum limit');
        END IF;
    END IF;
    END IF;
            
    INSERT INTO AccountTransaction (transName, transType, amount, accID) VALUES (v_transName, v_transType, v_amount, v_accID);
END;