import model.*;
import service.AppService;

public class Main {
    public static void main(String[] args){
        AppService appService = new AppService();

        User user = appService.createUser();
        Product product1 = appService.createProduct();
        Product product2 = appService.createProduct();
        Vehicle vehicle = appService.createVehicle();
        Driver driver = appService.createDriver(vehicle);
        Venue venue = appService.createVenue(product1, product2);
        Order order = appService.createOrder(user,venue,driver,product1, product2);
        appService.displayOrder(order);
    }
}
