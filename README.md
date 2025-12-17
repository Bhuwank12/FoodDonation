PART 1: DATABASE SETUP (MySQL)
1️⃣ Open MySQL command line or MySQL Workbench
2️⃣ Create Database
      CREATE DATABASE food_donation;
      USE food_donation;

3️⃣ Create USERS table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    role VARCHAR(20)
);


4️⃣ Create DONATIONS table
CREATE TABLE donations (
    donation_id INT AUTO_INCREMENT PRIMARY KEY,
    donor_id INT,
    food_type VARCHAR(50),
    quantity INT,
    status VARCHAR(20),
    FOREIGN KEY (donor_id) REFERENCES users(id)
);


5️⃣ Insert ADMIN user
INSERT INTO users (name, email, password, role)
VALUES ('Admin', 'admin@gmail.com', 'admin123', 'ADMIN');


Then Write your mysql credentials in DBConnection.java
