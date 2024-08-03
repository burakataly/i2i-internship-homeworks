-- sql02-ex-01

select employee_id, first_name, last_name, round(MONTHS_BETWEEN(SYSDATE, hire_date)) number_of_months
from employees order by employee_id;


 -- sql02-ex-02

select employee_id, first_name, last_name, 
DECODE(job_id, 'AD_PRES', 'A', 'ST_MAN', 'B', 'IT_PROG', 'C', 'SA_REP', 'D', 'ST_CLERK', 'E', '0')
grade
from employees order by employee_id;


-- sql02-ex-03

SELECT employee_id, first_name, last_name, 
  CASE 
    WHEN job_id = 'AD_PRES' THEN 'A'
    WHEN job_id = 'ST_MAN' THEN 'B'
    WHEN job_id = 'IT_PROG' THEN 'C'
    WHEN job_id = 'SA_REP' THEN 'D'
    WHEN job_id = 'ST_CLERK' THEN 'E'
    ELSE '0'
  END AS grade
FROM employees ORDER BY employee_id;


-- sql02-ex-04

SELECT  e.employee_id, e.last_name
FROM employees e
WHERE e.department_id IN (
  SELECT DISTINCT department_id
  FROM employees
  WHERE last_name LIKE '%i%'
) order by employee_id;


-- sql02-ex-05

CREATE TABLE MY_EMP_TABLE (
    ID INT PRIMARY KEY,
    LAST_NAME VARCHAR(50),
    FIRST_NAME VARCHAR(50),
    SALARY DECIMAL(10, 2)
);

INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (1, 'Black', 'John', 1000.00);
INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (2, 'White', 'Kent', 2000.00);
INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (3, 'Orange', 'David', 3000.00);
INSERT INTO MY_EMP_TABLE (ID, LAST_NAME, FIRST_NAME, SALARY) VALUES (4, 'Pink', 'Alissa', 4000.00);

UPDATE MY_EMP_TABLE SET SALARY = SALARY * 1.10;

DELETE FROM MY_EMP_TABLE WHERE FIRST_NAME = 'David';

TRUNCATE TABLE MY_EMP_TABLE;

