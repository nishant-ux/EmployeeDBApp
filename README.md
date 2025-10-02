# 🗄️ Employee Database Application (Java JDBC)

## 📌 Overview
This is my solution for **Task 7: Java JDBC – Employee Database App**.  
It is a **console-based CRUD system** to manage employee records using **Java + JDBC + MySQL**.

---

## ✅ What I Have Done
- Created a **Java JDBC connection** to MySQL database.
- Implemented full **CRUD operations**:
  - ➕ Add Employee
  - 👀 View Employees
  - ✏️ Update Employee
  - ❌ Delete Employee
- Used **PreparedStatement** to prevent SQL Injection.
- Displayed employee records using **ResultSet**.
- Designed a simple **menu-driven console interface**.
- Wrote a **README.md** for documentation.

---

## 🛠 Database Setup
1. Install **MySQL** and create a database:
   ```sql
   CREATE DATABASE employeedb;
   USE employeedb;
   CREATE TABLE employees (
       id INT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(100),
       age INT,
       department VARCHAR(50)
   );
