package model;

import java.util.List;


public class Venue {
    private String address;
    private String name;
    private List<Product> menu;
    private double earnedMoney;

    public Venue() {
        earnedMoney = 0;
    }

    public Venue(String address, String name, List<Product> menu, double earnedMoney) {
        this.address = address;
        this.name = name;
        this.menu = menu;
        this.earnedMoney = earnedMoney;
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

    public List<Product> getMenu() {
        return menu;
    }

    public void setMenu(List<Product> menu) {
        this.menu = menu;
    }

    public double getEarnedMoney() {
        return earnedMoney;
    }

    public void setEarnedMoney(double earnedMoney) {
        this.earnedMoney = earnedMoney;
    }
}
