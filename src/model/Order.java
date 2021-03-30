package model;

import java.util.Date;
import java.util.List;

public class Order {
    private Date date;
    private User user;
    private Venue venue;
    private Driver driver;
    private double total;
    private Product[] products;

    public Order() {
    }

    public Order(Date date, User user, Venue venue, Driver driver, double total, Product[] products) {
        this.date = date;
        this.user = user;
        this.venue = venue;
        this.driver = driver;
        this.total = total;
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }
}
