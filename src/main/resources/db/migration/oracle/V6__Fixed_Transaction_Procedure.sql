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