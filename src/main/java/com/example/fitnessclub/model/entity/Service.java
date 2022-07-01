package com.example.fitnessclub.model.entity;

public class Service extends AbstractEntity {

    private String serviceName;
    private byte numberVisit;
    private byte validityPeriod;
    private int price;
    private String description;

    public static Service.ServiceBuilder newBuilder() {
        return new Service().new ServiceBuilder();
    }

    public byte getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(byte validity_period) {
        this.validityPeriod = validity_period;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public byte getNumberVisit() {
        return numberVisit;
    }

    public void setNumberVisit(byte numberVisit) {
        this.numberVisit = numberVisit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public class ServiceBuilder {

        public Service.ServiceBuilder setId(Long serviceId) {
            Service.this.setId(serviceId);
            return this;
        }

        public Service.ServiceBuilder setServiceName(String serviceName) {
            Service.this.serviceName = serviceName;
            return this;
        }

        public Service.ServiceBuilder setNumberVisit(byte numberVisit) {
            Service.this.numberVisit = numberVisit;
            return this;
        }

        public Service.ServiceBuilder setValidityPeriod(byte validity_period) {
            Service.this.validityPeriod = validity_period;
            return this;
        }

        public Service.ServiceBuilder setPrice(int price) {
            Service.this.price = price;
            return this;
        }

        public Service.ServiceBuilder setDescription(String description) {
            Service.this.description = description;
            return this;
        }

        public Service build() {
            return Service.this;
        }
    }
}
