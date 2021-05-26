package menus;

import model.*;
import repository.DrinkRepository;
import repository.FoodRepository;
import repository.UserRepository;
import repository.VehicleRepository;
import service.AppService;
import service.AuditService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DBMenu {
    private static final Scanner scanner = new Scanner(System.in);

    private static void CRUDMenu(){
        System.out.println("1) Create");
        System.out.println("2) Read");
        System.out.println("3) Update");
        System.out.println("4) Delete");

    }

    public static void menu() throws SQLException {
        System.out.println("1) User");
        System.out.println("2) Vehicle");
        System.out.println("3) Food");
        System.out.println("4) Drink");


        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: CRUDMenu(); userMenu();
            case 2: CRUDMenu(); vehicleMenu();
            case 3: CRUDMenu(); foodMenu();
            case 4: CRUDMenu(); drinkMenu();
            default: System.out.println("You entered a wrong number, please try again"); menu();
        }
}

    private static void drinkMenu() throws SQLException {
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: {
                Drink drink = new Drink("Cola", 5, 0.33);
                DrinkRepository.insertDrink(drink);
                menu();
            }

            case 2: {
                System.out.println(DrinkRepository.getDrinks());
                menu();
            }
            case 3: {
                DrinkRepository.updateDrink("Pepsi", 6, 0.5, 13);
                menu();
            }

            case 4: {
                System.out.println("Please enter drink id\n");
                int id = scanner.nextInt();
                scanner.nextLine();
                DrinkRepository.delete(id);
                menu();
            }

            default: System.out.println("You entered a wrong number, please try again"); menu();
        }
    }

    private static void foodMenu() throws SQLException {
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: {
                Food food = new Food("pizza", 35.5, new ArrayList<>(Arrays.asList("flour", "oil","cheese", "salami")), Taste.SALTY);
                FoodRepository.insertFood(food);
                menu();
            }

            case 2: {
                System.out.println(FoodRepository.getFoods());
                menu();
            }
            case 3: {
                FoodRepository.updateFood("pizza", 40, new ArrayList<>(Arrays.asList("flour", "oil","cheese", "ham")), Taste.SOUR, 1);
                menu();
            }

            case 4: {
                System.out.println("Please enter food id\n");
                int id = scanner.nextInt();
                scanner.nextLine();
                FoodRepository.delete(id);
                menu();
            }

            default: System.out.println("You entered a wrong number, please try again"); menu();
        }
    }

    private static void vehicleMenu() throws SQLException {
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: {
                Vehicle vehicle = AppService.createVehicle();
                VehicleRepository.insertVehicle(vehicle);
                menu();
            }

            case 2: {
                System.out.println(VehicleRepository.getVehicles());
                menu();
            }
            case 3: {
                VehicleRepository.updateVehicle(5, 5, "Audi", 1);
                menu();
            }

            case 4: {
                System.out.println("Please enter vehicle id\n");
                int id = scanner.nextInt();
                scanner.nextLine();
                VehicleRepository.delete(id);
                menu();
            }

            default: System.out.println("You entered a wrong number, please try again"); menu();
        }
    }

    private static void userMenu() throws SQLException {
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option){
            case 1: {
                User user = AppService.createUser();
                UserRepository.insertUser(user);
                menu();
            }

            case 2: {
                System.out.println("Please enter user id\n");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println(UserRepository.getUserById(id));
                menu();
            }
            case 3: {
                UserRepository.updateUserName("Ionescu", "Adrian", 2);
                menu();
            }

            case 4: {
                System.out.println("Please enter user id\n");
                int id = scanner.nextInt();
                scanner.nextLine();
                UserRepository.delete(id);
                menu();
            }

            default: System.out.println("You entered a wrong number, please try again"); menu();
        }
    }
}
