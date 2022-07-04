package com.example.fitnessclub.model.entity;

import java.sql.Date;

public class ContractCT extends AbstractEntity {

    private short totalCost;
    private Date start;
    private Date end;
    private long userId;
    private long trainerId;

    public static ContractCT.ContractCTBuilder newBuilder() {
        return new ContractCT().new ContractCTBuilder();
    }

    public short getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(short totalCost) {
        this.totalCost = totalCost;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    public class ContractCTBuilder {

        public ContractCT.ContractCTBuilder setId(Long contractId) {
            ContractCT.this.setId(contractId);
            return this;
        }

        public ContractCT.ContractCTBuilder setTotalCost(short totalCost) {
            ContractCT.this.totalCost = totalCost;
            return this;
        }

        public ContractCT.ContractCTBuilder setStart(Date start) {
            ContractCT.this.start = start;
            return this;
        }

        public ContractCT.ContractCTBuilder setEnd(Date end) {
            ContractCT.this.end = end;
            return this;
        }

        public ContractCT.ContractCTBuilder setUserId(long userId) {
            ContractCT.this.userId = userId;
            return this;
        }

        public ContractCT.ContractCTBuilder setTrainerId(long trainerId) {
            ContractCT.this.trainerId = trainerId;
            return this;
        }

        public ContractCT build() {
            return ContractCT.this;
        }
    }
}
