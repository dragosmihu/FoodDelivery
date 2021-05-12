import model.*;
import service.AppService;
import service.ReadWriteCSVService;

import java.io.IOException;
import java.util.*;


public class Main {
    private static final AppService appService = new AppService();
    private static int option = -1;
    private static void menu(){
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

        Scanner scanner = new Scanner(System.in);
        option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: appService.createUser(); menu();
            case 2: appService.createProduct(); menu();
            case 3: appService.createVehicle(); menu();
            case 4: {
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
                menu();
            }
            case 5:{
                System.out.println("Type product names; after you listed all products, type NOMORE");
                String productName = null;
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
                appService.createVenue(products);
                menu();
            }
            case 6:{

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

                Optional<Venue> venue = Optional.empty();
                while(venue.isEmpty()){
                    System.out.println("Enter venue's name: ");
                    String name = scanner.nextLine();

                    venue = appService.getVenues().stream().filter(u-> u.getName().equals(name)).findFirst();
                    if(venue.isEmpty()){
                        System.out.println("Wrong name, try again");
                    }
                }

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

                System.out.println("Type product names; after you listed all products, type NOMORE");
                String productName = null;
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

                appService.createOrder(user.get(),venue.get(), driver.get(),products);
                menu();
            }
            case 7: System.out.println(appService.getOrders().get(appService.getOrders().size() - 1)); menu();
            case 8: appService.listAllProducts(); menu();
            case 9: appService.listAllUsers(); menu();
            case 10: {
                Optional<Venue> venue = Optional.empty();
                while(venue.isEmpty()){
                    System.out.println("Enter venue's name: ");
                    String name = scanner.nextLine();

                    venue = appService.getVenues().stream().filter(u-> u.getName().equals(name)).findFirst();
                    if(venue.isEmpty()){
                        System.out.println("Wrong name, try again");
                    }
                }
                System.out.println("The sum of venue's products is " + appService.sumOfProducts(venue.get()));
                menu();
            }
            case 0: return;
            default: System.out.println("You entered a wrong number, please try again"); menu();
        }
    }

    public static void main(String[] args) throws IOException {

        menu();
        ReadWriteCSVService rwService = ReadWriteCSVService.getInstance();

        User f = new User("Dragos", "Mihu", 21);
        Food food = new Food("pizza", 30, Arrays.asList("flour", "salami", "cheese"), Taste.SALTY);
        Drink drink = new Drink("Cola", 5, 0.33);
        Vehicle vehicle = new Vehicle(4, 4, "Tesla");
        rwService.writeCSV(vehicle);
        List<Food> foods = rwService.readCSV("Food");
        List<User> users = rwService.readCSV("User");
        List<Drink> drinks = rwService.readCSV("Drink");
        List<Vehicle> vehicles = rwService.readCSV("Vehicle");
        System.out.print(foods);
        System.out.print(users);
        System.out.print(drinks);
        System.out.print(vehicles);

    }
}
