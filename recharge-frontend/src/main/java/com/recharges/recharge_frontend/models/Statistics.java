package com.recharges.recharge_frontend.models;

import java.util.List;

public class Statistics {
    private List<StatisticGroup> byOperator;
    private List<StatisticGroup> bySeller;

    public List<StatisticGroup> getByOperator() {
        return byOperator;
    }

    public void setByOperator(List<StatisticGroup> byOperator) {
        this.byOperator = byOperator;
    }

    public List<StatisticGroup> getBySeller() {
        return bySeller;
    }

    public void setBySeller(List<StatisticGroup> bySeller) {
        this.bySeller = bySeller;
    }

    public static class StatisticGroup {
        private String name;
        private Integer totalCount;
        private Integer totalAmount;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

        public Integer getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Integer totalAmount) {
            this.totalAmount = totalAmount;
        }
    }
}
