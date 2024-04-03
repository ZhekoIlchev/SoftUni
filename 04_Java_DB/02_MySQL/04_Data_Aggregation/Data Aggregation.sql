# Lecture: Data Aggregation

#Task 1. Departments Info.

SELECT `department_id`, COUNT(*) AS 'Number of employees'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id` ASC, `Number of employees` ASC;

#Task 2. Average Salary.

SELECT `department_id`, ROUND(AVG(`salary`), 2) AS 'Average Salary'
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id` ASC;

#Task 3. Min Salary.

SELECT `department_id`, ROUND(MIN(`salary`), 2) AS 'Min Salary'
FROM `employees`
GROUP BY `department_id`
HAVING `Min Salary` > 800.00;

#Task 4. Appetizers Count.

SELECT COUNT(*) AS 'Count of appetizers'
FROM `products`
WHERE `price` > 8
GROUP BY `category_id`
HAVING `category_id` = 2;

#Task 5. Menu Prices.

SELECT `category_id`, ROUND(AVG(`price`), 2) AS 'Average Price', ROUND(MIN(`price`), 2) AS 'Cheapest Product', ROUND(MAX(`price`), 2) AS 'Most Expensive Product'
FROM `products`
GROUP BY `category_id`;

# Exercise: Data Aggregation

#Task 1. Records Count.

SELECT COUNT(*) AS 'count' 
FROM `wizzard_deposits`;

#Task 2. Longest Magic Wand.

SELECT MAX(`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits`;

#Task 3. Longest Magic Wand Per Deposit Groups.

SELECT `deposit_group`, MAX(`magic_wand_size`) AS 'longest_magic_wand'
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `longest_magic_wand` ASC, `deposit_group` ASC;

#Task 4. Smallest Deposit Group Per Magic Wand Size.

SELECT `deposit_group`
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY AVG(`magic_wand_size`) ASC
LIMIT 1;

#Task 5. Deposits Sum.

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
GROUP BY `deposit_group`
ORDER BY `total_sum` ASC;

#Task 6. Deposits Sum for Ollivander Family

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
ORDER BY `deposit_group` ASC;

#Task 7. Deposits Filter.

SELECT `deposit_group`, SUM(`deposit_amount`) AS 'total_sum'
FROM `wizzard_deposits`
WHERE `magic_wand_creator` = 'Ollivander family'
GROUP BY `deposit_group`
HAVING `total_sum` < 150000
ORDER BY `total_sum` DESC;

#Task 8. Deposit Charge.

SELECT `deposit_group`, `magic_wand_creator`, MIN(`deposit_charge`) AS 'min_deposit_charge'
FROM `wizzard_deposits`
GROUP BY `deposit_group`, `magic_wand_creator`
ORDER BY `magic_wand_creator` ASC, `deposit_group` ASC;

#Task 9. Age Groups.

SELECT 
(
	CASE
    WHEN `age` < 11 THEN '[0-10]'
    WHEN `age` < 21 THEN '[11-20]'
    WHEN `age` < 31 THEN '[21-30]'
    WHEN `age` < 41 THEN '[31-40]'
    WHEN `age` < 51 THEN '[41-50]'
    WHEN `age` < 61 THEN '[51-60]'
    ELSE '[61+]'
    END
) AS 'age_group', COUNT(*)
FROM `wizzard_deposits`
GROUP BY `age_group`
ORDER BY `age_group`;

#Task 10. First Letter.

SELECT LEFT(`first_name`, 1) AS 'first_letter'
FROM `wizzard_deposits`
WHERE `deposit_group` = 'Troll Chest'
GROUP BY `first_letter`
ORDER BY `first_letter`;

#Task 11. Average Interest.

SELECT `deposit_group`, `is_deposit_expired`, AVG(`deposit_interest`) AS 'avarage_interest'
FROM `wizzard_deposits`
WHERE `deposit_start_date` > '1985-01-01'
GROUP BY `deposit_group`, `is_deposit_expired`
ORDER BY `deposit_group` DESC, `is_deposit_expired` ASC;

#Task 12. Employees Minimum Salaries.

SELECT `department_id`, MIN(`salary`) AS 'minimum_salary'
FROM `employees`
WHERE `department_id` IN (2, 5, 7) AND `hire_date` > '2000-01-01'
GROUP BY `department_id`
ORDER BY `department_id` ASC;

#Task 13. Employees Average Salaries.

CREATE TABLE `hpe` AS
SELECT * FROM `employees`
WHERE `manager_id` != 42 AND `salary` > 30000;

UPDATE `hpe`
SET `salary` = `salary` + 5000
WHERE `department_id` = 1;

SELECT `department_id`, AVG(`salary`) AS 'avg_salary'
FROM `hpe`
GROUP BY `department_id`
ORDER BY `department_id` ASC;

#Task 14. Employees Maxim Salaries.

SELECT `department_id`, MAX(`salary`) AS 'max_salary'
FROM `employees`
GROUP BY `department_id`
HAVING `max_salary` NOT BETWEEN 30000 AND 70000
ORDER BY `department_id` ASC;

#Task 15. Employees Count Salaries.

SELECT COUNT(*) AS 'employees_without_manager'
FROM `employees`
WHERE `manager_id` IS NULL;

#Task 16. 3rd Highest Salary*

SELECT 
    e1.`department_id`,
    (SELECT DISTINCT
            e2.`salary`
        FROM
            `employees` AS `e2`
        WHERE
            e2.`department_id` = e1.`department_id`
        ORDER BY e2.`salary` DESC
        LIMIT 1 OFFSET 2) AS 'third_higest_salary'
FROM
    `employees` AS `e1`
GROUP BY e1.`department_id`
HAVING `third_higest_salary` IS NOT NULL
ORDER BY e1.`department_id` ASC;

#Task 17. Salary Challenge**.

SELECT 
    e1.`first_name`, e1.`last_name`, e1.`department_id`
FROM
    `employees` AS `e1`
WHERE
    e1.`salary` > (SELECT 
            AVG(e2.`salary`) AS 'avg_salary'
        FROM
            `employees` AS e2
        GROUP BY e2.`department_id`
        HAVING e2.`department_id` = e1.`department_id`)
ORDER BY e1.`department_id` ASC , e1.`employee_id` ASC
LIMIT 10;

#Task 18. Departments Todatl Salaries. 

SELECT `department_id`, SUM(`salary`)
FROM `employees`
GROUP BY `department_id`
ORDER BY `department_id`;