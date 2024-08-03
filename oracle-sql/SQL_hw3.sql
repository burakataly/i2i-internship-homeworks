--1

SELECT department_id,
       LISTAGG(first_name || ' ' || last_name, '; ') WITHIN GROUP (ORDER BY first_name, last_name) AS employees
FROM employees
GROUP BY department_id;

--2

SELECT employee_id,
       first_name,
       last_name,
       job_id,
       hire_date,
       salary,
       SUM(salary) OVER (PARTITION BY job_id ORDER BY hire_date ROWS BETWEEN 1 PRECEDING AND 1 FOLLOWING) AS salary_total
FROM employees
ORDER BY hire_date;

--3

WITH ranked_employees AS (
  SELECT employee_id,
         first_name,
         last_name,
         department_id,
         salary,
         ROW_NUMBER() OVER (PARTITION BY department_id ORDER BY salary DESC) AS salary_rank
  FROM employees
)
SELECT employee_id,
       first_name,
       last_name,
       department_id,
       salary
FROM ranked_employees
WHERE salary_rank > 1
ORDER BY department_id, salary DESC;

--4

SELECT employee_id,
       first_name,
       last_name,
       job_id,
       salary,
       EXTRACT(year from hire_date) hire_year
FROM employees ORDER BY hire_year;

--5

SELECT employee_id,
       first_name,
       last_name,
       job_id,
       hire_date,
       salary,
       LAG(salary, 1, 0) OVER (ORDER BY hire_date) AS prev_salary,
       LEAD(salary, 1, 0) OVER (ORDER BY hire_date) AS next_salary
FROM employees
ORDER BY hire_date;