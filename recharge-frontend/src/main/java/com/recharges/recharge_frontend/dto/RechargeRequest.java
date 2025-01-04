package com.recharges.recharge_frontend.dto;

public class RechargeRequest {
    private String operator;
    private String phoneNumber;
    private double amount;

    
    public RechargeRequest(String operator, String phoneNumber, double amount) {
        this.operator = operator;
        this.phoneNumber = phoneNumber;
        this.amount = amount;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

   
}
