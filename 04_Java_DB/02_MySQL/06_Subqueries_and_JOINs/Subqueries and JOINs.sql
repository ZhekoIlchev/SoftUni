# Lecture: Subqueries and JOINs

#Task 1. Maagers.

SELECT 
    e.`employee_id`,
    CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS 'full_name',
    d.`department_id`,
    d.`name` AS 'department_name'
FROM
    `departments` AS d
        JOIN
    `employees` AS e ON d.`manager_id` = e.`employee_id`
ORDER BY e.`employee_id`
LIMIT 5;

#Task 2. Towns Addresses.

SELECT 
    t.`town_id`, t.`name` AS 'town_name', a.`address_text`
FROM
    `towns` AS t
        JOIN
    `addresses` AS a USING (`town_id`)
WHERE
    t.`name` IN ('San Francisco' , 'Sofia', 'Carnation')
ORDER BY t.`town_id` ASC , a.`address_id` ASC;

#Task 3. Employees Without Managers.

SELECT `employee_id`, `first_name`, `last_name`, `department_id`, `salary`
FROM `employees`
WHERE `manager_id` IS NULL;

#Task 4. Higher Salary.

SELECT 
    COUNT(*) AS 'count'
FROM
    `employees`
WHERE
    `salary` > (SELECT 
            AVG(`salary`)
        FROM
            `employees`);
            
# Exercise: Subqueries and JOINs

#Task 1. Employee Addresses.

SELECT 
    e.`employee_id`,
    e.`job_title`,
    a.`address_id`,
    a.`address_text`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a USING (`address_id`)
ORDER BY e.`address_id`
LIMIT 5;

#Task 2. Addresses with Towns.

SELECT 
    e.`first_name`,
    e.`last_name`,
    t.`name` AS 'town',
    a.`address_text`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a USING (`address_id`)
        JOIN
    `towns` AS t ON a.`town_id` = t.`town_id`
ORDER BY e.`first_name` ASC , e.`last_name` ASC
LIMIT 5;

#Task 3. Sales Employees. 

SELECT 
    e.`employee_id`,
    e.`first_name`,
    e.`last_name`,
    d.`name` AS 'department_name'
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    d.`name` = 'Sales'
ORDER BY e.`employee_id` DESC;

#Task 4. Employee Departments. 

SELECT 
    e.`employee_id`,
    e.`first_name`,
    e.`salary`,
    d.`name` AS 'department_name'
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    e.`salary` > 15000
ORDER BY d.`department_id` DESC
LIMIT 5;

#Task 5. Employees Without Project. 

SELECT 
    e.`employee_id`, e.`first_name`
FROM
    `employees` AS e
        LEFT JOIN
    `employees_projects` AS ep USING (`employee_id`)
WHERE
    `project_id` IS NULL
ORDER BY e.`employee_id` DESC
LIMIT 3;

#Task 6. Employees Hired After. 

SELECT 
    e.`first_name`,
    e.`last_name`,
    e.`hire_date`,
    d.`name` AS 'dept_name'
FROM
    `employees` AS e
        JOIN
    `departments` AS d USING (`department_id`)
WHERE
    YEAR(e.`hire_date`) > 1999 - 01 - 01
        AND d.`name` IN ('Sales' , 'Finance')
ORDER BY e.`hire_date` ASC;

#Task 7. Employees with Project. 

SELECT 
    e.`employee_id`, e.`first_name`, p.`name` AS 'project_name'
FROM
    `employees` AS e
        LEFT JOIN
    `employees_projects` AS ep ON e.`employee_id` = ep.`employee_id`
        LEFT JOIN
    `projects` AS p ON ep.`project_id` = p.`project_id`
WHERE
   DATE(p.`start_date`) > '2002-08-13'
        AND p.`end_date` IS NULL
ORDER BY e.`first_name` ASC , p.`name` ASC
LIMIT 5;

#Task 8. Employee 24. 

SELECT 
    e.`employee_id`,
    e.`first_name`,
    IF(YEAR(p.`start_date`) >= 2005,
        NULL,
        p.`name`) AS 'project_name'
FROM
    `employees` AS e
        JOIN
    `employees_projects` AS ep ON e.`employee_id` = ep.`employee_id`
        JOIN
    `projects` AS p ON ep.`project_id` = p.`project_id`
WHERE
    e.`employee_id` = 24
ORDER BY `project_name` ASC;

#Task 9. Employee Manager. 

