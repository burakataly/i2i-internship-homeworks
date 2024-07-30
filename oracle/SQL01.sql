-- sql01-ex-01

select a.employee_id employee_id, a.first_name first_name, b.department_name from HR.employees a, HR.departments b
where a.department_id = b.department_id order by a.employee_id;


-- sql01-ex-02

select employee_id, manager_id from employees where manager_id IS not null order by employee_id ;


-- sql01-ex-03

SELECT SUBSTR(PHONE_NUMBER, 1, 3) operator,
COUNT(*) total FROM EMPLOYEES
GROUP BY SUBSTR(PHONE_NUMBER, 1, 3) ORDER BY operator;


-- sql01-ex-04

CREATE TABLE EMP AS
SELECT * FROM EMPLOYEES WHERE 0 = 1;

INSERT INTO EMP (SELECT * FROM EMPLOYEES WHERE employee_id = 105);

UPDATE EMP set phone_number = '555', salary = 1000 where employee_id = 105;

delete from emp where employee_id = 105;


-- sql01-ex-05

SELECT SUBSTR(first_name, 1, 2) || LPAD('*', LENGTH(first_name) - 2, '*') ||
' ' || SUBSTR(last_name, 1, 2) || LPAD('*', LENGTH(last_name) - 2, '*') 
AS employee_name FROM employees;









