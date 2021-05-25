package menus;

import model.*;
import service.AppService;
import service.AuditService;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class Menu {
    private static final AppService appService = new AppService();
    private static final Scanner scanner = new Scanner(System.in);

    private static Venue findVenue(){
        Optional<Venue> venue = Optional.empty();
        while(venue.isEmpty()){
            System.out.println("Enter venue's name: ");
            String name = scanner.nextLine();

            venue = appService.getVenues().stream().filter(u-> u.getName().equals(name)).findFirst();
            if(venue.isEmpty()){
                System.out.println("Wrong name, try again");
            }
        }
        return venue.get();
    }

    private static Product[] newProductList(){
        System.out.println("Type product names; after you listed all products, type NOMORE");
        String productName;
        Product[] products = new Product[30];
        int index = 0;
        while(true){
            productName = scanner.nextLine();
            if (productName.equals("NOMORE")){
                break;
            }
            String finalProductName = productName;
            Optional<Product> product = appService.getProducts().stream().filter(p -> p.getName().equals(finalProductName)).findFirst();
            if (product.isPresent()) {
                products[index++] = product.get();
            }
            else{
                System.out.println("Product not found, try again");
            }
            if (index == 30){
                System.out.println("You cannot put another product in the menu");
                break;
            }

        }
        return products;
    }

    private static void newOrder() throws IOException {
        Optional<User> user = Optional.empty();
        while(user.isEmpty()){
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            user = appService.getUsers().stream().filter(u-> u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)).findFirst();
            if(user.isEmpty()){
                System.out.println("Wrong name, try again");
            }
        }

        Venue venue = findVenue();

        Optional<Driver> driver = Optional.empty();
        while(driver.isEmpty()){
            System.out.println("Enter first name: ");
            String firstName = scanner.nextLine();
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();

            driver = appService.getDrivers().stream().filter(u-> u.getFirstName().equals(firstName) && u.getLastName().equals(lastName)).findFirst();
            if(driver.isEmpty()){
                System.out.println("Wrong name, try again");
            }
        }

        Product[] products = newProductList();

        appService.createOrder(user.get(),venue, driver.get(),products);
        AuditService.audit("create_order");
        menu();
    }

    private static void newVenue() throws IOException {
        Product[] products = newProductList();
        appService.createVenue(products);
        AuditService.audit("create_venue");
        menu();
    }


    private static void newDriver() throws IOException {
        System.out.println("Type driver's vehicle producer");
        String producer = scanner.nextLine();
        System.out.println(appService.getVehicles());
        Optional<Vehicle> vehicle = appService.getVehicles().stream().filter(v -> v.getProducer().equals(producer)).findFirst();
        if(vehicle.isEmpty()){
            System.out.println("Bad name, you can try again");
            menu();
            return;
        }
        appService.createDriver(vehicle.get());
        AuditService.audit("create_driver");
        menu();
    }
    public static void menu() throws IOException {
        System.out.println("1) Create user");
        System.out.println("2) Create product");
        System.out.println("3) Create vehicle");
        System.out.println("4) Create driver");
        System.out.println("5) Create venue");
        System.out.println("6) Create order");
        System.out.println("7) Display last order");
        System.out.println("8) List all products");
        System.out.println("9) List all users");
        System.out.println("10) Sum of all products of a venue");
        System.out.println("0) Exit");


        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: appService.createUser(); AuditService.audit("create_user"); menu();
            case 2: appService.createProduct(); AuditService.audit("create_product"); menu();
            case 3: appService.createVehicle(); AuditService.audit("create_vehicle"); menu();
            case 4: newDriver();
            case 5: newVenue();
            case 6: newOrder();
            case 7: System.out.println(appService.getOrders().get(appService.getOrders().size() - 1)); AuditService.audit("get_last_order");  menu();
            case 8: appService.listAllProducts(); AuditService.audit("list_all_products"); menu();
            case 9: appService.listAllUsers(); AuditService.audit("list_all_users"); menu();
            case 10: {
                Venue venue = findVenue();
                System.out.println("The sum of venue's products is " + appService.sumOfProducts(venue));
                AuditService.audit("sum_of_products");
                menu();
            }

            default: System.out.println("You entered a wrong number, please try again");
        }
    }
}
