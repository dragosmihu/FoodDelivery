package model;

import java.util.Arrays;
import java.util.List;


public class Venue {
    private String address;
    private String name;
    private Product[] menu;
    private double earnedMoney;

    public Venue() {
        earnedMoney = 0;
    }

    public Venue(String address, String name, Product[] menu) {
        this.address = address;
        this.name = name;
        this.menu = menu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product[] getMenu() {
        return menu;
    }

    public void setMenu(Product[] menu) {
        this.menu = menu;
    }

    public double getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(double earnedMoney) {
        this.earnedMoney = earnedMoney;
    }

    public void increaseEarnedMoney(double earnedMoney){
        this.earnedMoney += earnedMoney;
    }

    @Override
    public String toString() {
        StringBuffer returnString = new StringBuffer("Name: " + name+ "; Address: " + address);
        for(Product p : menu){
            returnString.append(p);
        }
        returnString.append("\nEarned money: " + earnedMoney);
        return returnString.toString();
    }
}
