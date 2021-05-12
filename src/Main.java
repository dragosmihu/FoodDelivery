import model.*;
import service.AppService;
import service.ReadWriteCSVService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {


    public static void main(String[] args) throws IOException, IllegalAccessException {
        /*
        AppService appService = new AppService();

        User user = appService.createUser();
        Product product1 = appService.createProduct();
        Product product2 = appService.createProduct();
        Vehicle vehicle = appService.createVehicle();
        Driver driver = appService.createDriver(vehicle);
        Venue venue = appService.createVenue(product1, product2);
        Order order = appService.createOrder(user,venue,driver,product1, product2);
        appService.displayOrder(order);
        appService.listAllProducts();
        appService.listAllUsers();
        System.out.println("The sum of venue's products is " + appService.sumOfProducts(venue));*/

        ReadWriteCSVService rwService = ReadWriteCSVService.getInstance();

        User f = new User("Dragos", "Mihu", 21);
        Food food = new Food("pizza", 30, Arrays.asList("flour", "salami", "cheese"), Taste.SALTY);
        rwService.writeCSV(food);
        List<Food> foods = rwService.readCSV("Food");
        System.out.print(foods);
    }
}
