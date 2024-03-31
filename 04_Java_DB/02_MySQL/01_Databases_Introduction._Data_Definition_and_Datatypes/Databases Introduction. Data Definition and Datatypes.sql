# Lecture: Databases Introduction. Data Definition and Datatypes

#Task 1. Create Tables.

CREATE DATABASE `gamebar`;
USE `gamebar`;

CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50) NOT NULL,
`last_name` VARCHAR(50) NOT NULL 
);

CREATE TABLE `categories` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

CREATE TABLE `products` (
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`category_id` INT NOT NULL
);

#Task 2. Insert Data in Tables.

INSERT INTO `employees` (`first_name`, `last_name`)
VALUES
('Boyan', 'Ivanov'),
('Vanina', 'Todorova'),
('Anna', 'Stoimenova');

#Task 3. Alter Tables.

ALTER TABLE `employees`
ADD COLUMN `middle_name` VARCHAR(50) NOT NULL;

#Task 4. Adding Constraints.

ALTER TABLE `products`
ADD CONSTRAINT fk_products_categories
FOREIGN KEY `products`(`category_id`)
REFERENCES `categories`(`id`);

#Task 5. Modifying Columns.

ALTER TABLE `employees`
CHANGE COLUMN `middle_name` `middle_name` VARCHAR(100);

# Exercise: Data Definition and Datatypes

#Task 1. Create Tables.

CREATE DATABASE `minions`;
USE `minions`;

CREATE TABLE `minions`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL,
`age` INT
);

CREATE TABLE `towns`(
`town_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) NOT NULL
);

#Task 2. Alter Minions Table.

ALTER TABLE `towns`
DROP PRIMARY KEY,
CHANGE COLUMN `town_id` `id` INT PRIMARY KEY AUTO_INCREMENT;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT NOT NULL,
ADD CONSTRAINT fk_minions_towns
FOREIGN KEY `minions`(`town_id`)
REFERENCES `towns`(`id`);

#Task 3. Insert Records in Both Tables.

INSERT INTO `towns`(`id`, `name`)
VALUES
(1, 'Sofia'),
(2, 'Plovdiv'),
(3, 'Varna');

INSERT INTO `minions`(`id`, `name`, `age`, `town_id`)
VALUES 
(1, 'Kevin', 22, 1),
(2, 'Bob', 15, 3),
(3, 'Steward', NULL, 2);

#Task 4. Truncate Minions.

TRUNCATE `minions`;

#Task 5. Drop All Tables.

DROP TABLE `minions`;
DROP TABLE `towns`;

#Task 6. Create Table People.

CREATE TABLE `people`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` FLOAT(5,2),
`weight` FLOAT(5,2),
`gender` CHAR(1) NOT NULL,
`birthdate` DATE NOT NULL, 
`biography` TEXT
);

INSERT INTO `people`(`name`, `gender`, `birthdate`)
VALUES
('Andon', 'M', '1992-08-01'),
('Aleksander', 'M', '1992-08-02'),
('Cvetelina', 'F', '1992-08-03'),
('Katrin', 'F', '1991-08-07'),
('Peter', 'M', '1992-08-04');

#Task 7. Create Table Users.

CREATE TABLE `users`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` TIME,
`is_deleted` BOOLEAN
);

INSERT INTO `users` (`username`, `password`, `profile_picture`, `last_login_time`, `is_deleted`)
VALUES
('Andon', 'ldkfa',  NULL, '05:01:09', TRUE),
('Aleksander', 'alvmka',  NULL, '01:03:06', TRUE),
('Cvetelina', 'mvkfjg',  NULL, '24:05:05', FALSE),
('Katrin', 'fsda',  NULL, '15:07:01', FALSE),
('Peter', 'paosmvls',  NULL, '10:09:11', TRUE);

#Task 8. Change Primary Key.

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users 
PRIMARY KEY (`id`, `username`);

#Task 9. Set Default Value of a Field.

ALTER TABLE `users`
CHANGE COLUMN `last_login_time` `last_login_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

#Task 10. Set Unique Field.

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD CONSTRAINT pk_users
PRIMARY KEY `users`(`id`);

ALTER TABLE `users`
CHANGE COLUMN `username` `username` VARCHAR(30) UNIQUE NOT NULL;

