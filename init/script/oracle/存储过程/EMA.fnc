CREATE OR REPLACE FUNCTION EMA
(curValue NUMBER,
 lastEmaValue NUMBER,
 N INT
) 
RETURN NUMBER IS
curEmaValue NUMBER;
BEGIN
   curEmaValue := lastEmaValue*(N-1)/(N+1)+curValue*2/(N+1);
   RETURN curEmaValue;
   EXCEPTION
     WHEN NO_DATA_FOUND THEN
       NULL;
     WHEN OTHERS THEN
       -- Consider logging the error and then re-raise
       RAISE;
END EMA;



/
