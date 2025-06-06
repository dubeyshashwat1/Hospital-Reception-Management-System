# Hospital Reception Management System

A full-featured hospital reception management system built using Java Swing and MySQL, with secure login, patient record management, CSV export/import, reporting, and a modern GUI interface.

---

## Features Implemented

- Add, Update, Delete patient records  
- Unique ID generation  
- Search patients by ID, Name, Phone, or Doctor  
- CSV Export and Import of patient records  
- Daily, Monthly Reports and Patient Statistics  
- Dark/Light theme toggle  
- Validation for name, phone, age, and appointment date  
- Responsive and user-friendly UI for receptionists  

---

## Technologies Used

| Component         | Technology             |
|------------------|------------------------|
| Frontend UI      | Java Swing             |
| Backend Logic     | Java (OOP, JDBC)       |
| Database         | MySQL (phpMyAdmin)     |
| Development IDE  | BlueJ                  |
| Local Server     | XAMPP                  |
| Version Control  | Git and GitHub         |

---

## System Architecture

```
User Input (Java Swing GUI)
       ↓
Action Events / Listeners
       ↓
Java Backend Logic (CRUD, Validation)
       ↓
MySQL Database via JDBC
       ↓
Reports / CSV Export / UI Refresh
```

---

## FEATURES



- Login Screen  
- Patient Entry Form  
- Search/Filter View  
- Reports (Daily/Monthly/Statistics)  
- CSV File Opened in Excel  
- Example of Error Message on Invalid Input  

---

## How to Run

1. Clone the repo or download the zip  
2. Start XAMPP and open phpMyAdmin  
3. Create a database named `hospital_db`  
4. The table will auto-create on first launch  
5. Edit `config.properties` with your DB username and password  
6. Run the project using BlueJ or any Java IDE  
7. Login credentials:  
   - Username: admin  
   - Password: admin123

---

## Validation Details

- Name: Only alphabets and spaces (2–50 characters)  
- Phone: Exactly 10 digits  
- Age: Between 0 and 150  
- Date: Format YYYY-MM-DD  
- SQL injection is prevented using prepared statements

---

## Reports

- Daily Report: New patients and appointments for the current day  
- Monthly Report: Gender distribution, average age, total registrations  
- Statistics: Age range, gender breakdown, appointments summary

---

## Project Structure

```
├── /src
│   └── HospitalReceptionSystem.java
├── /screenshots
│   └── (login.png, entry.png, report.png, etc.)
├── config.properties
├── README.md
```

---

## Author

[SHASHWAT DUBEY]  
Hospital Reception System — Semester Project  
Course Code: [R1UC201C]  
Instructor: [GUVI TEAM]  
Submission Date: 10 June 2025

---

## Conclusion

This project helped me gain practical skills in GUI programming, database connectivity, input validation, and full-stack Java application development. It simulates a real-world hospital receptionist workflow with all essential operations.

---

## GitHub Repository Link

https://github.com/YourUsername/Hospital-Reception-Management-System

(Replace the link above with your actual GitHub repository URL)
