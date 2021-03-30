package model;

public class Drink extends  Product{
    private double bottleCapacity;

    public  Drink(){

    }
    public Drink(String name, double price, double bottleCapacity) {
        super(name, price);
        this.bottleCapacity = bottleCapacity;
    }

    public double getBottleCapacity() {
        return bottleCapacity;
    }

    public void setBottleCapacity(double bottleCapacity) {
        this.bottleCapacity = bottleCapacity;
    }
}
