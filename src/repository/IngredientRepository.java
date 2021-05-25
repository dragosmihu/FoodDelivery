package repository;

import config.DatabaseConfiguration;
import model.Product;

import java.sql.*;

public class IngredientRepository {
    public static int insertIngredient(String ingredient) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "INSERT INTO ingredients name"
                + " VALUES('" + ingredient  +"')";

        Statement statement = databaseConnection.createStatement();
        int rows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);

        DatabaseConfiguration.closeDatabaseConnection();
        return rows;
    }

    // PreparedStatement - use when we have parameters
    public static int getIngredientByName(String ingredient) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM ingredients WHERE name=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setString(1, ingredient);

            ResultSet resultSet = preparedStatement.executeQuery();
            DatabaseConfiguration.closeDatabaseConnection();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
        return 0;
    }

    public static String getIngredientById(int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM ingredients WHERE IngredientId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            DatabaseConfiguration.closeDatabaseConnection();
            return resultSet.getString(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
        return null;
    }

}
