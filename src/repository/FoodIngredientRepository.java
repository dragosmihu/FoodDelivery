package repository;

import config.DatabaseConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FoodIngredientRepository {
    public static void insert(int foodId, int ingredientId) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "INSERT INTO foodingredient (FoodId, IngredientId)"
                + " VALUES('" + foodId + "', '" + ingredientId+"')";

        Statement statement = databaseConnection.createStatement();
        int rows = statement.executeUpdate(sql);

        DatabaseConfiguration.closeDatabaseConnection();
    }


    public static List<String> getIngredientsByFoodId(int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM foodingredient WHERE FoodId=?";
        List<String> ingredients = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                System.out.println("-----\n");
                ingredients.add(IngredientRepository.getIngredientById(resultSet.getInt(2)));

            }
            preparedStatement.close();
            return ingredients;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void update(List<String> ingredients, int foodId) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM foodingredient WHERE FoodId=?";
        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, foodId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String ingredientName = IngredientRepository.getIngredientById(resultSet.getInt(2));
                if (ingredients.contains(ingredientName)){
                    ingredients.remove(ingredientName);
                }
                else{
                    String sql = "DELETE FROM foodingredient WHERE FoodId=" + foodId+" AND IngredientId="+resultSet.getInt(2);
                    Statement statement = databaseConnection.createStatement();

                    int rows = statement.executeUpdate(sql);
                }

            }

            for(String ingredient : ingredients){
                int ingredientId = IngredientRepository.getIngredientByName(ingredient);
                if (ingredientId == 0)
                    ingredientId = IngredientRepository.insertIngredient(ingredient);
                insert(foodId,ingredientId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();

    }
}
