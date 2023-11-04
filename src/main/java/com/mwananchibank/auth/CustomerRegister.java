package com.mwananchibank.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import com.mwananchibank.model.Customer;
import com.mwananchibank.utils.jdbc.MysqlDataAccess;

public class CustomerRegister {
    public static void register(Scanner scanner) {
        try {
            MysqlDataAccess dataAccess = new MysqlDataAccess();
            Connection connection = dataAccess.connect();
            Statement statement = connection.createStatement();

            String createCustomersTables = "CREATE TABLE IF NOT EXISTS customers (customer_id INT PRIMARY KEY AUTO_INCREMENT, customer_name VARCHAR(255) NOT NULL, national_id INT NOT NULL, telephone_number VARCHAR(10) NOT NULL, email VARCHAR(255) NOT NULL, customer_password VARCHAR(255) NOT NULL);";
            statement.executeUpdate(createCustomersTables);

            String insertPreparedQuery = "INSERT INTO customers (customer_name, national_id, telephone_number, email, customer_password)values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertPreparedQuery);

            Customer customer = getCustomer(scanner);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setInt(2, customer.getNationalID());
            preparedStatement.setString(3, customer.getTelephoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setString(5, customer.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Customer getCustomer(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter National Id: ");
        int nationalId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Telephone Number: ");
        String telephoneNumber = scanner.nextLine();
        System.out.println("Enter Email: ");
        String email = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();


        Customer customer = new Customer(name, nationalId, telephoneNumber, email, password);
        return customer;

    }
}
