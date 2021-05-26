package repository;

import config.DatabaseConfiguration;
import model.Drink;
import model.Product;
import model.User;
import model.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DrinkRepository {
    public static void insertDrink(Drink drink) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        int prodId = ProductRepository.insertProduct(drink);
        String sqlDrink = "INSERT INTO drinks (ProductId, bottleCapacity)"
                + " VALUES('" + prodId + "', '" + drink.getBottleCapacity()+"')";
        Statement drinkStatement = databaseConnection.createStatement();

        drinkStatement.executeUpdate(sqlDrink);
        DatabaseConfiguration.closeDatabaseConnection();
    }


    public static List<Drink> getDrinks() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM drinks";
        List<Drink> drinks = new ArrayList<>();
        try {
            Statement stmt = databaseConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);

            while (true){
                Drink drink = mapToDrink(resultSet);
                if(drink == null) break;
                drinks.add(drink);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
        return drinks;
    }


    public static void updateDrink(String name, double price, double bottleCapacity, int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        String updateNameSql = "UPDATE drinks SET bottleCapacity=? WHERE ProductId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setDouble(1, bottleCapacity);
            preparedStatement.setInt(2,id);

            preparedStatement.executeUpdate();

            ProductRepository.updateProduct(name,price,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
    }

    private static Drink mapToDrink(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            Product base = ProductRepository.getProductById(resultSet.getInt(1));
            return new Drink(base.getName(), base.getPrice(), resultSet.getDouble(2));

        }
        return null;
    }

    public static void delete(int id) throws  SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "DELETE FROM drinks WHERE ProductId=" + id;
        Statement statement = databaseConnection.createStatement();

        statement.executeUpdate(sql);

        ProductRepository.delete(id);
        DatabaseConfiguration.closeDatabaseConnection();
    }

}