#Task 11. Movies Database.

CREATE DATABASE `movies`;
USE `movies`;

CREATE TABLE `directors`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`director_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `genres`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`genre_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `categories`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`category_name` VARCHAR(50) NOT NULL,
`notes` TEXT
);

CREATE TABLE `movies`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`title` VARCHAR(50) NOT NULL, 
`director_id` INT, 
`copyright_year` DATE, 
`length`TIME, 
`genre_id` INT, 
`category_id` INT, 
`rating` FLOAT(4, 2), 
`notes` TEXT
);

INSERT INTO `directors`
VALUES
(1, 'Ivan Ivanov', 'sdasdasqe'),
(2, 'Ivana Ivanova', 'sd'),
(3, 'Todor Todorov', 'sdsqe'),
(4, 'Todorka Todorova', 'sdfdfdfqe'),
(5, 'Simeon Simeonov', 'sdasdasqe');

INSERT INTO `genres`
VALUES
(1, 'Thriller', NULL),
(2, 'Action', NULL),
(3, 'Romance', NULL),
(4, 'Mystery', NULL),
(5, 'Fantasy', NULL);

INSERT INTO `categories` 
VALUES
(1, 'Country', NULL),
(2, 'Race', NULL),
(3, 'Nature', NULL),
(4, 'Ocean', NULL),
(5, 'Sea', NULL);

INSERT INTO `movies`
VALUES 
(1, 'Batman', 1, '2000-05-05', '02:53:57', 1, 1, 5.76, NULL),
(2, 'Kingdom Of Heaven', 2, '2000-05-06', '02:53:57', 2, 2, 5.77, NULL),
(3, 'Last Samurai', 3, '2000-05-07', '02:53:57', 3, 3, 5.78, NULL),
(4, 'Gladiator', 4, '2000-05-08', '02:53:57', 4, 4, 5.79, NULL),
(5, 'Interstelar', 5, '2000-05-09', '02:53:57', 5, 5, 5.80, NULL);

#Task 12. Car Rental Database.

CREATE DATABASE `car_rental`;
USE `car_rental`;

CREATE TABLE `categories` (
 `id` INT PRIMARY KEY AUTO_INCREMENT, 
 `category` VARCHAR(50), 
 `daily_rate` FLOAT(3,2), 
 `weekly_rate` FLOAT(3,2), 
 `monthly_rate` FLOAT(3,2), 
 `weekend_rate` FLOAT(3,2)
 );
 
INSERT INTO `categories` (`category`, `daily_rate`, `weekly_rate`, `monthly_rate`, `weekend_rate`)
VALUES
('Sport', 1.11, 1.11, 1.11, 1.11),
('Famaly', 2.11, 2.11, 2.11, 2.11),
('Luxary', 3.11, 3.11, 3.11, 3.11);

CREATE TABLE `cars` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`plate_number` INT, 
`make` VARCHAR(50), 
`model` VARCHAR(50), 
`car_year` DATE, 
`category_id` INT, 
`doors` INT, 
`picture` BLOB, 
`car_condition` BOOLEAN, 
`available` BOOLEAN
);

INSERT INTO `cars`(`plate_number`, `make`, `model`, `car_year`, `category_id`, `doors`, `picture`, `car_condition`, `available`)
VALUES
(28349, 'asdas', 'asdasd', '2000-05-05', 1, 4, NULL, TRUE, TRUE),
(28349, 'asdas', 'asdasd', '2000-05-05', 1, 4, NULL, FALSE, TRUE),
(28349, 'asdas', 'asdasd', '2000-05-05', 1, 4, NULL, TRUE, TRUE);

CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`first_name` VARCHAR(50), 
`last_name` VARCHAR(50), 
`title` VARCHAR(50), 
`notes` TEXT
);

INSERT INTO `employees` (`first_name`, `last_name`, `title`, `notes`)
VALUES
('Ivan', 'Ivanov', 'Some title', 'Notes'),
('Tedora', 'Ninova', 'Some title', 'Notes'),
('Drago', 'Soimenov', 'Some title', 'Notes');

CREATE TABLE `customers` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`driver_licence_number` INT, 
`full_name` VARCHAR(37), 
`address` VARCHAR(37), 
`city` VARCHAR(37), 
`zip_code` INT, 
`notes` TEXT
);

