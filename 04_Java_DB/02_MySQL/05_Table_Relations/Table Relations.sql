# Lecture: Table Relations

CREATE DATABASE `table_relations`;
USE `table_relations`;

#Task 1. Mountains and Peaks.

CREATE TABLE `mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE `peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`mountain_id` INT,
CONSTRAINT fk_peak_mountains
FOREIGN KEY (`mountain_id`)
REFERENCES `mountains`(`id`)
);

#Task 2. Trip Organization.

SELECT v.`driver_id`, v.`vehicle_type`, CONCAT_WS(' ', c.`first_name`, c.`last_name`) AS 'driver_name' 
FROM `vehicles` AS v
JOIN `campers` AS c ON v.`driver_id`= c.`id`;

#Task 3. SoftUni Hiking.

SELECT 
    r.`starting_point` AS 'route_starting_point',
    r.`end_point` AS 'route_ending_point',
    r.`leader_id`,
    CONCAT_WS(' ', c.`first_name`, c.`last_name`) AS 'leader_name'
FROM
    `routes` AS r
        JOIN
    `campers` AS c ON c.`id` = r.`leader_id`;
    
#Task 4. Delete Mountains.

CREATE TABLE `mountains`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL
);

CREATE TABLE `peaks`(
`id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`mountain_id` INT,
CONSTRAINT fk_peak_mountains
FOREIGN KEY (`mountain_id`)
REFERENCES `mountains`(`id`) ON DELETE CASCADE
);

#Task 5. Project Management DB**

CREATE DATABASE `project_management_db`;
USE `project_management_db`;

CREATE TABLE `clients`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`client_name` VARCHAR (100)
);

CREATE TABLE `projects`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`client_id` INT, 
`project_lead_id` INT, 
CONSTRAINT fk_projects_clients
FOREIGN KEY `projects`(`client_id`)
REFERENCES `clients`(`id`)
);

CREATE TABLE `employees`(
`id` INT PRIMARY KEY AUTO_INCREMENT, 
`first_name` VARCHAR (30),
`last_name` VARCHAR (30), 
`project_id` INT, 
CONSTRAINT fk_employees_projects
FOREIGN KEY `employees`(`project_id`)
REFERENCES `projects`(`id`)
);

ALTER TABLE `projects`
ADD CONSTRAINT pf_projects_employees
FOREIGN KEY `projects`(`project_lead_id`)
REFERENCES `employees`(`id`);

# Exercise: Table Relations

CREATE DATABASE `table_relations`;
USE `table_relations`;

#Task 1. One-To-One Relationship.

CREATE TABLE `passports`(
`passport_id` INT PRIMARY KEY AUTO_INCREMENT,
`passport_number` VARCHAR(20) UNIQUE
) AUTO_INCREMENT = 101;

CREATE TABLE `people`(
`person_id` INT PRIMARY KEY AUTO_INCREMENT,
`first_name` VARCHAR(50),
`salary` DECIMAL(10,2),
`passport_id` INT UNIQUE,
CONSTRAINT fk_people_passports
FOREIGN KEY (`passport_id`)
REFERENCES `passports`(`passport_id`)
);

INSERT INTO `passports` (`passport_number`)
VALUES
('N34FG21B'),
('K65LO4R7'),
('ZE657QP2');

INSERT INTO `people` (`first_name`, `salary`, `passport_id`) 
VALUES
('Roberto', 43300.00, 102),
('Tom', 56100.00, 103),
('Yana', 60200.00, 101);

#Task 2. One-To-Many Relationship.

CREATE TABLE `manufacturers`(
`manufacturer_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) UNIQUE NOT NULL,
`established_on` DATE
);

CREATE TABLE `models`(
`model_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
`manufacturer_id` INT,
CONSTRAINT fk_models_manufacturers
FOREIGN KEY (`manufacturer_id`)
REFERENCES `manufacturers`(`manufacturer_id`)
) AUTO_INCREMENT = 101;

INSERT INTO `manufacturers` (`name`, `established_on`)
VALUES
('BMW', '1916-03-01'),
('Tesla', '2003-01-01'),
('Lada', '1966-05-01');

INSERT INTO `models` (`name`, `manufacturer_id`)
VALUES 
('X1', 1),
('i6', 1),
('Model S', 2),
('Model X', 2),
('Model 3', 2),
('Nova', 3);

#Task 3. Many-To-Many Relationship.

CREATE TABLE `students`(
`student_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);

