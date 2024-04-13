package com.example.final_p.classes;

public class ServiceOptions {
    private String serviceName;
    private String serviceDescription;
    private double price;
    private int duration; // Duration in minutes

    public ServiceOptions(String serviceName, String serviceDescription, double price, int duration) {
        this.serviceName = serviceName;
        this.serviceDescription = serviceDescription;
        this.price = price;
        this.duration = duration;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
