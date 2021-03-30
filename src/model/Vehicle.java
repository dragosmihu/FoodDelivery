package model;

public class Vehicle {
    private int noOfWheels;
    private int noOfSeats;

    public Vehicle() {
    }

    public Vehicle(int noOfWheels, int noOfSeats) {
        this.noOfWheels = noOfWheels;
        this.noOfSeats = noOfSeats;
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
