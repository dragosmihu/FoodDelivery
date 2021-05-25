package repository;

import config.DatabaseConfiguration;
import model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {
    public static void insertVehicle(Vehicle vehicle) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "INSERT INTO vehicles (noOfSeats, noOfWheels, producer)"
                + " VALUES('" + vehicle.getNoOfSeats() + "', '" + vehicle.getNoOfWheels() + "', '" + vehicle.getProducer() +"')";
        Statement statement = databaseConnection.createStatement();

        int rows = statement.executeUpdate(sql);
        DatabaseConfiguration.closeDatabaseConnection();
    }

    // PreparedStatement - use when we have parameters
    public static List<Vehicle> getVehicles() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Statement stmt = databaseConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (true){
                Vehicle vehicle = mapToVehicle(resultSet);
                if(vehicle == null) break;
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
        return vehicles;
    }

    // PreparedStatement
    public static void updateVehicle(int noOfSeats, int noOfWheels, String producer, int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String updateNameSql = "UPDATE vehicles SET noOfSeats = ?, noOfWheels = ?, producer = ? WHERE VehicleId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, noOfSeats);
            preparedStatement.setInt(2,noOfWheels);
            preparedStatement.setString(3, producer);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
    }

    private static Vehicle mapToVehicle(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){

            return new Vehicle(resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(4));
        }
        return null;
    }

    public static void delete(int id) throws  SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "DELETE FROM vehicles WHERE VehicleId=" + id;
        Statement statement = databaseConnection.createStatement();

        int rows = statement.executeUpdate(sql);
        DatabaseConfiguration.closeDatabaseConnection();
    }

}
