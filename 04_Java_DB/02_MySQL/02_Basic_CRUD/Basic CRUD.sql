# Lecture: Basic CRUD

#Task 1. Select Employee Information.

SELECT `id`, `first_name`, `last_name`, `job_title`
FROM `employees`
ORDER BY `id` ASC;

#Task 2. Select Employees with Filter.

SELECT `id`, CONCAT_WS(' ', `first_name`, `last_name`) AS 'full_name', `job_title`, `salary`
FROM `employees`
WHERE `salary` > 1000.00
ORDER BY `id` ASC;

#Task 3. Update Employees Salary.

UPDATE `employees`
SET `salary` = `salary` + 100.00
WHERE `job_title` = 'Manager';

SELECT `salary` FROM `employees`;

#Task 4. Top Paid Employee.

CREATE VIEW v_top_paid_employee AS
SELECT * FROM `employees`
ORDER BY `salary` DESC
LIMIT 1;

SELECT * FROM v_top_paid_employee;

#Task 5. Select Employees by Multiple Filters.

SELECT * FROM `employees`
WHERE `department_id` = 4 AND `salary` >= 1000.00
ORDER BY `id` ASC;

#Task 6. Delete from Table.

DELETE FROM `employees`
WHERE `department_id` IN (1, 2);

SELECT * FROM `employees`
ORDER BY `id` ASC;

# Exercise: Basic CRUD

#Task 1. Find All Information About Departments.

SELECT * FROM `departments`
ORDER BY `department_id` ASC;

#Task 2. Find All Department Names.

SELECT `name` FROM `departments`
ORDER BY `department_id` ASC;

#Task 3. Find salary of Each Employee.

SELECT `first_name`, `last_name`, `salary`
FROM `employees`
ORDER BY `employee_id`;

#Task 4. Find Full Name of Each Employee.

SELECT `first_name`, `middle_name`, `last_name`
FROM `employees`
ORDER BY `employee_id`;

#Task 5. Find Email Address of Each Employee.

SELECT CONCAT(`first_name`, '.', `last_name`, '@softuni.bg') AS 'full_email_address'
FROM `employees`;

#Task 6. Find All Different Employees Salary.

SELECT DISTINCT `salary` FROM `employees`;

#Task 7. Find all Information About Employees.

SELECT * FROM `employees`
WHERE `job_title` = 'Sales Representative'
ORDER BY `employee_id` ASC;

#Task 8. Find Names of All Employees by salary in Range.

SELECT `first_name`, `last_name`, `job_title`
FROM `employees`
WHERE `salary` BETWEEN 20000 AND 30000
ORDER BY `employee_id` ASC;

#Task 9. Find Names of All Employees

SELECT CONCAT_WS(' ', `first_name`, `middle_name`, `last_name`) AS 'Full Name'
FROM `employees`
WHERE `salary` IN (25000, 14000, 12500, 23600);

#Task 10. Find All Employees Without Manager.

SELECT `first_name`, `last_name`
FROM `employees`
WHERE `manager_id` IS NULL;

#Task 11. Find All Employees with salary More Than 50000.

SELECT `first_name`, `last_name`, `salary`
FROM `employees`
WHERE `salary` > 50000
ORDER BY `salary` DESC;

#Task 12. Find 5 Best Paid Employees.

SELECT `first_name`, `last_name`
FROM `employees`
ORDER BY `salary` DESC
LIMIT 5;

#Task 13. Find All Employees Except Marketing.

SELECT `first_name`, `last_name`
FROM `employees`
WHERE `department_id` != 4;

#Task 14. Sort Employees Table.

SELECT * FROM `employees`
ORDER BY `salary` DESC, `first_name` ASC, `last_name` DESC, `middle_name` ASC;

#Task 15. Create View Employees with Salaries.

CREATE VIEW v_employees_salaries AS
SELECT `first_name`, `last_name`, `salary`
FROM `employees`;

SELECT * FROM `v_employees_salaries`;

#Task 16. Create View Employees with Job Titles.

CREATE VIEW v_employees_job_titles AS
SELECT CONCAT_WS(' ', `first_name`, `middle_name`, `last_name`) AS 'full_name', `job_title`
FROM `employees`;

SELECT * FROM `v_employees_job_titles`;

#Task 17. Distinct Job Titles.

SELECT DISTINCT `job_title`
FROM `employees`
ORDER BY `job_title` ASC;

#Task 18. Find First 10 Started Projects.

SELECT * FROM `projects`
ORDER BY `start_date` ASC, `name`
LIMIT 10;

#Task 19. Last 7 Hired Employees.

SELECT `first_name`, `last_name`, `hire_date`
FROM `employees`
ORDER BY `hire_date` DESC
LIMIT 7;

#Task 20. Increase Salaries.

UPDATE `employees`
SET `salary` = `salary` * 1.12
WHERE `department_id` IN (1, 2, 4, 11);

SELECT `salary` FROM `employees`;

#Task 21. All Mountain Peaks.

SELECT `peak_name` FROM `peaks`
ORDER BY `peak_name` ASC;

#Task 22. Biggest Countries by Population

SELECT `country_name`, `population`
FROM `countries`
WHERE `continent_code` = 'EU'
ORDER BY `population` DESC, `country_name` ASC
LIMIT 30;

#Task 23. Countries and Currency (Euro/Not Euro).

SELECT `country_name`, `country_code`, IF(`currency_code` = 'EUR', 'Euro', 'Not Euro') AS 'currency'
FROM `countries`
ORDER BY `country_name` ASC;

#Task 24. All Diablo Characters.

SELECT `name` FROM `characters`
ORDER BY `name` ASC;