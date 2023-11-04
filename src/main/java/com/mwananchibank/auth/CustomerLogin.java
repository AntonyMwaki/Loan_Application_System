package com.mwananchibank.auth;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mwananchibank.model.Customer;
import com.mwananchibank.service.RetrieveCustomers;

public class CustomerLogin {

    public static Customer loggedInCustomer(Scanner scanner) {
        List<Customer> customers = RetrieveCustomers.retrieveCustomers();
        scanner.nextLine();
        int retry = 0;
        while (retry <= 2) {
            System.out.println();
            System.out.println("Enter your email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();

            for (Customer customer : customers) {
                if (customer.getEmail().equalsIgnoreCase(email) && customer.getPassword().equalsIgnoreCase(password)) {
                    System.out.println("Login successful, Welcome");
                    return customer;
                }
            }

            if (retry == 2) {
                System.out.println("Invalid password or email. Login failed");
            } else {
                System.out.println("Invalid password or email. Retry");
            }
            retry++;
        }
        return null;
    }
}
