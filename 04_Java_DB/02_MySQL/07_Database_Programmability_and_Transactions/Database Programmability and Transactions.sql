#Lecture: Database Programmability and Transactions.

#Task 1. Count Employees by Town.

DELIMITER &&
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN
	DECLARE e_count INT;
    SET e_count := (SELECT 
    COUNT(e.`employee_id`) AS 'count'
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON e.`address_id` = a.`address_id`
        JOIN
    `towns` AS t ON a.`town_id` = t.`town_id`
WHERE
    t.`name` = town_name);
    
	RETURN e_count;
END &&

DELIMITER ;
SELECT ufn_count_employees_by_town('Sofia');
SELECT ufn_count_employees_by_town('Berlin');
SELECT ufn_count_employees_by_town(NULL);

#Task 2. Employees Promotion.

DELIMITER &&
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(30))
BEGIN

UPDATE `employees` AS e
        JOIN
    `departments` AS d ON e.`department_id` = d.`department_id` 
SET 
    e.`salary` = e.`salary` * 1.05
WHERE
    d.`name` = department_name;

END &&

DELIMITER ;
CALL usp_raise_salaries('Finance');

SELECT `first_name`, `salary`
FROM `employees`
WHERE `department_id` = 10
ORDER BY `first_name` ASC, `salary` ASC;

#Task 3. Employees Promotion by ID.

DELIMITER &&
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
BEGIN
	START TRANSACTION;
    IF 
    (SELECT COUNT(id) FROM `employees`
    WHERE id > 0) 
    THEN 
    UPDATE `employees` AS e
    SET e.`salary` = e.`salary` * 1.05
    WHERE e.`employee_id` = id;
    ELSE 
    ROLLBACK;
    END IF;

END &&

DELIMITER ;
CALL usp_raise_salary_by_id(1);

#Task 4. Triggered. 

