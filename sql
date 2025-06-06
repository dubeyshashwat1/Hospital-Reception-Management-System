-- Create the database
CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

-- Create patients table (matches the Java code)
CREATE TABLE IF NOT EXISTS patients (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(10) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address TEXT,
    disease VARCHAR(200),
    appointment_date DATE,
    doctor VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



-- Create a view for active appointments
CREATE VIEW active_appointments AS
SELECT id, name, phone, appointment_date, doctor 
FROM patients 
WHERE appointment_date IS NOT NULL 
ORDER BY appointment_date;

-- Create a view for patient statistics
CREATE VIEW patient_stats AS
SELECT 
    COUNT(*) AS total_patients,
    AVG(age) AS average_age,
    MIN(age) AS min_age,
    MAX(age) AS max_age,
    SUM(CASE WHEN gender = 'Male' THEN 1 ELSE 0 END) AS male_count,
    SUM(CASE WHEN gender = 'Female' THEN 1 ELSE 0 END) AS female_count,
    SUM(CASE WHEN appointment_date IS NOT NULL THEN 1 ELSE 0 END) AS with_appointments
FROM patients;

-- Create a user for the application (adjust password as needed)
CREATE USER 'hospital_admin'@'localhost' IDENTIFIED BY 'securepassword123';
GRANT ALL PRIVILEGES ON hospital_db.* TO 'hospital_admin'@'localhost';
FLUSH PRIVILEGES;
