package com.recharges.recharge_frontend.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

import com.recharges.recharge_frontend.services.StatisticsService;

@Named
@RequestScoped
public class StatisticsBean {

    private List<Statistic> byOperator;
    private List<Statistic> bySeller;
    private final StatisticsService statisticsService = new StatisticsService();

    @PostConstruct
    public void init() {
        try {
            StatisticsResponse response = statisticsService.getStatistics();
            this.byOperator = response.getByOperator();
            this.bySeller = response.getBySeller();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Statistic> getByOperator() {
        return byOperator;
    }

    public List<Statistic> getBySeller() {
        return bySeller;
    }

    public static class Statistic {
        private String name; 
        private int totalCount;
        private double totalAmount;


        public Statistic() {
        }


        public Statistic(String name, int totalCount, double totalAmount) {
            this.name = name;
            this.totalCount = totalCount;
            this.totalAmount = totalAmount;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }

    public static class StatisticsResponse {
        private List<Statistic> byOperator;
        private List<Statistic> bySeller;

        
        public StatisticsResponse() {
        }

        public StatisticsResponse(List<Statistic> byOperator, List<Statistic> bySeller) {
            this.byOperator = byOperator;
            this.bySeller = bySeller;
        }

        public List<Statistic> getByOperator() {
            return byOperator;
        }

        public void setByOperator(List<Statistic> byOperator) {
            this.byOperator = byOperator;
        }

        public List<Statistic> getBySeller() {
            return bySeller;
        }

        public void setBySeller(List<Statistic> bySeller) {
            this.bySeller = bySeller;
        }
    }
}
