package org.example;

public class Car {
    private int registrationNumber;
    private String brand;
    private double price;
    private int id;

    public Car(int registrationNumber, String brand, double price, int id) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.price = price;
        this.id = id;
    }


    public int getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(int registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
