package com.mwananchibank.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import com.mwananchibank.model.Customer;
import com.mwananchibank.model.LoanApplication;
import com.mwananchibank.model.LoanStatus;
import com.mwananchibank.utils.jdbc.MysqlDataAccess;

public class MakeLoanApplication {

    public static void applyLoan(Customer customer, Scanner scanner) {
        try {
            MysqlDataAccess dataAccess = new MysqlDataAccess();
            Connection connection = dataAccess.connect();
            Statement statement = connection.createStatement();

            String createLoanApplicationsTable = "CREATE TABLE IF NOT EXISTS applications (application_id INT PRIMARY KEY AUTO_INCREMENT, loan_amount DECIMAL(12,2) NOT NULL, loan_purpose VARCHAR(255) NOT NULL, loan_terms VARCHAR(255) NOT NULL, loan_status VARCHAR(255) NOT NULL, customer_id INT NOT NULL);";
            statement.executeUpdate(createLoanApplicationsTable);

            String insertPreparedQuery = "INSERT INTO applications (loan_amount, loan_purpose, loan_terms, loan_status, customer_id)values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertPreparedQuery);

            LoanApplication application = getNewLoanApplication(customer, scanner);
            preparedStatement.setBigDecimal(1, application.getLoanAmount());
            preparedStatement.setString(2, application.getLoanPurpose());
            preparedStatement.setString(3, application.getLoanTerms());
            preparedStatement.setString(4, application.getLoanStatus().name());
            preparedStatement.setInt(5, application.getCustomer().getId());
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static LoanApplication getNewLoanApplication(Customer customer, Scanner scanner) {

        System.out.println();
        System.out.println("Enter loan amount:");
        BigDecimal amount = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Enter Purpose of the loan");
        String purpose = scanner.nextLine();
        System.out.println("Enter repayment preferences");
        String preferences = scanner.nextLine();

        LoanApplication application = new LoanApplication(amount, purpose, preferences, LoanStatus.INPROGRESS, customer);
        return application;
    }
}
