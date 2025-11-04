

--Trigger to Update the balance in Account  and check bill reminder and add data to billReminderHistory
CREATE OR REPLACE TRIGGER updateBalance 
AFTER INSERT ON AccountTransaction 
FOR EACH ROW
DECLARE
    v_count NUMBER;
    v_billDetails BillReminder%ROWTYPE;
BEGIN
    IF :NEW.transTYPE = 'Expense' THEN 
    UPDATE ACCOUNT SET balance = balance - :NEW.amount WHERE accID = :NEW.accID;
    ELSE 
    UPDATE ACCOUNT SET balance = balance + :NEW.amount WHERE accID = :NEW.accID;
    END IF;
    
    --Delete the bill reminder and add to history
    SELECT COUNT(*) INTO v_count FROM BillReminder WHERE remindName = :NEW.transName;
    IF v_count > 0 THEN
        SELECT * INTO v_billDetails FROM BillReminder WHERE remindName = :NEW.transName;
        INSERT INTO BillReminderHistory (remindID, remindName, deadline, status, userID) VALUES (v_billDetails.remindID, v_billDetails.remindName, v_billDetails.deadline, 'Paid', v_billDetails.userID );
        DELETE FROM BillReminder WHERE remindID = v_billDetails.remindID;
    END IF;
END;

--Test
BEGIN
    addTransaction('data', 'Expense',500, 24);
END;


--View Transactions
CREATE OR REPLACE FUNCTION viewTransaction(v_accID NUMBER)
RETURN SYS_REFCURSOR
IS 
details SYS_REFCURSOR;
BEGIN
    OPEN details FOR 
    SELECT * FROM AccountTransaction WHERE accID = v_accID;
    RETURN details;
END;

--Test
SET SERVEROUTPUT  ON;
DECLARE
    details SYS_REFCURSOR;
    rec AccountTransaction%ROWTYPE;
BEGIN
    details := viewTransaction(1);
    LOOP
    FETCH details INTO rec;
    EXIT WHEN details%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(rec.transName || '  ' || rec.transType || '  ' || rec.transDate || ' ' || rec.amount);
    END LOOP;
    CLOSE details;
END;

--Edit Transaction
CREATE OR REPLACE PROCEDURE editTransaction(v_transID IN NUMBER, v_transName IN VARCHAR2, v_transType IN VARCHAR2, v_amount IN NUMBER) IS
BEGIN
    UPDATE AccountTransaction SET transName = v_transName, transType = v_transType, amount =  v_amount WHERE transID = v_transID;
END;

--Trigger to update the balance in Account;
CREATE OR REPLACE TRIGGER trig_updateBalance_whenEditingTransaction 
AFTER UPDATE ON AccountTransaction
FOR EACH ROW
BEGIN
    IF :OLD.transType = 'Deposit' AND :NEW.transType = 'Expense' THEN
    UPDATE Account SET balance = balance - (:OLD.amount + :NEW.amount) WHERE accID = :OLD.accID;
    
    ELSIF :OLD.transType = 'Expense' AND :NEW.transType = 'Deposit' THEN 
    UPDATE Account SET balance = balance + (:OLD.amount + :NEW.amount) WHERE accID = :OLD.accID;
    
    ELSIF :OLD.transType = 'Deposit' AND :NEW.transType = 'Deposit' THEN 
    UPDATE Account SET balance = balance - :OLD.amount + :NEW.amount WHERE accID = :OLD.accID;
    
    ELSIF :OLD.transType = 'Expense' AND :NEW.transType = 'Expense' THEN 
    UPDATE Account SET balance = balance + :OLD.amount - :NEW.amount WHERE accID = :OLD.accID;
    
    END IF;
END;

--Test
BEGIN
    editTransaction(3, 'updatedFood2', 'Deposit', 1000);
END;

--Delete Transaction
CREATE OR REPLACE PROCEDURE deleteTransaction(v_transID IN NUMBER) IS 
BEGIN
    DELETE FROM AccountTransaction WHERE transID = v_transID;
END;

--Trigger to update the balance after delete a transaction;
CREATE OR REPLACE TRIGGER trig_updateBalance_afterDeletingTransaction 
AFTER DELETE ON AccountTransaction
FOR EACH ROW
BEGIN
    IF :OLD.transType = 'Deposit' THEN
    UPDATE ACCOUNT SET balance = balance - :OLD.amount WHERE accID = :OLD.accID;
    ELSE 
    UPDATE ACCOUNT SET balance = balance + :OLD.amount WHERE accID = :OLD.accID;
    END IF;
END;

--Test
BEGIN
    deleteTransaction(2);
END;
