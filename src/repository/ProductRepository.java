package repository;

import config.DatabaseConfiguration;
import model.Product;
import model.User;

import java.sql.*;

public class ProductRepository {
    public static int insertProduct(Product product) throws SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "INSERT INTO products (name, price)"
                + " VALUES('" + product.getName() + "', '" + product.getPrice() +"')";

        Statement statement = databaseConnection.createStatement();
        int rows = statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.next()){
            return resultSet.getInt(1);
        }
       // statement.close();
       // DatabaseConfiguration.closeDatabaseConnection();
        return 0;
    }


    public static Product getProductById(int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String selectSql = "SELECT * FROM products WHERE ProductId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(selectSql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            return mapToProduct(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void updateProduct(String name, double price, int id) {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String updateNameSql = "UPDATE products SET name = ? , price= ? WHERE ProductId=?";

        try {
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2,price);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.closeDatabaseConnection();
    }

    private static Product mapToProduct(ResultSet resultSet) throws SQLException {
        if (resultSet.next()){
            return new Product(resultSet.getString(2), resultSet.getInt(3));

        }
        return null;
    }

    public static void delete(int id) throws  SQLException {
        Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
        String sql = "DELETE FROM products WHERE ProductId=" + id;
        Statement statement = databaseConnection.createStatement();

        int rows = statement.executeUpdate(sql);
        DatabaseConfiguration.closeDatabaseConnection();
    }
}
