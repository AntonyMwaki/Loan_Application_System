package com.mwananchibank.model;

import java.math.BigDecimal;

public class LoanApplication {
    private int id;
    private BigDecimal loanAmount;
    private String loanPurpose;
    private String loanTerms;
    private LoanStatus loanStatus;
    private Customer customer;
    
    public LoanApplication(int id, BigDecimal loanAmount, String loanPurpose, String loanTerms, LoanStatus loanStatus,
            Customer customer) {
        this.id = id;
        this.loanAmount = loanAmount;
        this.loanPurpose = loanPurpose;
        this.loanTerms = loanTerms;
        this.loanStatus = loanStatus;
        this.customer = customer;
    }

    public LoanApplication(BigDecimal loanAmount, String loanPurpose, String loanTerms, LoanStatus loanStatus,
            Customer customer) {
        this.loanAmount = loanAmount;
        this.loanPurpose = loanPurpose;
        this.loanTerms = loanTerms;
        this.loanStatus = loanStatus;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(String loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public String getLoanTerms() {
        return loanTerms;
    }

    public void setLoanTerms(String loanTerms) {
        this.loanTerms = loanTerms;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LoanApplication [id=" + id + ", loanAmount=" + loanAmount + ", loanPurpose=" + loanPurpose
                + ", loanTerms=" + loanTerms + ", loanStatus=" + loanStatus + "]";
    }

        
}
