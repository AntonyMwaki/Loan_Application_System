package com.mwananchibank.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

import com.mwananchibank.model.Customer;
import com.mwananchibank.model.LoanApplication;
import com.mwananchibank.utils.jdbc.MysqlDataAccess;

public class UpdateLoanApplication {
        public static void applyLoan(LoanApplication application) {
        try {
            MysqlDataAccess dataAccess = new MysqlDataAccess();
            Connection connection = dataAccess.connect();
            Statement statement = connection.createStatement();

            String insertPreparedQuery = "INSERT INTO applications (loan_amount, loan_purpose, loan_terms, loan_status, customer_id)values(?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(insertPreparedQuery);

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
}
