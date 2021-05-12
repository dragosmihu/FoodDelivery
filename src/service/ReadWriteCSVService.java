package service;

import model.*;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadWriteCSVService {
    private static ReadWriteCSVService instance = null;

    private ReadWriteCSVService(){
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

    private User readUser(String[] values){
        User user = new User();
        user.setFirstName(values[0]);
        user.setLastName(values[1]);
        user.setAge(Integer.parseInt(values[2]));
        user.setNoOfOrders(Integer.parseInt(values[3]));
        user.setSpentMoney(Double.parseDouble(values[4]));
        return user;
    }

    private Vehicle readVehicle(String[] values){
        Vehicle vehicle = new Vehicle();
        vehicle.setProducer(values[0]);
        vehicle.setNoOfWheels(Integer.parseInt(values[1]));
        vehicle.setNoOfSeats(Integer.parseInt(values[2]));
        return vehicle;
    }

    private Drink readDrink(String[] values){
        Drink drink = new Drink();
        drink.setName(values[0]);
        drink.setPrice(Double.parseDouble(values[1]));
        drink.setBottleCapacity(Double.parseDouble(values[2]));
        return drink;
    }

    private Food readFood(String[] values){
        Food food = new Food();
        food.setName(values[0]);
        food.setPrice(Double.parseDouble(values[1]));

        List<String> ingredients = new ArrayList<>();
        for(int i = 2; i < values.length - 1; i++) {
            String x = values[i];
            x = x.replace('[', ' ');
            x = x.replace(']', ' ');
            x = x.strip();
            ingredients.add(x);
        }
        food.setIngredients(ingredients);
        switch (values[values.length - 1]){
            case "SOUR" -> food.setTaste(Taste.SOUR);
            case "SALTY" -> food.setTaste(Taste.SALTY);
            case "SWEET" -> food.setTaste(Taste.SWEET);
            default -> food.setTaste(Taste.BITTER);
        }
        return food;
    }


    public <T> List<T> readCSV(String className) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("csv/" + className + ".csv"));
        String line;
        T readObject;
        List<T> objects = new ArrayList<>();
        csvReader.readLine();
        while ((line = csvReader.readLine()) != null) {
            String[] values = line.split(",");
            switch (className){
                case "User" -> readObject = (T) readUser(values);
                case "Vehicle" -> readObject = (T) readVehicle(values);
                case "Drink" -> readObject = (T)readDrink(values);
                case "Food" -> readObject = (T) readFood(values);
                default -> throw new IllegalStateException("Unexpected value: " + className);
            }
            objects.add(readObject);
        }
        return  objects;

    }




}
