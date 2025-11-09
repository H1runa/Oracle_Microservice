CREATE OR REPLACE TRIGGER trg_goal_completed
FOR INSERT OR UPDATE OF amount ON AccountTransaction
COMPOUND TRIGGER

    TYPE t_accIDList IS TABLE OF NUMBER;
    accIDs t_accIDList := t_accIDList();

AFTER EACH ROW IS
BEGIN
    accIDs.EXTEND;
    accIDs(accIDs.LAST) := :NEW.accID;
END AFTER EACH ROW;

AFTER STATEMENT IS
    v_total NUMBER;
BEGIN
    -- Loop through each distinct accID in the collection
    FOR i IN 1 .. accIDs.COUNT LOOP
        -- Calculate total deposits for this account
        SELECT NVL(SUM(amount), 0)
        INTO v_total
        FROM AccountTransaction
        WHERE accID = accIDs(i)
          AND transType = 'Deposit';

        -- Check all saving goals linked to this account
        FOR r_goal IN (
            SELECT goalName, targetAmount
            FROM SavingGoal
            WHERE accID = accIDs(i)
        ) LOOP
            IF v_total >= r_goal.targetAmount THEN
                DBMS_OUTPUT.PUT_LINE(
                    'Goal "' || r_goal.goalName || '" reached! Total Saved: ' || v_total
                );
            END IF;
        END LOOP;
    END LOOP;
END AFTER STATEMENT;

END trg_goal_completed;
/