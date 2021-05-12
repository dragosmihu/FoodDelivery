package model;

public class Vehicle {
    private int noOfWheels;
    private int noOfSeats;
    private String producer;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Vehicle() {
    }

    public Vehicle(int noOfWheels, int noOfSeats, String producer) {
        this.noOfWheels = noOfWheels;
        this.noOfSeats = noOfSeats;
        this.producer = producer;
    }

    public int getNoOfWheels() {
        return noOfWheels;
    }

    public void setNoOfWheels(int noOfWheels) {
        this.noOfWheels = noOfWheels;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(int noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    @Override
    public String toString() {
        return "Vehicle:\n Number of wheels: " +noOfWheels + "; Number of seats: " + noOfSeats + "\n";
    }
}
