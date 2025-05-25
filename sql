CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;
CREATE TABLE IF NOT EXISTS patients (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  age INT NOT NULL CHECK (age BETWEEN 1 AND 120),
  gender ENUM('M','F','O') NOT NULL,
  phone VARCHAR(10) NOT NULL UNIQUE CHECK (phone REGEXP '^[0-9]{10}$'),
  address TEXT NOT NULL,
  disease VARCHAR(100) NOT NULL,
  doctor VARCHAR(100) NOT NULL,
  appointment_date DATE NOT NULL
);