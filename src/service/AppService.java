package service;

import model.*;

import java.util.*;

public class AppService {
    private static Set<User> users = new TreeSet<User>();
    private static List<Product> products = new ArrayList<Product>();
    private static List<Vehicle> vehicles = new ArrayList<>();
    private static List<Driver> drivers = new ArrayList<>();
    private static List<Venue> venues = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();

    public Set<User> getUsers() {
        return users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public List<Venue> getVenues() {
        return venues;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public User createUser(){
        System.out.println("Creating user...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        User user = new User(firstName, lastName, age);
        users.add(user);
        System.out.println("-----------------------------\nUser created\n-----------------------------");
        return user;
    }

    public Product createProduct(){
        System.out.println("Creating product...");
        Product product;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Product name: ");
        String name = scanner.nextLine();

        System.out.print("Price: ");
        int price = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Press f for food or d for drink: ");
        String type = scanner.nextLine();

        if(type.equals("f")){
            System.out.print("Number of ingredients: ");
            int noOfIngredients = scanner.nextInt();
            List<String> ingredients = new ArrayList<>();
            scanner.nextLine();
            for (int i = 0; i < noOfIngredients; i++){
                System.out.print("Ingredient " + (i+1) + ": ");
                String ingredient = scanner.nextLine();
                ingredients.add(ingredient);
            }
            System.out.print("Taste (sweet, sour, salty, bitter) : ");
            String taste = scanner.nextLine();
            Taste T = switch (taste) {
                case "sweet" -> Taste.SWEET;
                case "sour" -> Taste.SOUR;
                case "salty" -> Taste.SALTY;
                default -> Taste.BITTER;
            };
            System.out.println("-----------------------------\nFood created\n-----------------------------");
            product = new Food(name,price, ingredients, T);

        }
        else{
            System.out.print("Bottle capacity: ");
            double capacity = scanner.nextDouble();
            System.out.println("-----------------------------\nDrink created\n-----------------------------");
            product = new Drink(name,price,capacity);
        }
        products.add(product);
        return product;

    }

    public Vehicle createVehicle(){
        System.out.println("Creating vehicle...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of wheels: ");
        int noOfWheels = scanner.nextInt();
        System.out.print("Number of seats: ");
        int noOfSeats = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Producer: ");
        String producer = scanner.nextLine();
        Vehicle vehicle = new Vehicle(noOfWheels, noOfSeats, producer);
        System.out.println("-----------------------------\nVehicle created\n-----------------------------");

        vehicles.add(vehicle);
        return vehicle;
    }

    public Driver createDriver(Vehicle vehicle){
        System.out.println("Creating driver...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.println("-----------------------------\nDriver created\n-----------------------------");

        Driver driver = new Driver(firstName,lastName,phoneNumber, vehicle);

        drivers.add(driver);
        return driver;
    }

    public Venue createVenue(Product... products){
        System.out.println("Creating venue...");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.println("-----------------------------\nVenue created\n-----------------------------");

        Venue venue = new Venue(address,name,products);

        venues.add(venue);
        return venue;
    }

    public Order createOrder(User user, Venue venue, Driver driver, Product... products){
        double total = 0;
        for (Product p : products){
            if(p != null)
                total += p.getPrice();
        }
        user.newOrder(total);
        venue.increaseEarnedMoney(total);

        Date date = new Date();
        Order order = new Order(date, user,venue, driver, total, products);

        orders.add(order);
        return order;
    }

    public void displayOrder(Order o){
        System.out.println("Ordered products: ");
        for(Product p : o.getProducts()){
            System.out.println(p);
        }
        System.out.println("User: ");
        System.out.println(o.getUser());
    }
    public void listAllUsers(){
        for(User u : users){
            System.out.println(u);
        }
    }
    public void listAllProducts(){
        for(Product p : products){
            System.out.println(p);
        }
    }

    public double sumOfProducts(Venue venue){
        double totalSum = 0;
        for(Product p : venue.getMenu()){
            if(p != null)
                totalSum += p.getPrice();
        }
        return totalSum;
    }
}
