package com.mwananchibank.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mwananchibank.model.Customer;
import com.mwananchibank.model.LoanApplication;
import com.mwananchibank.model.LoanStatus;
import com.mwananchibank.utils.jdbc.MysqlDataAccess;

public class RetrieveLoanApplications {

    public static List<LoanApplication> loanApplications() {
        List<LoanApplication> applications = new ArrayList<>();
            try {
            MysqlDataAccess dataAccess = new MysqlDataAccess();
            Connection connection = dataAccess.connect();
            Statement statement = connection.createStatement();
            
            String query = "SELECT * FROM applications;";

            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt("application_id");
                BigDecimal amount = resultSet.getBigDecimal("loan_amount");
                String purpose = resultSet.getString("loan_purpose");
                String loanTerms = resultSet.getString("loan_terms");
                String loanStatusStr = resultSet.getString("loan_status");
                LoanStatus loanStatus = mapLoanStatus(loanStatusStr);
                int customerId = resultSet.getInt("customer_id");
                Customer resultSetCustomer = RetrieveCustomers.retrieveCustomers()
                .stream().filter((customer)-> customerId == customer.getId()).findFirst().orElse(null);

                applications.add(new LoanApplication(id, amount, purpose, loanTerms, loanStatus, resultSetCustomer));
            }
            return applications;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static LoanStatus mapLoanStatus(String loanStatusStr) {
        LoanStatus loanStatus = null;
        if (loanStatusStr != null) {
            switch (loanStatusStr) {
                case "APPROVED":
                    loanStatus = LoanStatus.APPROVED;
                    break;
                case "INPROGRESS":
                    loanStatus = LoanStatus.INPROGRESS;
                    break;
                case "REJECTED":
                    loanStatus = LoanStatus.REJECTED;
                    break;
                case "CLOSED":
                    loanStatus = LoanStatus.CLOSED;
                    break;
            }
        }
        return loanStatus;
    }
}
