
CREATE TABLE `department` (
  `department_name` varchar(255) NOT NULL,
  `department_leader` varchar(255) DEFAULT NULL,
  `department_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `username` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `manager` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `status_employee` varchar(255) DEFAULT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FKl40vjp1eu7dkhs4nvi2nplkra` (`department_name`),
  CONSTRAINT `FKl40vjp1eu7dkhs4nvi2nplkra` FOREIGN KEY (`department_name`) REFERENCES `department` (`department_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;