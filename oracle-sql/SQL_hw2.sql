--SQL03-EX-01

ALTER TABLE employees
ADD MAX_SALARY DECIMAL(10, 2);

UPDATE employees
SET MAX_SALARY = salary + NVL(salary * commission_pct / 100, 0);


update employees
set max_salary = (select max(salary) from employees);

delete from employees
where salary = (select min(salary) from employees);


--SQL03-EX-02

CREATE INDEX DPR_NAME_IDX
ON DEPARTMENTS (DEPARTMENT_NAME);

ALTER TABLE employees
ADD CONSTRAINT CNSTR_SALARY
CHECK (salary BETWEEN 1000 AND 100000);

update employees
set salary = 6000 where employee_id = 125;

DROP INDEX DPR_NAME_IDX;

ALTER TABLE employees
DISABLE CONSTRAINT CNSTR_SALARY;

ALTER TABLE employees
ENABLE CONSTRAINT CNSTR_SALARY;

ALTER TABLE employees
DROP CONSTRAINT CNSTR_SALARY;


--SQL03-EX-03

CREATE TABLE new_table AS
SELECT DISTINCT department_id
FROM employees;

ALTER TABLE new_table
add department_name varchar(40);

MERGE INTO new_table a
USING departments b
ON (a.department_id = b.department_id)
WHEN MATCHED THEN
  UPDATE SET a.department_name = b.department_name
WHEN NOT MATCHED THEN
  INSERT (a.department_id, a.department_name)
  VALUES (b.department_id, b.department_name);
 

--SQL03-EX-04
 
with f_employees as (select first_name, last_name, job_id, department_id from employees where last_name like 'S%')
select * from f_employees;

with f_employees as 
(select a.first_name, a.last_name, a.job_id, a.department_id, b.job_title, b.min_salary, b.MAX_SALARY, c.department_name
from employees a, jobs b, departments c where a.last_name like 'S%' and a.job_id = b.job_id and a.department_id = c.department_id)
select * from f_employees;

with f_employees as 
(select a.first_name || ' ' || a.last_name full_name, a.job_id, a.department_id, b.job_title, b.min_salary, b.MAX_SALARY, c.department_name
from employees a, jobs b, departments c where a.last_name like 'S%' and a.job_id = b.job_id and a.department_id = c.department_id)
select * from f_employees;



