package com.example.fitnessclub.model.entity;

public class Payment extends AbstractEntity {

    private Long userId;
    private Long serviceId;
    private byte remainedVisits;
    private boolean paid = false;

    public static Payment.PaymentBuilder newBuilder() {
        return new Payment().new PaymentBuilder();
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public class PaymentBuilder {

        public Payment.PaymentBuilder setUserId(Long userId) {
            Payment.this.userId = userId;
            return this;
        }

        public Payment.PaymentBuilder setServiceId(Long serviceId) {
            Payment.this.serviceId = serviceId;
            return this;
        }

        public Payment.PaymentBuilder setRemainedVisits(byte remainedVisits) {
            Payment.this.remainedVisits = remainedVisits;
            return this;
        }

        public Payment.PaymentBuilder setPaid(boolean paid) {
            Payment.this.paid = paid;
            return this;
        }

        public Payment build() {
            return Payment.this;
        }
    }
}
