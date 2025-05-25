# Hospital-Reception-Management-System

A simple GUI-based logging system for a hospital reception management system. Built using Java (BlueJ) and connected to a MySQL (phpMyAdmin) database using JDBC via XAMPP.

---

## 💻 Requirements

- BlueJ IDE
- Java JDK (version 17 or above)
- XAMPP Control Panel
- MySQL (phpMyAdmin)
- MySQL JDBC Connector

---



## 🛠️ Project Setup

Follow these steps to set up and run the project on your local machine.



## ⚙️ How to Run the Project

### 🔹 Step 1: Set up the Database

1. Open **XAMPP** and start **Apache** & **MySQL**.
2. Open your browser → go to `http://localhost/phpmyadmin`.
3. Create a new database named `hospital_db`.
4. Import the SQL file from the `sql/hospital_db_schema.sql`.

### 🔹 Step 2: Setup in BlueJ

1. Open BlueJ and open this project.
2. Go to `Project > Use Library > Add External JARs` and add the MySQL JDBC connector JAR file.


### 🔹 Step 3: Run the App

1. Compile all `.java` files.
2. Right-click on `HospitalReception` → click `void main(String[] args)`.
3. App GUI will open.

---

## 🗃️ Database Design

### Database: `hospital_db`
### Table: `patient`

Fields include:
- 'id', 'name', 'age', 'gender', 'phone', 'address', 'disease', 'doctor', 'appointment_date'

SQL file is in: `sql/hospital_db_schema.sql`

---

## ✨ Features

- Simple GUI for log entry
- Connected to MySQL using JDBC


---


