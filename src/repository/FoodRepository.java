package repository;

import config.DatabaseConfiguration;
import model.Drink;
import model.Food;
import model.Product;
import model.Taste;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository {
    public static void insertFood(Food food) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        int prodId = ProductRepository.insertProduct(food);
        String sql = "INSERT INTO foods (ProductId, taste)"
                + " VALUES('" + prodId + "', '" + food.getTaste()+"')";
        Statement stmt = databaseConnection.createStatement();
        int foodId = stmt.executeUpdate(sql);
        for (String ingredient:food.getIngredients()){
            int ingredientId = IngredientRepository.getIngredientByName(ingredient);
            if(ingredientId == 0){
                ingredientId = IngredientRepository.insertIngredient(ingredient);
            }
            FoodIngredientRepository.insert(foodId, ingredientId);

        }

        DatabaseConfiguration.closeDatabaseConnection();
    }

    // PreparedStatement - use when we have parameters
    public static List<Food> getFoods() {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM foods";
        List<Food> foods = new ArrayList<>();
        try {
            Statement stmt = databaseConnection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            foods.add(mapToFood(resultSet));
            while (resultSet.next()){

                foods.add(mapToFood(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
        return foods;
    }

    // PreparedStatement
    public void updateFood(String name, double price, List<String> ingredients, Taste T, int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();

        String updateNameSql = "UPDATE foods SET taste = ? WHERE ProductId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, String.valueOf(T));
            preparedStatement.setInt(2,id);

            preparedStatement.executeUpdate();

            ProductRepository.updateProduct(name,price,id);
            FoodIngredientRepository.update(ingredients,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
    }

    private static Food mapToFood(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            Product base = ProductRepository.getProductById(resultSet.getInt(1));
            List<String> ingredients = FoodIngredientRepository.getIngredientsByFoodId(resultSet.getInt(1));
            Taste T = switch (resultSet.getString(2)) {
                case "sweet" -> Taste.SWEET;
                case "sour" -> Taste.SOUR;
                case "salty" -> Taste.SALTY;
                default -> Taste.BITTER;
            };
            return new Food(base.getName(), base.getPrice(), ingredients, T);

        }
        return null;
    }

    public void delete(int id) throws  SQLException {
        ProductRepository.delete(id);
    }
}