INSERT INTO `customers` (`driver_licence_number`, `full_name`, `address`, `city`, `zip_code`, `notes`)
VALUES
(121231, 'Ivan Stoimenov', 'Some address...', 'Sofia', 1000, 'Notes...'),
(1211, 'Drago Ivanov', 'Some address...', 'Plovdiv', 4000, 'Notes...'),
(11, 'Nina Todorova', 'Some address...', 'Varna', 9000, 'Notes...');

CREATE TABLE `rental_orders` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`employee_id` INT, 
`customer_id` INT, 
`car_id` INT , 
`car_condition` BOOLEAN, 
`tank_level` INT, 
`kilometrage_start` INT, 
`kilometrage_end` INT, 
`total_kilometrage` INT, 
`start_date` DATE, 
`end_date` DATE, 
`total_days` INT, 
`rate_applied` FLOAT(3, 2), 
`tax_rate` FLOAT(3, 2), 
`order_status` BOOLEAN, 
`notes` TEXT
);

INSERT INTO `rental_orders`(`employee_id`, `customer_id`, `car_id`, `car_condition`, `tank_level`, `kilometrage_start`, `kilometrage_end`, `total_kilometrage`, `start_date`, `end_date`, `total_days`, `rate_applied`, `tax_rate`, `order_status`, `notes`)
VALUES
(1, 1, 1, TRUE, 1, 1, 1, 1, '2000-05-05', '2000-05-05', 1, 3.25, 3.25, TRUE, 'Notes...'),
(2, 2, 2, FALSE, 2, 2, 2, 2, '2000-05-05', '2000-05-05', 1, 3.25, 3.25, FALSE, 'Notes...'),
(3, 3, 3, TRUE, 3, 3, 3, 3, '2000-05-05', '2000-05-05', 1, 3.25, 3.25, TRUE, 'Notes...');

#Task 13. Basic Insert.

CREATE DATABASE `soft_uni`;
USE `soft_uni`;

CREATE TABLE `towns` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`name` VARCHAR(50)
);

INSERT INTO `towns`(`name`)
VALUES
('Sofia'),
('Plovdiv'),
('Varna'),
('Burgas');

CREATE TABLE `addresses` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`address_text` TEXT, 
`town_id` INT
);

INSERT INTO `addresses` (`address_text`, `town_id`)
VALUES
('Address 1...', 1),
('Address 1...', 2),
('Address 1...', 3),
('Address 1...', 4);

CREATE TABLE `departments` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`name` VARCHAR(50)
);

INSERT INTO `departments`(`name`)
VALUES
('Engineering'),
('Sales'),
('Marketing'),
('Software Development'),
('Quality Assurance');

CREATE TABLE `employees` (
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`first_name` VARCHAR(50), 
`middle_name` VARCHAR(50), 
`last_name` VARCHAR(50), 
`job_title` VARCHAR(50), 
`department_id` INT, 
`hire_date` DATE, 
`salary` DECIMAL(10, 2), 
`address_id` INT
);

INSERT INTO `employees` (`first_name`, `middle_name`, `last_name`, `job_title`, `department_id`, `hire_date`, `salary`, `address_id`)
VALUES
('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00, 1),
('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00, 2),
('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25, 3),
('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00, 4),
('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88, 1);

#Task 14. Basic Select All Fields.

SELECT * FROM `towns`;
SELECT * FROM `departments`;
SELECT * FROM `employees`;

#Task 15. Basic Select All Fields and Order Them.

SELECT * FROM `towns`
ORDER BY `name`;

SELECT * FROM `departments`
ORDER BY `name`;

SELECT * FROM `employees`
ORDER BY `salary` DESC;

#Task 16. Basic Select Some Fields.

SELECT `name` FROM `towns`
ORDER BY `name`;

SELECT `name` FROM `departments`
ORDER BY `name`;

SELECT `first_name`, `last_name`, `job_title`, `salary` FROM `employees`
ORDER BY `salary` DESC;

#Task 17. Increase Employees Salary.

UPDATE `employees`
SET `salary` = `salary` * 1.1;

SELECT `salary` FROM `employees`;