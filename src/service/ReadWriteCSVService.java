package service;

import model.Drink;
import model.Food;
import model.User;
import model.Vehicle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class ReadWriteCSVService {
    private static ReadWriteCSVService instance = null;

    private ReadWriteCSVService(){
        System.out.print("abcd");

    }

    public static ReadWriteCSVService getInstance(){
        if (instance == null){
            instance = new ReadWriteCSVService();
        }
        return instance;
    }

    private String getUserDetails(User user){
        String[] details = new String[]{user.getFirstName(),
                user.getLastName(),
                Integer.toString(user.getAge()),
                Integer.toString(user.getNoOfOrders()),
                Double.toString(user.getSpentMoney())};

        return String.join(",",details) + "\n";
    }

    private String getVehicleDetails(Vehicle vehicle){
        String[] details = new String[]{vehicle.getProducer(),
                Integer.toString(vehicle.getNoOfWheels()),
                Integer.toString(vehicle.getNoOfSeats())};

        return String.join(",",details) + "\n";
    }

    private String getDrinkDetails(Drink drink){
        String[] details = new String[]{drink.getName(),
                Double.toString(drink.getPrice()),
                Double.toString(drink.getBottleCapacity())};

        return String.join(",",details) + "\n";
    }


    private String getFoodDetails(Food food){
        String[] details = new String[]{food.getName(),
                Double.toString(food.getPrice()),
                String.valueOf(food.getIngredients()),
                String.valueOf(food.getTaste())};

        return String.join(",",details) + "\n";
    }

    public <T> void writeCSV(T obj) throws IOException {
        FileWriter csvWriter = new FileWriter("csv/"+obj.getClass().getSimpleName()+".csv", true);
        String details = switch (obj.getClass().getSimpleName()){
            case "User" -> getUserDetails((User) obj);
            case "Vehicle" -> getVehicleDetails((Vehicle) obj);
            case "Drink" -> getDrinkDetails((Drink) obj);
            case "Food" -> getFoodDetails((Food) obj);
            default -> throw new IllegalStateException("Unexpected value: " + obj.getClass().getSimpleName());
        };
        csvWriter.append(details);
        csvWriter.flush();
        csvWriter.close();
    }





}
