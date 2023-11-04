package com.mwananchibank.model;

public class Customer {
    private int id;
    private String name;
    private int nationalID;
    private String telephoneNumber;
    private String email;
    private String password;
    private LoanApplication loanApplication;

    public Customer(int id, String name, int nationalID, String telephoneNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.nationalID = nationalID;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
    }

    public Customer(String name, int nationalID, String telephoneNumber, String email, String password) {
        this.name = name;
        this.nationalID = nationalID;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNationalID() {
        return nationalID;
    }

    public void setNationalID(int nationalID) {
        this.nationalID = nationalID;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoanApplication getLoanApplication() {
        return loanApplication;
    }

    public void setLoanApplication(LoanApplication loanApplication) {
        this.loanApplication = loanApplication;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", nationalID=" + nationalID + ", telephoneNumber="
                + telephoneNumber + ", email=" + email + "]";
    }
        
    
}
