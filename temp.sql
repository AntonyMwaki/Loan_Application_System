CREATE TABLE IF NOT EXISTS customers (customer_id INT PRIMARY KEY AUTO_INCREMENT, customer_name VARCHAR(255) NOT NULL, national_id INT NOT NULL, telephone_number VARCHAR(10) NOT NULL, email VARCHAR(255) NOT NULL, customer_password VARCHAR(255) NOT NULL);

INSERT INTO customers (customer_name, national_id, telephone_number, email, customer_password)values(?,?,?,?,?);

CREATE TABLE IF NOT EXISTS applications (application_id INT PRIMARY KEY AUTO_INCREMENT, loan_amount DECIMAL(12,2) NOT NULL, loan_purpose VARCHAR(255) NOT NULL, loan_terms VARCHAR(255) NOT NULL, loan_status VARCHAR(255) NOT NULL, customer_id INT NOT NULL);