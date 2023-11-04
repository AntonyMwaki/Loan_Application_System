package com.mwananchibank.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mwananchibank.model.Customer;
import com.mwananchibank.utils.jdbc.MysqlDataAccess;

public class RetrieveCustomers {

    public static List<Customer> retrieveCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            MysqlDataAccess dataAccess = new MysqlDataAccess();
            Connection connection = dataAccess.connect();
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM customers;";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("customer_name");
                int nationalId = resultSet.getInt("national_id");
                String telephoneNumber = resultSet.getString("telephone_number");
                String email = resultSet.getString("email");
                String password = resultSet.getString("customer_password");

                Customer customer = new Customer(id, name, nationalId, telephoneNumber, email, password);
                customers.add(customer);
            }
            return customers;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
