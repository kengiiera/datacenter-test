package com.recharges.recharge_frontend.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.recharges.recharge_frontend.models.Operator;
import com.recharges.recharge_frontend.services.RechargeService;

@Named
@RequestScoped
public class RechargeBean {
    private String operator;
    private String phoneNumber;
    private double amount;
    private List<Operator> availableOperators;
    private final RechargeService rechargeService = new RechargeService();

     public RechargeBean() {
        this.availableOperators = new ArrayList<>();
        this.availableOperators.add(new Operator("TIGO", "TIGO"));
        this.availableOperators.add(new Operator("MOVISTAR", "MOVISTAR"));
        this.availableOperators.add(new Operator("COMCEL", "COMCEL"));
        this.availableOperators.add(new Operator("UFF", "UFF"));
    }
    public String makeRecharge() {
        try {
            rechargeService.createRecharge(operator, phoneNumber, amount);
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Recarga realizada con éxito"));
            return null;
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }


    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public List<Operator> getAvailableOperators() {
        return availableOperators;
    }
    public void setAvailableOperators(List<Operator> availableOperators) {
        this.availableOperators = availableOperators;
    }


    
}
