package com.example.fitnessclub.model.entity;

public class Payment extends AbstractEntity {

    private Long usersId;
    private Long serviceId;
    private byte remainedVisits;
    private boolean paid;

    public Payment(Long usersId, Long serviceId, byte remainedVisits, boolean paid) {
        this.usersId = usersId;
        this.serviceId = serviceId;
        this.remainedVisits = remainedVisits;
        this.paid = paid;
    }

    public Long getUsersId() {
        return usersId;
    }

    public void setUsersId(Long usersId) {
        this.usersId = usersId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public byte getRemainedVisits() {
        return remainedVisits;
    }

    public void setRemainedVisits(byte remainedVisits) {
        this.remainedVisits = remainedVisits;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
