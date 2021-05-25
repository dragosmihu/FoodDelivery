import menus.DBMenu;
import model.*;
import repository.UserRepository;
import repository.VehicleRepository;
import service.ReadWriteCSVService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException, SQLException {

        //menus.Menu.menu();
        ReadWriteCSVService rwService = ReadWriteCSVService.getInstance();

        User f = new User("Dragos", "Mihu", 21);
        Food food = new Food("pizza", 30, Arrays.asList("flour", "salami", "cheese"), Taste.SALTY);
        Drink drink = new Drink("Cola", 5, 0.33);
        Vehicle vehicle = new Vehicle(2, 1, "MB");
        rwService.writeCSV(vehicle);

        //UserRepository.insertUser(f);
       // System.out.println(UserRepository.getUserById(1));

        //VehicleRepository.insertVehicle(vehicle);
        //System.out.println(VehicleRepository.getVehicles());
        //VehicleRepository.updateVehicle(7,7,"Audi", 1);
        //VehicleRepository.delete(3);

        menus.DBMenu.menu();
    }
}
