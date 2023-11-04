package com.mwananchibank.auth;

import java.util.Scanner;

public class LoanOfficerLogin {
    private static final String ADMIN_PASSWORD = "Admin123";

    public static boolean login(Scanner scanner) {
        scanner.nextLine();
        int retry = 0;
        while (retry <= 2) {
            System.out.println();
            System.out.print("Enter Loan Officer password: ");
            String password = scanner.nextLine();
            
            if (ADMIN_PASSWORD.equalsIgnoreCase(password)) {
                System.out.println("Login successful, Welcome.");
                return true;
            }

            if (retry == 2) {
                System.out.println("Invalid password. Login failed");
            } else {
                System.out.println("Invalid password. Retry");
            }

            retry++;
        }
        
        return false;
    }
}