SELECT 
    e.`employee_id`,
    e.`first_name`,
    e.`manager_id`,
    (SELECT 
            s.`first_name`
        FROM
            `employees` AS s
        WHERE
            s.`employee_id` = e.`manager_id`) AS 'manager_name'
FROM
    `employees` AS e
        LEFT JOIN
    `employees` AS emp_m ON e.`employee_id` = e.`manager_id`
WHERE
    e.`manager_id` IN (3 , 7)
ORDER BY e.`first_name` ASC;

#Task 10. Employee Summary. 

SELECT 
    e.`employee_id`,
    CONCAT_WS(' ', e.`first_name`, e.`last_name`) AS 'employee_name',
    CONCAT_WS(' ', emp_m.`first_name`, emp_m.`last_name`) AS 'manager_name',
    d.`name` AS 'department_name'
FROM
    `employees` AS e
        JOIN
    `employees` AS emp_m ON e.`manager_id` = emp_m.`employee_id`
        JOIN
    `departments` AS d ON e.`department_id` = d.`department_id`
ORDER BY e.`employee_id`
LIMIT 5;

#Task 11. Min Average Salary. 

SELECT AVG(e.`salary`) AS 'min_average_salary'
FROM `employees` AS e
GROUP BY `department_id`
ORDER BY `min_average_salary` ASC
LIMIT 1;

#Task 12. Highest Peaks in Bulgaria. 

SELECT 
    mc.`country_code`,
    m.`mountain_range`,
    p.`peak_name`,
    p.`elevation`
FROM
    `mountains_countries` AS mc
        JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
        JOIN
    `peaks` AS p ON m.`id` = p.`mountain_id`
WHERE
    mc.`country_code` = 'BG'
        AND p.`elevation` > 2835
ORDER BY p.`elevation` DESC;

#Task 13. Count Mountain Ranges. 

SELECT 
    mc.`country_code`, COUNT(m.`id`) AS 'mountain_range'
FROM
    `mountains_countries` AS mc
        JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
WHERE
    mc.`country_code` IN ('BG' , 'RU', 'US')
GROUP BY mc.`country_code`
ORDER BY `mountain_range` DESC;

#Task 14. Countries with Rivers. 

SELECT 
    c.`country_name`, r.`river_name`
FROM
    `countries` AS c
       LEFT JOIN
    `countries_rivers` AS cr ON c.`country_code` = cr.`country_code`
        LEFT JOIN
    `rivers` AS r ON cr.`river_id` = r.`id`
        LEFT JOIN
    `continents` AS cont ON c.`continent_code` = cont.`continent_code`
WHERE
    cont.`continent_name` = 'Africa'
ORDER BY c.`country_name` ASC
LIMIT 5;

#Task 15. Continents and Currencies.*** 

SELECT 
    c.`continent_code`,
    c.`currency_code`,
    COUNT(c.`country_name`) AS 'currency_usage'
FROM
    `countries` AS c
GROUP BY c.`continent_code` , c.`currency_code`
HAVING `currency_usage` = (SELECT 
        COUNT(c1.`country_code`) AS 'count'
    FROM
        `countries` AS c1
    WHERE
        c1.`continent_code` = c.`continent_code`
    GROUP BY `currency_code`
    ORDER BY `count` DESC
    LIMIT 1)
    AND `currency_usage` > 1
ORDER BY c.`continent_code` ASC , c.`currency_code` ASC;

#Task 16. Countries Without Any Mountains. 

SELECT 
    COUNT(*) AS 'country_count'
FROM
    `countries` AS c
        LEFT JOIN
    `mountains_countries` AS mc USING (`country_code`)
        LEFT JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
WHERE
    m.`mountain_range` IS NULL;
    
#Task 17. Highest Peak and Longest River by Country. 

SELECT 
    c.`country_name`,
    MAX(p.`elevation`) AS 'highest_peak_elevation',
    MAX(r.`length`) AS 'longest_river_length'
FROM
    `countries` AS c
        JOIN
    `mountains_countries` AS mc ON c.`country_code` = mc.`country_code`
        JOIN
    `mountains` AS m ON mc.`mountain_id` = m.`id`
        JOIN
    `peaks` AS p ON m.`id` = p.`mountain_id`
        JOIN
    `countries_rivers` AS cr ON c.`country_code` = cr.`country_code`
        JOIN
    `rivers` AS r ON cr.`river_id` = r.`id`
GROUP BY c.`country_name`
ORDER BY `highest_peak_elevation` DESC , `longest_river_length` DESC , c.`country_name` ASC
LIMIT 5;