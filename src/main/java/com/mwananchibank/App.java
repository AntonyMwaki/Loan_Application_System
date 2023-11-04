package com.mwananchibank;

import java.util.List;
import java.util.Scanner;

import com.mwananchibank.auth.CustomerLogin;
import com.mwananchibank.auth.CustomerRegister;
import com.mwananchibank.auth.LoanOfficerLogin;
import com.mwananchibank.model.Customer;
import com.mwananchibank.model.LoanApplication;
import com.mwananchibank.model.LoanStatus;
import com.mwananchibank.service.MakeLoanApplication;
import com.mwananchibank.service.RetrieveLoanApplications;

public class App {
    Scanner scanner = new Scanner(System.in);
    private boolean isActive = false;
    
    public static void main(String[] args) {
        App app = new App();
        app.isActive = true;
        
        if (app.isActive) {
            app.rolesMenu();
        }
    }

    public void rolesMenu() {
        System.out.println();
        System.out.println("*************************");
        System.out.println("Welcome to Mwananchi Bank");
        System.out.println("1. Customer");
        System.out.println("2. Loan Officer");
        System.out.println("3. Quit");
        System.out.println("*************************");

        switchRolesMenu();
    }

    public void customerLoginMenu() {
        System.out.println();
        System.out.println("*************************");
        System.out.println("1. Login for Existing Customer");
        System.out.println("2. Register for New Customer");
        System.out.println("3. Back to main menu");
        System.out.println("4. Quit");
        System.out.println("*************************");

        switchCustomerLoginMenu();
    }

    public void customerMainMenu() {
        System.out.println();
        System.out.println("*************************");
        System.out.println("1. Apply for a Loan");
        System.out.println("2. View Loan Application Status");
        System.out.println("3. View Loan Agreement");
        System.out.println("4. Quit");
        System.out.println("*************************");
    }

    public void loanOfficerMenu() {
        System.out.println();
        System.out.println("*************************");
        System.out.println("1. View Loan Applications");
        System.out.println("2. Review Loan Application");
        System.out.println("3. Approve/Reject Loan Application:");
        System.out.println("4. Generate Loan Agreement");
        System.out.println("5. Update Loan Status");
        System.out.println("6. Back to main menu");
        System.out.println("7. Quit");
        System.out.println("*************************");
    }

    public void switchRolesMenu() {
        System.out.println();
        System.out.print("Select Role, Enter 1 for customer, 2 for Loan Officer or 3 to quit: ");
        int role = scanner.nextInt();
        switch (role) {
            case 1:
                customerLoginMenu();
                break;
            case 2:
                activateLoanOfficer();
                break;
            case 3:
                isActive = false;
                break;
            default:
            System.out.println("Invalid, Enter a valid role");
                break;
        }
    }

    public void switchCustomerLoginMenu() {
        System.out.println();
        System.out.print("Choose option: ");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                activateCustomer();
                break;
            case 2:
                CustomerRegister.register(scanner);
                break;
            case 3:
                System.out.println("Back");
                break;
            case 4:
                isActive = false;
                break;
            default:
            System.out.println("Invalid, Enter a valid role");
                break;
        }
    }

    public void activateLoanOfficer() {
        boolean isLoggedIn = LoanOfficerLogin.login(scanner);

        if(isLoggedIn) {
            loanOfficerMenu();
            switchLoanOfficerOptions();
        }

    }

    public void activateCustomer() {
        Customer customer = CustomerLogin.loggedInCustomer(scanner);
        if (customer != null) {
            customerMainMenu();
            switchCustomerOptions(customer);
        }
    }

    public void switchCustomerOptions(Customer customer) {
        System.out.println();
        System.out.println("Choose option:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                MakeLoanApplication.applyLoan(customer, scanner);
                break;
            case 2:
                CustomerRegister.register(scanner);
                break;
            case 3:
                System.out.println("Back");
                break;
            case 4:
                isActive = false;
                break;
            default:
                System.out.println("Invalid, Enter a valid role");
                break;
        }
    }

    public void switchLoanOfficerOptions() {
        System.out.println();
        System.out.println("Choose option:");
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                viewLoanApplications();
                break;
            case 2:
                reviewLoanApplication();
                break;
            case 3:
                approveOrRejectLoanApplication();
                break;
            case 4:
                isActive = false;
                break;
            default:
                System.out.println("Invalid, Enter a valid role");
                break;
        }
    }

    public void viewLoanApplications() {
        List<LoanApplication> applications = RetrieveLoanApplications.loanApplications();

        for (LoanApplication loanApplication : applications) {
            System.out.println(loanApplication);
            System.out.println();
        }
    }

    public LoanApplication reviewLoanApplication() {
        List<LoanApplication> applications = RetrieveLoanApplications.loanApplications();
        System.out.println("Enter customer national ID:");
        int nationalId = scanner.nextInt();

        for (LoanApplication loanApplication : applications) {
            if(loanApplication.getCustomer().getNationalID() == nationalId) {
                System.out.println();
                System.out.print(loanApplication + " " + loanApplication.getCustomer());
                System.out.println();
                System.out.println();

                return loanApplication;
            }
        }
        return null;
    }

    public void approveOrRejectLoanApplication() {
        LoanApplication loanApplication = reviewLoanApplication();
        loanStatusMenu();
        System.out.println("Select option 1 or 3 to approve or reject the application");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                loanApplication.setLoanStatus(LoanStatus.APPROVED);
                break;
            case 3:
                loanApplication.setLoanStatus(LoanStatus.REJECTED);
                break;
        }
    }

    public void loanStatusMenu() {
        System.out.println();
        System.out.println("1. APPROVED");
        System.out.println("2. INPROGRESS");
        System.out.println("3. REJECTED");
        System.out.println("4. CLOSED");
    }
    
}