CREATE TABLE `exams`(
`exam_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL
);

INSERT INTO `students`(`name`)
VALUES
('Mila'),
('Toni'),
('Ron');

ALTER TABLE `exams` AUTO_INCREMENT = 101;

INSERT INTO `exams`(`name`)
VALUES
('Spring MVC'),
('Neo4j'),
('Oracle 11g');

CREATE TABLE `students_exams`(
`student_id` INT,
`exam_id` INT,
CONSTRAINT pk_students_exams
PRIMARY KEY (`student_id`, `exam_id`),
CONSTRAINT fk_students_exams_students
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`),
CONSTRAINT fk_students_exams_exams
FOREIGN KEY (`exam_id`)
REFERENCES `exams`(`exam_id`)
);

INSERT INTO `students_exams`
VALUES
(1, 101),
(1, 102),
(2, 101),
(3, 103),
(2, 102),
(2, 103);

#Task 4. Self-Referencing.

CREATE TABLE `teachers`(
`teacher_id` INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) NOT NULL,
`manager_id` INT
);

ALTER TABLE `teachers` AUTO_INCREMENT = 101;

INSERT INTO `teachers`
VALUES
(101, 'John', NULL),
(102, 'Maya', 106),
(103, 'Silvia', 106),
(104, 'Ted', 105),
(105, 'Mark', 101),
(106, 'Greta', 101);

ALTER TABLE `teachers`
ADD CONSTRAINT fk_teacher_manager
FOREIGN KEY (`manager_id`)
REFERENCES `teachers`(`teacher_id`);

#Task 5. Online Store Database

CREATE TABLE `cities`(
`city_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE `customers`(
`customer_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
`birthday` DATE,
`city_id` INT(11),
CONSTRAINT fk_customers_cities
FOREIGN KEY (`city_id`)
REFERENCES `cities`(`city_id`)
);

CREATE TABLE `orders`(
`order_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`customer_id` INT(11),
CONSTRAINT fk_orders_customers
FOREIGN KEY (`customer_id`)
REFERENCES `customers`(`customer_id`)
);

CREATE TABLE `item_types`(
`item_type_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE `items`(
`item_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50),
`item_type_id` INT(11),
CONSTRAINT fk_items_item_types
FOREIGN KEY (`item_type_id`)
REFERENCES `item_types`(`item_type_id`)
);

CREATE TABLE `order_items`(
`order_id` INT(11),
`item_id` INT(11),
CONSTRAINT pk_order_items
PRIMARY KEY (`order_id`, `item_id`),
CONSTRAINT fk_order_items_orders
FOREIGN KEY (`order_id`)
REFERENCES `orders`(`order_id`),
CONSTRAINT fk_order_items_items
FOREIGN KEY (`item_id`)
REFERENCES `items`(`item_id`)
);

#Task 6. University Database.

CREATE TABLE `majors`(
`major_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE `students`(
`student_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`student_number` VARCHAR(12),
`student_name` VARCHAR(50),
`major_id` INT(11),
CONSTRAINT fk_students_majors
FOREIGN KEY (`major_id`)
REFERENCES `majors`(`major_id`)
);

CREATE TABLE `payments`(
`payment_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`payment_date` DATE,
`payment_amount` DECIMAL(8,2),
`student_id` INT(11),
CONSTRAINT fk_payments_students
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`)
);

CREATE TABLE `subjects`(
`subject_id` INT(11) PRIMARY KEY AUTO_INCREMENT,
`subject_name` VARCHAR(50)
);

CREATE TABLE `agenda`(
`student_id` INT(11),
`subject_id` INT(11),
CONSTRAINT pk_agenda_students_subjects
PRIMARY KEY (`student_id`, `subject_id`),
CONSTRAINT fk_agenda_students
FOREIGN KEY (`student_id`)
REFERENCES `students`(`student_id`),
CONSTRAINT fk_agenda_subjects
FOREIGN KEY (`subject_id`)
REFERENCES `subjects`(`subject_id`)
);

#Task 7. Peaks in Rila.

SELECT 
    m.`mountain_range`, p.`peak_name`, p.`elevation`
FROM
    `mountains` AS m
        JOIN
    `peaks` AS p ON m.`id` = p.`mountain_id`
WHERE
    m.`mountain_range` = 'Rila'
ORDER BY p.`elevation` DESC;