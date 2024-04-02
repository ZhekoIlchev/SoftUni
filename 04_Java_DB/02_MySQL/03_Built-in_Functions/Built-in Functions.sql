# Lecture: Built-in Functions

#Task 1. Find Book Titles.

SELECT `title` FROM `books`
WHERE SUBSTRING(`title` FROM 1 FOR 3) = "The"
ORDER BY `id` ASC;

#Task 2. Replace Titles.

SELECT REPLACE(`title`, 'The', '***') AS 'title'
FROM `books`
WHERE `title` LIKE 'The%'
ORDER BY `id`;

#Task 3. Sum Cost of All Books.

SELECT ROUND(SUM(`cost`), 2) 
FROM `books`;

#Task 4. Days Lived.

SELECT CONCAT_WS(' ', `first_name`, `last_name`) AS 'Full Name', TIMESTAMPDIFF(DAY, `born`, `died`) AS 'Days Lived'  
FROM `authors`;

#Task 5. Harry Potter Books.

SELECT `title` FROM `books`
WHERE `title` LIKE 'Harry Potter%'
ORDER BY `id` ASC;

# Exercise: Built-in Functions

#Task 1. Find Names of All Employees by First Name.

SELECT `first_name`, `last_name`
FROM `employees`
WHERE `first_name` LIKE 'Sa%'
ORDER BY `employee_id` ASC;

#Task 2. Find Names of All Employees by Last Name.

SELECT `first_name`, `last_name`
FROM `employees`
WHERE `last_name` LIKE '%ei%'
ORDER BY `employee_id` ASC;

#Task 3. Find First Names of All Employees.

SELECT `first_name`
FROM `employees`
WHERE `department_id` IN (3, 10) AND EXTRACT(YEAR FROM `hire_date`) BETWEEN 1995 AND 2005
ORDER BY `employee_id` ASC;

#Task 4. Find All Employees Except Engineers.

SELECT `first_name`, `last_name` 
FROM `employees` 
WHERE `job_title` NOT LIKE '%engineer%'
ORDER BY `employee_id`;

#Task 5. Find Towns with Name Length.

SELECT `name` FROM `towns`
WHERE CHAR_LENGTH(`name`) IN (5, 6)
ORDER BY `name` ASC;

#Task 6. Find Towns Starting With.

SELECT `town_id`, `name`
FROM `towns`
WHERE LEFT(`name`, 1) IN ('M', 'K', 'B', 'E')
ORDER BY `name` ASC;

#Task 7. Find Towns Not Starting With.

SELECT `town_id`, `name`
FROM `towns`
WHERE LEFT(`name`, 1) NOT IN ('R', 'B', 'D')
ORDER BY `name` ASC;

#Task 8. Create View Employees Hired After 2000 Year.

CREATE VIEW v_employees_hired_after_2000 AS
SELECT `first_name`, `last_name`
FROM `employees`
WHERE EXTRACT(YEAR FROM `hire_date`) > 2000;

SELECT * FROM `v_employees_hired_after_2000`;

#Task 9. Length of Last Name.

SELECT `first_name`, `last_name`
FROM `employees`
WHERE CHAR_LENGTH(`last_name`) = 5;

#Task 10. Countries Holding A 3 or More Times.

SELECT `country_name`, `iso_code`
FROM `countries`
WHERE `country_name` LIKE '%A%A%A%'
ORDER BY `iso_code`;

#Task 11. Mix of Peak and River Names.

SELECT `peak_name`, `river_name`, LOWER(CONCAT(`peak_name`, SUBSTRING(`river_name`, 2))) AS 'mix'
FROM `peaks`, `rivers`
WHERE RIGHT(`peak_name`, 1) = LEFT(`river_name`, 1)
ORDER BY `mix` ASC; 

#Task 12. Games from 2011 and 2012 Year.

SELECT `name`, DATE_FORMAT(`start`,'%Y-%m-%d') AS 'start'
FROM `games`
WHERE YEAR(`start`) BETWEEN 2011 AND 2012
ORDER BY `start`
LIMIT 50;

#Task 13. User Email Providers.

SELECT `user_name`, SUBSTRING(`email`, LOCATE('@', `email`) + 1) AS 'email provider'
FROM `users`
ORDER BY `email provider` ASC, `user_name` ASC;

#Task 14. Get Users with IP Address Like Pattern.

SELECT `user_name`, `ip_address`
FROM `users`
WHERE `ip_address` LIKE '___.1%.%.___'
ORDER BY `user_name`;

#Task 15. Show All Games with Duration and Part of the Day.

SELECT 
    `name`,
    (CASE
        WHEN HOUR(`start`) < 12 THEN 'Morning'
        WHEN HOUR(`start`) < 18 THEN 'Afternoon'
        ELSE 'Evening'
    END) AS 'Parts of the day',
    (CASE
        WHEN `duration` < 4 THEN 'Extra Short'
        WHEN `duration` < 7 THEN 'Short'
        WHEN `duration` < 11 THEN 'Long'
        ELSE 'Extra Long'
    END) AS 'Duration'
FROM
    `games`;
    
#Task 16. Orders Table.

SELECT 
    `product_name`,
    `order_date`,
    DATE_ADD(`order_date`, INTERVAL 3 DAY) AS 'pay_due',
    DATE_ADD(`order_date`, INTERVAL 1 MONTH) AS 'deliver_due'
FROM
    `orders`;