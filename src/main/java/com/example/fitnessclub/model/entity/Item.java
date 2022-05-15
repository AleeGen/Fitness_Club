package com.example.fitnessclub.model.entity;

public class Item extends AbstractEntity {

    private String serviceName;
    private String numberVisit;
    private int price;
    private String description;

    public Item(Long serviceId, String serviceName, String numberVisit, int price, String description) {
        setId(serviceId);
        this.serviceName = serviceName;
        this.numberVisit = numberVisit;
        this.price = price;
        this.description = description;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getNumberVisit() {
        return numberVisit;
    }

    public void setNumberVisit(String numberVisit) {
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
}