CREATE TABLE `deleted_employees`(
`employee_id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50),
`last_name` VARCHAR(50),
`middle_name` VARCHAR(50),
`job_title` VARCHAR(30),
`department_id` INT,
`salary` DECIMAL(19,4)
);

DELIMITER &&
CREATE TRIGGER deleted_employees
AFTER DELETE
ON `employees`
FOR EACH ROW
BEGIN

INSERT INTO `deleted_employees`(`first_name`, `last_name`, `middle_name`, `job_title`, `department_id`, `salary`)
VALUES
(OLD.`first_name`, OLD.`last_name`, OLD.`middle_name`, 
	OLD.`job_title`, OLD.`department_id`, OLD.`salary`);

END &&

DELIMITER ;
DELETE FROM `employees`
WHERE `employee_id` = 1;

#Exercise: Functions and Procedures.

#Task 1. Employees with Salary Above 35000. 

DELIMITER &&
CREATE PROCEDURE usp_get_employees_salary_above_35000()
BEGIN

	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE `salary` > 35000
    ORDER BY `first_name` ASC, `last_name` ASC, `employee_id` ASC;
    
END &&

DELIMITER ;
CALL usp_get_employees_salary_above_35000();

#Task 2. Employees with Salary Above Number.

DELIMITER &&
CREATE PROCEDURE usp_get_employees_salary_above(above_salary DECIMAL(19, 4))
BEGIN
	
    SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE `salary` >= above_salary
    ORDER BY `first_name` ASC, `last_name` ASC, `employee_id` ASC;
    
END &&

DELIMITER ;
CALL usp_get_employees_salary_above(45000);

#Task 3. Town Names Starting With. 

DELIMITER &&
CREATE PROCEDURE usp_get_towns_starting_with(starts_with VARCHAR(30))
BEGIN

	SELECT `name` 
    FROM `towns`
    WHERE `name` LIKE CONCAT(starts_with, '%')
    ORDER BY `name`;

END &&

DELIMITER ;
CALL usp_get_towns_starting_with('b');

#Task 4. Employees from Town. 

DELIMITER &&
CREATE PROCEDURE usp_get_employees_from_town(town_name VARCHAR(30))
BEGIN

	SELECT 
    e.`first_name`, e.`last_name`
FROM
    `employees` AS e
        JOIN
    `addresses` AS a ON e.`address_id` = a.`address_id`
        JOIN
    `towns` AS t ON a.`town_id` = t.`town_id`
WHERE
    t.`name` = town_name
ORDER BY e.`first_name` ASC , e.`last_name` ASC , e.`employee_id` ASC;
    
END &&

DELIMITER ;
CALL usp_get_employees_from_town('Sofia');

#Task 5. Salary Level Function.

DELIMITER &&
CREATE FUNCTION ufn_get_salary_level(salary DECIMAL(19,4))
RETURNS VARCHAR(7)
DETERMINISTIC
BEGIN
	DECLARE salary_level VARCHAR(7);
    
    SET salary_level := 
	CASE
    WHEN `salary` < 30000 THEN 'Low'
    WHEN `salary` BETWEEN 30000 AND 50000 THEN 'Average'
    ELSE 'High'
	END;
    
    RETURN salary_level;
END &&

DELIMITER ;
SELECT ufn_get_salary_level(51000);

#Task 6. Employees by Salary Level

DELIMITER &&
CREATE PROCEDURE usp_get_employees_by_salary_level(salary_level VARCHAR(7))
BEGIN

	SELECT `first_name`, `last_name`
    FROM `employees`
    WHERE ufn_get_salary_level(`salary`) = salary_level
    ORDER BY `first_name` DESC, `last_name` DESC;
    
END &&

DELIMITER ;
CALL usp_get_employees_by_salary_level('high');

#Task 7. Define Function. 

DELIMITER &&
CREATE FUNCTION ufn_is_word_comprised(set_of_letters VARCHAR(50), word VARCHAR(50))
RETURNS INT
DETERMINISTIC
BEGIN 
	DECLARE state INT;
    
    SET state := 
    (SELECT LOWER(word) REGEXP CONCAT('^[', set_of_letters, ']+$')); 
	
    RETURN state;

END &&

#Task 8. Find Full Name. 

DELIMITER &&
CREATE PROCEDURE usp_get_holders_full_name()
BEGIN
	
    SELECT CONCAT_WS(' ', `first_name`, `last_name`) AS 'full_name'
    FROM `account_holders`
    ORDER BY `full_name` ASC, `id` ASC;

END &&

CALL usp_get_holders_full_name;

#Task 9. People with Balance Higher Than.

DELIMITER &&
CREATE PROCEDURE usp_get_holders_with_balance_higher_than(balance_above DECIMAL(19,4))
BEGIN
	
SELECT 
    `first_name`, ah.`last_name`
FROM
    `accounts` AS a
        JOIN
    `account_holders` AS ah ON a.`account_holder_id` = ah.`id`
GROUP BY a.`account_holder_id`
HAVING SUM(a.`balance`) > balance_above
ORDER BY a.`account_holder_id` ASC;
    
END &&

DELIMITER ;
CALL usp_get_holders_with_balance_higher_than(7000);

#Task 10. Future Value Function.

DELIMITER &&
CREATE FUNCTION ufn_calculate_future_value(sum DECIMAL(19,4), yearly_interest_rate DECIMAL(19,4), number_of_years INT)
RETURNS DECIMAL(19,4)
DETERMINISTIC
BEGIN

RETURN sum * pow((1 + yearly_interest_rate), number_of_years);

END &&

DELIMITER ;
SELECT ufn_calculate_future_value(1000, 0.5, 5);

#Task 11. Calculate Interest. 

DELIMITER &&
CREATE PROCEDURE usp_calculate_future_value_for_account(account_id INT, interest_rate DECIMAL(19,4))
BEGIN

SELECT a.`id` AS 'account_id',
		ah.`first_name`, 
        ah.`last_name`,
        a.`balance` AS 'current_balance',
        ufn_calculate_future_value(a.`balance`, interest_rate, 5) AS 'balance_in_5_years'
FROM `account_holders` AS ah
JOIN `accounts` AS a
ON ah.`id` = a.`account_holder_id`
WHERE a.`id` = account_id;

END &&

DELIMITER ;
CALL usp_calculate_future_value_for_account(1, 0.1);

#Task 12. Deposit Money. 

DELIMITER &&
CREATE PROCEDURE usp_deposit_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    IF 
    (SELECT `id` FROM `accounts`
    WHERE `id` = account_id) IS NULL 
    OR money_amount < 0
    THEN ROLLBACK;
    ELSE 
    UPDATE `accounts`
    SET `balance` = `balance` + money_amount
    WHERE `id` = account_id;
    END IF;

END &&

DELIMITER ;
CALL usp_deposit_money(1, 10);

SELECT `id`, `balance`
FROM `accounts`;

#Task 13. Withdraw Money. 

DELIMITER &&
CREATE PROCEDURE usp_withdraw_money(account_id INT, money_amount DECIMAL(19, 4))
BEGIN
	START TRANSACTION;
    
	IF 
    (SELECT `id` FROM `accounts`WHERE `id` = account_id) IS NULL
    OR money_amount < 0 
    OR 
    (SELECT `balance` FROM `accounts` WHERE `id` = account_id) < money_amount
    THEN ROLLBACK;
    ELSE 
    UPDATE `accounts`
    SET `balance` = `balance` - money_amount
    WHERE `id` = account_id;
    END IF;

END &&;

DELIMITER ;
CALL usp_withdraw_money(1, 10);

SELECT `id`, `balance`
FROM `accounts`;

#Task 14. Money Transfer. 

DELIMITER &&
CREATE PROCEDURE usp_transfer_money(from_account_id INT, to_account_id INT, amount DECIMAL(19, 4))
BEGIN
	IF 
		amount > 0
		AND
		(SELECT `id` FROM `accounts` WHERE `id` = from_account_id) IS NOT NULL
		AND
		(SELECT `id` FROM `accounts` WHERE `id` = to_account_id) IS NOT NULL
		THEN
		START TRANSACTION;
    
		UPDATE `accounts`
		SET `balance` = `balance` - amount
		WHERE `id` = from_account_id;
    
		UPDATE `accounts`
		SET `balance` = `balance` + amount
		WHERE `id` = to_account_id;
    
		IF (SELECT `balance` FROM `accounts` WHERE `id` = from_account_id) < 0
		THEN ROLLBACK;
		ELSE COMMIT;
		END IF;
        
	END IF;
END &&

DELIMITER ;
CALL usp_transfer_money(1, 2, 10);

SELECT `id`, `balance`
FROM `accounts`;

#Task 15. Log Accounts Trigger. 

CREATE TABLE `logs`(
`log_id` INT PRIMARY KEY AUTO_INCREMENT,
`account_id` INT, 
`old_sum` DECIMAL(19, 4),
`new_sum` DECIMAL(19, 4)
);

DELIMITER &&
CREATE TRIGGER updated_balance
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN

	INSERT INTO `logs`(`account_id`, `old_sum`, `new_sum`)
    VALUES
    (OLD.`id`, OLD.`balance`, NEW.`balance`);

END &&

DELIMITER ;
DROP TRIGGER IF EXISTS soft_uni.updated_balance;

SELECT * FROM `logs`;
CALL usp_transfer_money(1, 2, 10);
SELECT * FROM `logs`;

#Task 16. Emails Trigger. 

CREATE TABLE `logs`(
`log_id` INT PRIMARY KEY AUTO_INCREMENT, 
`account_id` INT, 
`old_sum` DECIMAL(19, 4), 
`new_sum` DECIMAL(19, 4)
);

DELIMITER &&
CREATE TRIGGER tr_update_balance_accounts
AFTER UPDATE
ON `accounts`
FOR EACH ROW
BEGIN
	INSERT INTO `logs` (`account_id`, `old_sum`, `new_sum`)
    VALUES
    (OLD.`id`, OLD.`balance`, NEW.`balance`);
END &&

DELIMITER ;
CREATE TABLE `notification_emails`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`recipient` INT, 
`subject` TEXT, 
`body` TEXT); 

DELIMITER &&
CREATE TRIGGER tr_update_balance_logs
AFTER INSERT
ON `logs`
FOR EACH ROW
BEGIN
	INSERT INTO `notification_emails` (`recipient`, `subject`, `body`)
    VALUES
    (NEW.`log_id`, CONCAT_WS(' ', 'Balance change for account:', NEW.`log_id`), CONCAT('On ', DATE_FORMAT(NOW(), '%b %d %Y'), ' at ', DATE_FORMAT(NOW(), '%r'), ' your balance was changed from ', NEW.`old_sum`, ' to ', NEW.`new_sum`, '.'));
END &&

DELIMITER ;
SELECT * FROM `logs`;
SELECT * FROM `notification_emails`;
CALL usp_transfer_money(1, 2, 10);
SELECT * FROM `logs`;
SELECT * FROM `notification_emails`;